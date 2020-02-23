package analyzercrawler;
import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.ArrayList;
import java.io.File;


public class TwitterCrawler {
    private static final int pageCount = 100;
    private Twitter twitter;
    /* ##Introduced this to allow user to select where to save the file## */
    //private String path;// not needed 
    /*
     * Constructor
     */
    public TwitterCrawler() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        .setOAuthConsumerKey("xLoe9hZYpNDthiFEZ2ffGAuhf")
        .setOAuthConsumerSecret("hRzq2LJYvU7mnN5q9IJceiQvNnlMmzlxPBRFAzXXcwFBx6oRcm")
        .setOAuthAccessToken("826091506591150080-zhJRmDUg7XFsOe0Ku4YaQaB1M1Tcc8B")
        .setOAuthAccessTokenSecret("cLzg9NmHykEZbvoNNP1DvZKgcy5PSbtdjj7kxBGMY3ILU");

        this.twitter = new TwitterFactory(cb.build()).getInstance();
    }

    /*
     * Query twitter API by a given keyword
     */
    public ArrayList<Tweet> searchTweetsByKeyword(String keyword, Date date,String path) {
        /* Initializing variables to store tweets */
        List<Status> rawTweets;
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        JSONArray arr;

        /* Variables to store tweet sentiment score */
        SentimentMap sentimentMap = new SentimentMap();

        /* To filter Tweets by date, the API requires the target date and target date + 1 day*/
        Date[] dates = date.nextNDays(1);

        /* File path to check/store comments */
     
         path = String.format("%s\\%s-%s.txt", path, keyword, date.toStringFor("twitter"));

        File tweetsFile = new File(path); 

        /* If tweets is not already in cache, we should get it from the internet and store it in a file */
        if (!tweetsFile.exists()) {
            arr = new JSONArray();

            Query query = new Query(String.format("%s exclude:retweets", keyword));
            query.setSince(dates[0].toStringFor("twitter"));
            query.setUntil(dates[1].toStringFor("twitter"));
            query.setCount(TwitterCrawler.pageCount);
            query.setResultType(Query.RECENT);

            try {
                rawTweets = this.twitter.search(query).getTweets();

                for (Status s : rawTweets) {
                    String tweetText = helper.clean(s.getText());
                    int tweetScore = 0;

                    for (String word : tweetText.split("\\s")) {
                        tweetScore += sentimentMap.getOrDefault(word.toLowerCase(), 0);
                    }

                    Tweet t = new Tweet(tweetText, tweetScore);
                    tweets.add(t);
                    arr.put(t.toJSON());
                }
            }
            catch (TwitterException e) {
                e.printStackTrace();
            }

            /* Save it to a file under data folder */
            helper.writeToFile(path, arr.toString(5));
        }
        else {
            arr = new JSONArray(helper.readFile(path));

            for (int i = 0; i < arr.length(); i++) {
                JSONObject curr = arr.getJSONObject(i);
                tweets.add(new Tweet(curr.getString("text"), curr.getInt("score")));
            }
        }
            
        return tweets;
    }

    /*
     * Getters & Setters below, for encapsulation
     */
    public int getPageCount() {
        return TwitterCrawler.pageCount;
    }
}