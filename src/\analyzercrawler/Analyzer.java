package analyzercrawler;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Analyzer {

    /* Default search, last 7 days, keyword = bitcoin */
    public static String analyze() {
        Date now = new Date();
        Date weekAgo = now.minusDays(7);

        return analyze("bitcoin", now, weekAgo);
    }

    /* Search by user defined date range, keyword = bitcoin */
    public static String analyze(Date start, Date end) {
        return analyze("bitcoin", start, end);
    }
    
    /* The method itself */
    public static String analyze(String keyword, Date start, Date end) {
        /* Some error correction, if check if end/start date is after today */
        Date now = new Date();
        if (start.isAfter(now))
            start = new Date(now);

        if (end.isAfter(now)) 
            end = new Date(now);

        /* Swap the variable in case user enters a wrong range of date */
        if (start.isAfter(end)) {
            Date temp = new Date(start);
            start = end;
            end = temp;
        }

        /* Initialize the crawlers to get data */
        JSONArray output = new JSONArray();
        RedditCrawler reddit = new RedditCrawler();
        TwitterCrawler twitter = new TwitterCrawler();

        /* Retrieve prices from Bitmex */
        Bitmex bitmex = new Bitmex().startDate(start).endDate(end);
        ArrayList<Bitmex.Price> bitmexPrices = bitmex.getPrices();
        
        /* Get the date range to fetch reddit/twitter comments for */
        /* We want to fetch every comment between start & end date */
        Date[] dateRange = end.range(start);


        for (int i = 0; i < dateRange.length; i++) {
            JSONObject curr = new JSONObject();
            int redditScore = 0;
            int twitterScore = 0;

            /* Compute the total score for that day for Reddit */
            ArrayList<RedditComment> rComments = reddit.searchCommentsByKeyword(dateRange[i].toStringFor("reddit"));
            for (RedditComment rc : rComments) {
                redditScore += rc.getScore();
            }

            /* Compute the total score for that day for Twitter */
            ArrayList<Tweet> tweets = twitter.searchTweetsByKeyword(keyword, dateRange[i]);
            for (Tweet t : tweets) {
                twitterScore += t.getScore();
            }

            /* Generate the JSON object for serialization */
            curr.put("twitter_score", twitterScore);
            curr.put("reddit_score", redditScore);
            curr.put("date", dateRange[i].toString());
            curr.put("open", bitmexPrices.get(i).getOpen());
            curr.put("high", bitmexPrices.get(i).getHigh());
            curr.put("low", bitmexPrices.get(i).getLow());
            curr.put("close", bitmexPrices.get(i).getClose());
            output.put(curr);
        }

        String path = String.format("../data/output/analysis-%s-%s.txt", start.toString(), end.toString());
        helper.writeToFile(path, output.toString(3));

        return output.toString(3);
        
    }

}
    