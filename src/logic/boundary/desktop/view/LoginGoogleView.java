package logic.boundary.desktop.view;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.boundary.desktop.controlgrafico.LoginGuiControl;
import logic.control.bean.LoginBean;

public class LoginGoogleView {

	private static String googleAuthServer = "https://accounts.google.com/o/oauth2/v2/auth";
	private static String googleAccessTokenServer = "https://oauth2.googleapis.com/token";
	private static String googleRevokeTokenServer = "https://oauth2.googleapis.com/revoke";
	private static String scope = "email%20profile";
	private static String responseType = "code";
	private static String state = "security_token%3D138r5719ru3e1%26url%3Dhttps%3A%2F%2Foauth2.example.com%2Ftoken";
	private static String redirectUri = "http://localhost:8080/SpeedyFila/jspPages/closeThisPage.jsp";
	private static String clientId = "80408227597-8q6kctaqnd2up2am22u5o517ql90474k.apps.googleusercontent.com";
	private static String clientSecret = "PwK86HUf4ABIyBa4ZU05X9zb";
		
	
	public boolean revokeTokenDesktop(String aT) {
		
		//revoke token
		try {

        	String urlParams = "token=" + aT;
        	
        	byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8);
        	
        	URL url = new URL(googleRevokeTokenServer);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            int responseCode = sendRequest(connection, postData);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
            } else {
                  return false;
            }
            
        } catch (IOException e) {
            return false;
        }
		
		//notify logout completed
		return true;
	}
	
	public void loginDesktop(LoginGuiControl lGC) {
		
		Stage stage = new Stage();
		stage.setTitle("Google login");
		
        WebView root = new WebView();
        WebEngine engine = root.getEngine();
        
        
        String authURL = googleAuthServer+"?scope="+scope+"&response_type="+responseType +"&state="+state+"&redirect_uri="+redirectUri+"&client_id="+clientId;
        
        engine.load(authURL);
        
        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED.equals(newValue)) {
            	
            	String location = engine.getLocation();
                
                if (location.contains("code") && location.startsWith(redirectUri)) {

                    stage.close();
                    String accessCode = location.substring(location.indexOf("code=") + 5);                 
                    
                    JSONObject fullResponse = doGetAccessTokenRequest(accessCode);
                    
                    String idToken = fullResponse.getString("id_token");
                    String accessToken = fullResponse.getString("access_token");
                    
                    String idUser = getSub(idToken);
                    
                                       
                    //notificare i valore
                    LoginBean lB = new LoginBean();
                    lB.setAccessToken(accessToken);
                    lB.setUserID(idUser);
                    
                    lGC.setLoginState(lB);
                    
                }
                
            }
        });
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
		
	}
	
	protected JSONObject doGetAccessTokenRequest(String accessCode) {
        
		try {

        	String urlParams = "code="+accessCode+"&client_id="+clientId+"&client_secret="+clientSecret+"&redirect_uri="+redirectUri+"&grant_type=authorization_code";
        	
        	byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8); 
        	
        	URL url = new URL(googleAccessTokenServer);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            int responseCode = sendRequest(connection, postData);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
            } else {
                  return null;
            }
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            
            in.close();
            connection.disconnect();
            
            String fullResponse = response.toString();
            
            return new JSONObject(fullResponse);
            
        } catch (IOException e) {
            return null;
        }
	}
	
	protected int sendRequest(HttpURLConnection connection, byte[] postData) throws IOException{
		
		int postDataLength = postData.length;

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Host", "oauth2.googleapis.com");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", "" + postDataLength);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(postData);
        wr.flush();
        
        return connection.getResponseCode();
	}
	
    protected String getSub(String idToken){

    	String[] splitString = idToken.split("\\.");
        String base64EncodedBody = splitString[1];
    	
        Base64 base64Url = new Base64(true);
        String idTokenString = new String(base64Url.decode(base64EncodedBody), StandardCharsets.UTF_8);
        
        StringBuilder sub = new StringBuilder();
        
        int indexStart = idTokenString.indexOf("\"sub\":\"") + 7;
        char c;
        while((c = idTokenString.charAt(indexStart)) != '\"') {
        	indexStart++;
        	sub.append(c);
        }
        
        return sub.toString();
    }
}
