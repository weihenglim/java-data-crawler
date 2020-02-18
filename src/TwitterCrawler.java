import twitter4j.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TwitterCrawler {
	static final int PAGE_COUNT = 100; 				// Number of tweets to search (each page contains 100 tweets)
	static final String QUERY = "#bitcoin";			// The search keyword
	static final String START_DATE = "2020-02-17";	// Starting date range for the search
	static final String END_DATE = "2020-02-18";	// Ending date range for the search
	
	public static void main(String[] args) throws IOException {
		int index = 0;
		List<String> output = new ArrayList<String>();
		Twitter twitter = new TwitterFactory().getInstance();
		Query query = new Query(QUERY + " +exclude:retweets");
		query.setSince(START_DATE);
		query.setUntil(END_DATE);
		query.setCount(100);
		query.setResultType(Query.RECENT);
		QueryResult result = null;
		
		for (int i = 0; i < PAGE_COUNT; ++i) {
			try {
				result = twitter.search(query);

			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to search tweets: " + te.getMessage());
				System.exit(-1);
			}
			List<Status> tweets = result.getTweets();
			for (Status tweet : tweets) {
				index++;
//				String str = index + ". [" + tweet.getCreatedAt() + "] @" + tweet.getUser().getScreenName() + " - " + tweet.getText();
				String str = "@" + tweet.getUser().getScreenName() + " - " + tweet.getText();
				System.out.println(str);
				output.add(str);
			}
			query = result.nextQuery();
		}
		
		Path file = Paths.get("data\\TwitterCrawl " + START_DATE + ".txt");
		Files.write(file, output, StandardCharsets.UTF_8);
		
		System.exit(0);
	}
}
