import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.io.File;
import org.json.JSONArray;

import java.io.*;

public class RedditCrawler {
    private static final String RedditCommentClass = "SQnoC3ObvgnGjWt90zD9Z _2INHSNB8V5eaWp4P0rY_mE";
    private String subreddit;
    private String flair;
    private String sort;

    /*
     * Default constructor
     */
    public RedditCrawler() {
        this.subreddit = "BitcoinMarkets";
        this.flair = "flair:Daily Discussion";
        this.sort = "top";
    }

    /*
     * Parameterized constructor
     */
    public RedditCrawler(String subreddit, String flair, String sortBy) {
        this.subreddit = subreddit;
        this.flair = "flair:" + flair;
        this.sort = "top";
    }


    /*
     * At time of development, Reddit always use a specific html class to style
     * the element that contains the URL of the post we want. We look for it and
     * extract its href attribute to get its URL. Afterwards, get all comments inside the
     * thread and append all of them to an arraylist.
     */
    public ArrayList<RedditComment> searchCommentsByKeyword(String keyword) {

        /* Initialize a new ArrayList to store comments */
        ArrayList<RedditComment> redditComments = new ArrayList<RedditComment>();
        JSONArray arr;

        /* File path to check/store comments */
        String path = String.format("../data/Reddit/%s-%s.txt", this.subreddit, keyword);
        File commentsFile = new File(path);

        /* If comments is not already in cache, we should get it from the internet and store it in a file */
        if (!commentsFile.exists()) {

            arr = new JSONArray();

            /* Get the url to the post we search for by keyword */
            String url = String.format("https://www.reddit.com/search/?q=subreddit:%s %s title:%s", this.subreddit, this.flair, keyword);
            Document body = helper.parseHttp(url);
            
            /* Go into the post so we can look at the comments */
            Element link = body.getElementsByClass(RedditCrawler.RedditCommentClass).first();
            url = String.format("https://www.reddit.com/%s?sort=%s", link.attr("href"), this.sort);
            body = helper.parseHttp(url);
            
            /* Select all comment html elements */
            Elements commentElements = body.select(".entry > .usertext > .usertext-body > div > p");
            
            /*  
                Loop through every comment elements to extract their text
                Afterwards, add them to the arraylist
            */
            for (Element commentElement : commentElements) {
                String commentText = commentElement.text();
                
                /* We don't need [deleted] or [removed] comments */
                if (!commentText.contains("[removed]") && !commentText.contains("[deleted]")) {
                    /* 
                        Create a new redditComment object & clean its 
                        text before adding it to the arraylist
                    */
                    RedditComment redditComment = new RedditComment(commentText);
                    helper.cleanComment(redditComment);
                    redditComments.add(redditComment);
                    arr.put(redditComment.toJSON());
                }
            }

            /* Save it to a file under data folder */
            helper.writeToFile(path, arr.toString(3));
        }
        else {
            arr = new JSONArray(helper.readFile(path));

            for (int i = 0; i < arr.length(); i++) {
                redditComments.add(new RedditComment(arr.getJSONObject(i).getString("text")));
            }
        }
            
        return redditComments;
    }

    /*
     * Getters & Setters below, for encapsulation
     */
    public String getSubreddit() {
        return this.subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getFlair() {
        return this.flair;
    }

    public void setFlair(String flair) {
        this.flair = flair;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}