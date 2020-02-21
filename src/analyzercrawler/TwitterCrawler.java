package analyzercrawler;
import twitter4j.JSONArray;
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
    private int pageCount;
    private Twitter twitter;

    /*
     * Constructor
     */
    public TwitterCrawler(int pageCount) {
        this.pageCount = pageCount;

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
    public ArrayList<Tweet> searchTweetsByKeyword(String keyword, Date date) {
        /* Initializing variables to store tweets */
        List<Status> rawTweets;
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        JSONArray arr;
        Date[] dates = date.nextNDays(1);

        /* File path to check/store comments */
        String path = String.format("../data/Twitter/%s-%s.txt", keyword, date.toStringFor("twitter"));

        File tweetsFile = new File(path);

        /* If tweets is not already in cache, we should get it from the internet and store it in a file */
        if (!tweetsFile.exists()) {
            arr = new JSONArray();

            Query query = new Query(String.format("%s exclude:retweets", keyword));
            query.setSince(dates[0].toStringFor("twitter"));
            query.setUntil(dates[1].toStringFor("twitter"));
            query.setCount(this.pageCount);
            query.setResultType(Query.RECENT);

            try {
                rawTweets = this.twitter.search(query).getTweets();

                for (Status s : rawTweets) {
                    Tweet t = new Tweet(s.getText());
                    helper.cleanComment(t);
                    tweets.add(t);
                    arr.put(t.toJSON());
                }
            }
            catch (TwitterException e) {
                e.printStackTrace();
            }

            /* Save it to a file under data folder */
            helper.writeToFile(path, arr.toString(3));
        }
        else {
            arr = new JSONArray(helper.readFile(path));

            for (int i = 0; i < arr.length(); i++) {
                tweets.add(new Tweet(arr.getJSONObject(i).getString("text")));
            }
        }
            
        return tweets;
    }

    /*
     * Getters & Setters below, for encapsulation
     */
    public int getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}