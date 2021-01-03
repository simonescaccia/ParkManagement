package logic.control.controlapplicativo;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class LoginControl {
	
	protected void addUser() {
		//this method call ParkVisitorDao to insert a new user
	}
	
	public void verifyToken(String idtoken) throws IOException, GeneralSecurityException{
		
		System.out.println("qui");
		
		//verifica token
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
			    // Specify the CLIENT_ID of the app that accesses the backend:
			    .setAudience(Collections.singletonList("80408227597-gnan03ov689qhrgt23eho38jmt0krs06.apps.googleusercontent.com"))
			    // Or, if multiple clients access the backend:
			    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
			    .build();

		// (Receive idTokenString by HTTPS POST)

		GoogleIdToken idToken = verifier.verify(idtoken);
		if (idToken != null) {
			
			Payload payload = idToken.getPayload();
	
			// Print user identifier
			String userId = payload.getSubject();
			System.out.println("User ID: " + userId);
	
			// Get profile information from payload
			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
	
			//verifica se l'utente è già stato inserito nel database
				
			//aggiungi Park Visitor se non presente nel database
		  

		} else {
		  System.out.println("Invalid ID token.");
		}
		

	}
	
	
	public String getParkVisitor() {
		//dummy dummy
		String s = "ParkVisitor";
		return s;
	}
}
