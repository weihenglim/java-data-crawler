import java.util.ArrayList;
import java.util.Hashtable;

public class Analyzer {
    Hashtable<String, Integer> sentiments = new Hashtable<String, Integer>();

    public Analyzer() {
        String path = "../data/sentiments.csv";
        ArrayList<String> sentimentsArray = helper.readFile(path);

        for (String s : sentimentsArray) {
            String[] pair = s.split(",", 2);
            this.sentiments.putIfAbsent(pair[0], Integer.parseInt(pair[1]));
        }
    }
    
    public int getScore(String word) {
        return this.sentiments.getOrDefault(word, 0);
    }
}