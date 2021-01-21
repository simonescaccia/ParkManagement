package logic.boundary.desktop.view;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import logic.control.bean.PositionBean;
import logic.exception.PositionNotFoundException;

public class PositionGoogleMapsView {
	
	
	public void getPosition(PositionBean pB) throws PositionNotFoundException{
		
		String key = "AIzaSyB_gV3LAG733x2hfpgz7mxkl9bWRjqvPCo";
		String googleMapsGeoServer = "https://www.googleapis.com/geolocation/v1/geolocate";
		
		try {
			
			String urlParams = "key="+key;
        	byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8); 
        	int postDataLength = postData.length;
			
			URL url = new URL(googleMapsGeoServer);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setRequestMethod("POST");
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
                  throw new PositionNotFoundException("Connection GMaps err: "+responseCode);
            }
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            
            in.close();
            connection.disconnect();
            
            String responseS = response.toString();
            
            String lat = getLat(responseS);
            String lng = getLng(responseS);
            
            pB.setLatitude(Double.parseDouble(lat));
            pB.setLongitude(Double.parseDouble(lng));
            
		} catch (IOException e) {
			 throw new PositionNotFoundException("Google Maps failure");
		}
	}
	
    protected String getLng(String response){
        
        StringBuilder sub = new StringBuilder();
        
        int indexStart = response.indexOf("\"lng\":") + 7;
        char c;
        while((c = response.charAt(indexStart)) != ' ') {
        	indexStart++;
        	sub.append(c);
        }
        
        return sub.toString();
    }
    
    protected String getLat(String response){
        
        StringBuilder sub = new StringBuilder();
        
        int indexStart = response.indexOf("\"lat\":") + 7;
        char c;
        while((c = response.charAt(indexStart)) != ',') {
        	indexStart++;
        	sub.append(c);
        }
        
        return sub.toString();
    }
}
