import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*; 


public class runme {
    public static void main(String args[]) {

        RedditCrawler rc = new  RedditCrawler();

        ArrayList<RedditComment> comments = rc.searchCommentsByKeyword("16 February, 2020");

        for (Comment c : comments) {
            System.out.println(c.getText());
        }
    }
}

