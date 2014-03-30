package a.service.proj;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SentiAnalysisCalls {
	
	
	static String alchemy_api_key="c4dcd11a5f381349e93d56d6f4e5dbd0f29cd576";
	static String uri="http://access.alchemyapi.com/calls/text/TextGetTextSentiment?apikey=c4dcd11a5f381349e93d56d6f4e5dbd0f29cd576&text=";

	
	
	public static int getSentiAnalysis(String text) throws ClientProtocolException, IOException{
		
		Client client = Client.create();
		 
		WebResource webResource = client
		   .resource(uri+text);
 
		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			String output = response.getEntity(String.class);
	 
			System.out.println("Output from Server .... \n");
			System.out.println(output);
			if(output.contains("positive")){
				System.out.println("positive");
				return 1;
					
			}else if(output.contains("neutral")){
				System.out.println("neutral");
				return 0;
					
			}else if(output.contains("negative")){
				System.out.println("negative");
				return -1;
					
			}
			
			return 1;		
	}

	public static void main(String[] args) throws ClientProtocolException, IOException{
		getSentiAnalysis(URLEncoder.encode("why only stones throw large rocks at @INCIndia for mismanaging India ", "UTF-8"));
		
	}

}

