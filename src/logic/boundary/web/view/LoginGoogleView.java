package logic.boundary.web.view;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import logic.control.controlapplicativo.LoginControl;

public class LoginGoogleView {

public void loginWebVerifyToken(String idtoken) throws IOException, GeneralSecurityException {
		
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
			String idUser = payload.getSubject();
	
			// Get profile information from payload
			String email = payload.getEmail();
			boolean emailVerified = payload.getEmailVerified();
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
	
            //chiama loginControl.validate per:
			//verificare se l'utente è già stato inserito nel database
			//aggiungere il Park Visitor se non presente nel database
			LoginControl lC = new LoginControl();
            lC.validateOnDB(idUser);

		} else {
			//refresh token?
			System.out.println("Invalid ID token.");
		}
		

	}

	
}
