package a.service.proj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.apache.http.client.ClientProtocolException;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApiCalls {

	static String consumer_key="PpCnb7TEziEcXzoRrpVgyg";
	static String consumer_secret="ySR8oPTxmnF75IwFlJN4zz12WcMOYgoNaPxkxbCqvc";
	static String access_token="89261937-BIF7heMBid1BsT9VUyMqSYqfCFrLEYn8abUrnBQcJ";
	static String access_token_secret="w80wq8XLz3LrxAG2y1WrgQz73YZCGyVkyOfURGa5NiJyJ";
	
	String twitter_uri="https://www.api.twitter.com/1.1/";
	
	public static HashMap<String,String> getTweetsFromUserName(String username) throws ClientProtocolException, IOException, TwitterException{
	
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumer_key)
		  .setOAuthConsumerSecret(consumer_secret)
		  .setOAuthAccessToken(access_token)
		  .setOAuthAccessTokenSecret(access_token_secret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Paging page = new Paging (1, 100);
		Twitter twitter = tf.getInstance();
		List<Status> statuses = twitter.getUserTimeline(username,page);
		HashMap<String,String> tweets=new HashMap();
		for (Status status : statuses) {
			System.out.println(status.getUser().getScreenName() + ":" +  status.getText()+":https://twitter.com/"+username+"/status/"+status.getId());
			tweets.put(status.getText(), ":https://twitter.com/"+username+"/status/"+status.getId());
		}
		
		return tweets;
		
	}
	
	public static HashMap<String,String> getTweetsFromHashTag(String hash) throws TwitterException{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumer_key)
		  .setOAuthConsumerSecret(consumer_secret)
		  .setOAuthAccessToken(access_token)
		  .setOAuthAccessTokenSecret(access_token_secret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		Paging page = new Paging (1, 100);
		System.out.println(hash);
		QueryResult result = twitter.search(new Query("#"+hash));
		List<Status> statuses = result.getTweets();
		HashMap<String,String> tweets=new HashMap();
	    for (Status status : statuses) {
	    	System.out.println(status.getUser().getName() + ":" +  status.getText()+":https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
			tweets.put(status.getText(), ":https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
		
	    }
		return tweets;
		
	}
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException, TwitterException{
		//getTweetsFromUserName("pyth0n_");
		getTweetsFromHashTag("aap");
	}
	
}
