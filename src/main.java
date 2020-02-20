import java.io.IOException;
import java.util.ArrayList;

public class main {
    public static void main(String args[]) throws IOException {
    	TwitterCrawler test = new TwitterCrawler();
    	System.out.println(test.findComments());
    }
}