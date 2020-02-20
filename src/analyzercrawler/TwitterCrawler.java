package analyzercrawler;

import twitter4j.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TwitterCrawler
{
	private static final int PAGE_COUNT = 100; 	// Number of tweets to search (each page contains 100 tweets)
	private static String QUERY;				// The search keyword
	private static String START_DATE;			// Starting date range for the search
	private static String END_DATE;				// Ending date range for the search
	private String userpath;
	
	/*
     * Default constructor
     */
    public TwitterCrawler()
    {
        TwitterCrawler.QUERY = "#bitcoin";
        TwitterCrawler.START_DATE = "2020-02-17";
        TwitterCrawler.END_DATE = "2020-02-17";
    }
	
    /*
     * Parameterized constructor
     */
    public TwitterCrawler(String keyword, String date, String path)
    {
    	TwitterCrawler.QUERY = keyword;
        TwitterCrawler.START_DATE = date;
        TwitterCrawler.END_DATE = date;
        this.userpath = path;
    }
    
    public List<String> findComments()
    {
		int index = 0;
		List<String> output = new ArrayList<String>();
		Twitter twitter = new TwitterFactory().getInstance();
		Query query = new Query(QUERY + " +exclude:retweets");
		query.setSince(START_DATE);
		query.setUntil(END_DATE);
		query.setCount(PAGE_COUNT);
		query.setResultType(Query.RECENT);
		QueryResult result = null;
		
		for (int i = 0; i < PAGE_COUNT; ++i)
		{
			try
			{
				result = twitter.search(query);
			}
			catch (TwitterException te)
			{
				te.printStackTrace();
			}
			List<Status> tweets = result.getTweets();
			for (Status tweet : tweets)
			{
				index++;
				String str = "@" + tweet.getUser().getScreenName() + " - " + tweet.getText();
				output.add(str);
			}
			query = result.nextQuery();
		}
		
		/* Save it to a file under data folder */
		Path file = Paths.get("%s/%s_%s.txt", this.userpath, TwitterCrawler.QUERY, TwitterCrawler.START_DATE);
		try
		{
			Files.write(file, output, StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return output;
	}
}
