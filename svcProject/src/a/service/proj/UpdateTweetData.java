package a.service.proj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;

import twitter4j.Status;
import twitter4j.TwitterException;

public class UpdateTweetData {
	
	/*Tweet_Data consists of party (name of the political party), tweet (the tweet about the party),
	 sentiment (flag to determine if the tweet is positive, negative or neutral, 0 for positive, 1 for 
	 negative and 2 for neutral) and tweet_from (flag to determine if the tweet is from junta or critic,
	 0 for critic and 1 for junta)  */
	
	static Map<String, List<String>> hashTagMapping = new HashMap<String, List<String>>();
	static List<String> userNameMapping = new ArrayList<String>();
	
	public static List<String> getPublicTweets(String partyName) throws TwitterException{
		List tweetList = new ArrayList<String>();
		String partyNameToLower = partyName.toLowerCase();
				
		List<String> hashTags = hashTagMapping.get(partyNameToLower);
		
		for (String hashTag : hashTags){
			HashMap<String, String> tweets = TwitterApiCalls.getTweetsFromHashTag(hashTag);
			//System.out.println("TESTING");
			Set tweetSet = tweets.keySet();
			List list = new ArrayList(tweetSet);
				for (int i=0;i<list.size();i++){
				tweetList.add(list.get(i));
				}
		}
		
		return tweetList;
	}
	
	public static List<String> getCriticTweets(String partyName) throws ClientProtocolException, IOException, TwitterException{
		List<String> tweetList = new ArrayList<String>();
		String partyNameToLower = partyName.toLowerCase();
		List<String> hashTags = hashTagMapping.get(partyNameToLower);
		
		for (String userName : userNameMapping){
			HashMap<String,String> tweets = TwitterApiCalls.getTweetsFromUserName(userName);
			Set<String> tweetSet = tweets.keySet();
			List<String> list = new ArrayList(tweetSet);
			for(String hashTag: hashTags){
				for(int i=0; i<list.size(); i++){
					String str = list.get(i);
					if(str.toLowerCase().contains(hashTag))
						tweetList.add(str);
				}
			}
		}
		
		return tweetList;
	}
	
	public List<String> getTweetsWithSentiment(String partyName, int sentiment, int tweet_from) throws TwitterException, ClientProtocolException, IOException{
		//Sentiment: 0 for positive, 1 for negative and 2 for neutral
		//tweet_from: 0 for public and 1 for junta
		List<String> tweetList = null;
		
		if(tweet_from == 0)
			tweetList = getPublicTweets(partyName);
		else
			tweetList = getCriticTweets(partyName);
		
		List<String> list = new ArrayList();
		
		for (String tweet : tweetList){
			//Call the Alchemy API, if the sentiment of the tweet matches with the 
			//required sentiment, add it to the list
		}
		
		return list;
	}
	
	public static void updateHashTagMap(){
		List<String> values = new ArrayList<String>();
		values.add("Modi");
		values.add("bjp");
		values.add("NaMo");
		values.add("liesofmodigovt");
		hashTagMapping.put("bjp", values);
		
		values = new ArrayList<String>();
		values.add("congress");
		values.add("abkibaarmodikihaar");
		values.add("rahulgandhi");
		values.add("soniagandhi");
		hashTagMapping.put("congress", values);
		
		values = new ArrayList<String>();
		values.add("aap");
		values.add("kejriwalinvaranasi");
		values.add("Kejriwal");
		values.add("KrantiKariAAPGovt");
		hashTagMapping.put("aap", values);
	
	}
	
	public static void updateUserNameMapping(){
		userNameMapping.add("sardesairajdeep");
		userNameMapping.add("aajtaknews");
		userNameMapping.add("StarNews");
		userNameMapping.add("ZeeNews");
		userNameMapping.add("ndtv");
	}
	
	public static int checkSentiment(Status status){
		return 1;
	}
	
	public static void main(String args[]) throws TwitterException, ClientProtocolException, IOException {
		updateHashTagMap();
		updateUserNameMapping();
		System.out.println(getCriticTweets("bjp"));
	}
	
}
