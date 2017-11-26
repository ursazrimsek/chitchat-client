package si.zrimsek.chitchat;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Hello ChitChat!
 */
public class App {
    public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
    	String hello = Request.Get("http://chitchat.andrej.com")
    						.execute()
                            .returnContent().asString();
        System.out.println(hello);
    }
    
    
    public static void getUsers() throws ClientProtocolException, IOException {
    	String users = Request.Get("http://chitchat.andrej.com/users")
								.execute()
								.returnContent()
								.asString();
	    
    	System.out.println(users);
    }
    
    
    public static void login(String user) throws URISyntaxException, ClientProtocolException, IOException {
	    try {	
    		URI uri = new URIBuilder("http://chitchat.andrej.com/users")
	    				.addParameter("username", user)
	    				.build();
	    	String responseBody = Request.Post(uri)
	                         		.execute()
	                         		.returnContent()
	                         		.asString();
	
	    	System.out.println(responseBody);
	    }
	    catch (Exception HttpResponseException) {
	    	System.out.println("User already exists");
	    }
    }
    
    
    public static void logout(String user) throws URISyntaxException, ClientProtocolException, IOException {
    	URI uri = new URIBuilder("http://chitchat.andrej.com/users")
		          .addParameter("username", user)
		          .build();
		String responseBody = Request.Delete(uri)
		                               .execute()
		                               .returnContent()
		                               .asString();
		System.out.println(responseBody);
    }
    

    
    
}
