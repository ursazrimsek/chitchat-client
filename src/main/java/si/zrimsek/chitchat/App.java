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
 * Hello ChitChat!
 */
public class App {
    public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
    	String hello = Request.Get("http://chitchat.andrej.com")
    						.execute()
                            .returnContent().asString();
        System.out.println(hello);
        
        logIn("Ursa");
        logIn("Toto");
//        getUsers();
        sendMessage(true, "Toto", "", "halohalo!");
//        sendMessage(false, "vsiljivec", "Toto", "Vse najboljse za 13. rojstni dan!");
//        sendMessage(true, "Ursa", "....", "dkflw!");
//        recieveMessages("Toto");
    }
    
    
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
    
    
    public static Boolean logIn(String user) throws URISyntaxException, ClientProtocolException, IOException {
	    try {	
    		URI uri = new URIBuilder("http://chitchat.andrej.com/users")
	    				.addParameter("username", user)
	    				.build();
	    	Request.Post(uri).execute().returnContent().asString();
	    	return true;
	    }
	    catch (Exception HttpResponseException) {
	    	System.out.println("User already exists");
	    	return false;
	    }
    }
    
    
    public static void logOut(String user) throws URISyntaxException, ClientProtocolException, IOException {
    	URI uri = new URIBuilder("http://chitchat.andrej.com/users")
		          .addParameter("username", user)
		          .build();
		
    	Request.Delete(uri).execute().returnContent().asString();
    }
    

    
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
    	  catch (Exception HttpResponseException) {
    		  System.out.println(sender + "not logged in");
  	      }
    }
    
}
