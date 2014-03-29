package a.service.proj;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jettison.json.JSONArray;

import twitter4j.Status;
import twitter4j.TwitterException;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@XmlRootElement
@Path("/moodn")
public class ReturnCalls {
	 @GET
	  @Path("/hashtweet")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public HashMap<String, String> getAllTweetsForHashtag(@QueryParam(value = "hash") String hash) throws ClientProtocolException, IOException, TwitterException {
			System.out.println(hash);	
		  return TwitterApiCalls.getTweetsFromHashTag(hash);
	  }
	  
	 @GET
	  @Path("/usertweet")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public HashMap<String, String> getAllTweetsForUser(@QueryParam(value = "user") String user) throws ClientProtocolException, IOException, TwitterException {
		//  String json = new Gson().toJson(TwitterApiCalls.getTweetsFromUserName(user) );
		 // JSONArray jsonA = JSONArray.fromObject(TwitterApiCalls.getTweetsFromUserName(user));
		  return TwitterApiCalls.getTweetsFromUserName(user);
		  
		//  return null;
	  }
	  
	  @GET
	  @Path("/partytweets")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public HashMap<String, String> getTweetsForParty(@QueryParam(value = "party") String party) throws ClientProtocolException, IOException, TwitterException {
		//  String json = new Gson().toJson(TwitterApiCalls.getTweetsFromUserName(user) );
		 // JSONArray jsonA = JSONArray.fromObject(TwitterApiCalls.getTweetsFromUserName(user));
		  return TwitterApiCalls.getTweetsFromUserName(party);
		  
		//  return null;
	  }
	 
	 	 
	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	  }

}
