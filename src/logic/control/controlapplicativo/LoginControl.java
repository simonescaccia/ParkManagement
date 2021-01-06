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
	
	public String getParkVisitor() {
		//dummy dummy
		String s = "ParkVisitor";
		return s;
	}
	
	public void validateOnDB(String idUser) {
		//verifica se l'utente è già stato inserito nel database
		//aggiungi il Park Visitor se non presente nel database
		System.out.println("I'm validating the user: "+ idUser);
	}
	
}
