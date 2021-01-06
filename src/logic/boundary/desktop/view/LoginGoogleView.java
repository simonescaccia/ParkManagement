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
import logic.control.bean.MessageBean;
import logic.control.controlapplicativo.LoginControl;

public class LoginGoogleView {

	private static String googleAuthServer = "https://accounts.google.com/o/oauth2/v2/auth";
	private static String googleAccessTokenServer = "https://oauth2.googleapis.com/token";
	private static String scope = "email%20profile";
	private static String responseType = "code";
	private static String state = "security_token%3D138r5719ru3e1%26url%3Dhttps%3A%2F%2Foauth2.example.com%2Ftoken";
	private static String redirectUri = "http://localhost:8080/SpeedyFila/jspPages/CloseThisPage.jsp";
	private static String clientId = "80408227597-8q6kctaqnd2up2am22u5o517ql90474k.apps.googleusercontent.com";
	private static String clientSecret = "PwK86HUf4ABIyBa4ZU05X9zb";
		
	public void loginDesktop(BackgroundPage bgPage) {
		
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
                    
                    String accessToken = doGetAccessTokenRequest(accessCode, bgPage);

                    String idUser = decodeJWT(accessToken);
                    
//	                notifyLoginViewCompleted();
                    
                    
                    //chiama loginControl.validate per:
        			//verificare se l'utente è già stato inserito nel database
        			//aggiungere il Park Visitor se non presente nel database
                    
                    LoginControl lC = new LoginControl();
                    lC.validateOnDB(idUser);
                    
                }
                
            }
        });
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
		
	}
	
	protected String doGetAccessTokenRequest(String accessCode, BackgroundPage bgPage) {
        
		try {

        	String urlParams = "code="+accessCode+"&client_id="+clientId+"&client_secret="+clientSecret+"&redirect_uri="+redirectUri+"&grant_type=authorization_code";
        	
        	byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
        	
        	URL url = new URL(googleAccessTokenServer);
        	
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Host", "oauth2.googleapis.com");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + postDataLength);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(postData);
            wr.flush();
            
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
            } else {
                  MessageBean mB = new MessageBean();
                  mB.setMessage("Error getting access token for OAuth Login!");
                  mB.setType(false);
                  bgPage.showMessage(mB);
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

            JSONObject json = new JSONObject(fullResponse);
            
            return json.getString("id_token");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
    protected String decodeJWT(String accessToken){

    	String[] splitString = accessToken.split("\\.");
        String base64EncodedBody = splitString[1];
    	
        Base64 base64Url = new Base64(true);
        String accessTokenString = new String(base64Url.decode(base64EncodedBody));
        StringBuilder sub = new StringBuilder();
        
        int indexStart = accessTokenString.indexOf("\"sub\":\"") + 7;
        char c;
        while((c = accessTokenString.charAt(indexStart)) != '\"') {
        	indexStart++;
        	sub.append(c);
        }
        
        return sub.toString();
    }
}
