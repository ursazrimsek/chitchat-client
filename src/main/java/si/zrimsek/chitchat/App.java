package si.zrimsek.chitchat;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Razred, ki skrbi za pogovor s serverjem.
 */
public class App {
	
	// Vrne seznam prijavljenih uporabnikov (razred User)
    public static List<User> getUsers() throws ClientProtocolException, IOException {
    	String users = Request.Get("http://chitchat.andrej.com/users")
							.execute()
							.returnContent()
							.asString();
    	
    	ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new ISO8601DateFormat());
		TypeReference<List<User>> t = new TypeReference<List<User>>() { };
		List<User> users_object = mapper.readValue(users, t);
		return users_object;
    }
    
    // Prijavi uporabnika
    public static Boolean logIn(String user) throws URISyntaxException, ClientProtocolException, IOException {
	    try {	
    		URI uri = new URIBuilder("http://chitchat.andrej.com/users")
	    				.addParameter("username", user)
	    				.build();
	    	Request.Post(uri).execute().returnContent().asString();
	    	return true;
	    }
	    catch (Exception HttpResponseException) {
	    	return false;
	    }
    }
    
    // Odjavi uporabnika
    public static void logOut(String user) throws URISyntaxException, ClientProtocolException, IOException {
    	URI uri = new URIBuilder("http://chitchat.andrej.com/users")
		          .addParameter("username", user)
		          .build();
		
    	Request.Delete(uri).execute().returnContent().asString();
    }
    
    // Prejme sporočila za danega uporabnika (razred Messages)
    public static List<Message> recieveMessages(String user) throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = new URIBuilder("http://chitchat.andrej.com/messages")
					.addParameter("username", user)
					.build();

		String received = Request.Get(uri)
								.execute()
		                        .returnContent()
		                        .asString();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new ISO8601DateFormat());
		TypeReference<List<Message>> t = new TypeReference<List<Message>>() { };
		List<Message> messages = mapper.readValue(received, t);
		
		return messages;
    }
    
    // Pošlje sporočilo
    public static void sendMessage(Boolean global, String sender, String recipient, String text) throws ClientProtocolException, IOException, URISyntaxException {
    	URI uri = new URIBuilder("http://chitchat.andrej.com/messages")
    	        .addParameter("username", sender)
    	        .build();
    	String message = "";
    	
    	if (global) {
    		message = "{\"global\" : true, \"text\" :\"" + text + "\"}";
    	} else {
    		message = "{\"global\" : false, \"recipient\" : \"" + recipient + "\", \"text\" :\"" + text + "\"}";
    	}
    	  
    	try {
    	Request.Post(uri).bodyString(message, ContentType.APPLICATION_JSON)
    	       		.execute()
    	       		.returnContent().asString();
    	} 
    	catch (Exception HttpResponseException) { }
    } 
}
