

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class RedditCrawler {
    private String subreddit;
    private String flair;
    private String sort;

    /*
     * Default constructor
     */
    public RedditCrawler() {
        this.subreddit = "subreddit:BitcoinMarkets";
        this.flair = "flair:Daily Discussion";
        this.sort = "top";
    }

    /*
     * Parameterized constructor
     */
    public RedditCrawler(String subreddit, String flair, String sortBy) {
        this.subreddit = "subreddit:" + subreddit;
        this.flair = "flair:" + flair;
        this.sort = "top";
    }


    /*
     * At time of development, Reddit always use the class below to style
     * the element that contains the URL of the post we want. We look for it and
     * extract its href attribute to get its URL. Afterwards, get all comments inside the
     * thread and append all of them to an arraylist.
     */
    public ArrayList<String> findComments(String keyword) throws IOException {
        /* Initialize a new ArrayList to store comments */
        ArrayList<String> comments = new ArrayList<String>();

        /* File path to check/store comments */
        String path = String.format("../data/%s-%s.txt", this.subreddit, keyword);

        File commentsFile = new File(path);

        /* If comments is not already in cache, we should get it from the internet and store it in a file */
        if (!commentsFile.exists()) {
            /* Get the url to the post we search for by keyword */
            String url = String.format("https://www.reddit.com/search/?q=%s %s title:%s", this.subreddit, this.flair, keyword);
            Document body = helper.parseHttp(url);
            
            /* Go into the post so we can look at the comments */
            Element link = body.getElementsByClass("SQnoC3ObvgnGjWt90zD9Z _2INHSNB8V5eaWp4P0rY_mE").first();
            url = String.format("https://www.reddit.com/%s?sort=%s", link.attr("href"), this.sort);
            body = helper.parseHttp(url);
            
            /* Select all comment html elements */
            Elements commentElements = body.select(".entry > .usertext > .usertext-body > div > p");
            
            /* Loop through every comment elements to extract their text */
            /* Afterwards, add them to the arraylist */
            for (Element commentElement : commentElements) {
                String comment = commentElement.text();
                
                /* We don't need [deleted]/[removed] comments */
                if (!comment.contains("[removed]") && !comment.contains("[deleted]"))
                comments.add(comment);
            }

            /* Save it to a file under data folder */
            commentsFile.createNewFile();
            helper.writeFile(path, comments);
        }
        else {
            comments = helper.readFile(path);
        }
            
        return comments;
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