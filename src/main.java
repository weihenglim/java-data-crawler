import java.util.ArrayList;

public class main {
    public static void main(String args[]) {
        RedditCrawler reddit = new RedditCrawler();
        ArrayList<String> comments = reddit.findComments("February 12, 2019");

        for (String comment : comments) {
            System.out.println(comment);
        }

        
    }
}