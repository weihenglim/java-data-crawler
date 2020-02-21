/* import java.util.ArrayList;
import java.util.Hashtable;

public class Analyzer {
    private Hashtable<String, Integer> sentimentTable = new Hashtable<String, Integer>();

    public Analyzer() {
        ArrayList<String> sentimentsArray = helper.readFile(helper.SentimentsFilePath);

        for (String s : sentimentsArray) {
            String[] pair = s.split(",", 2);
            this.sentimentTable.putIfAbsent(pair[0], Integer.parseInt(pair[1]));
        }
    }

    public Analyzer(String path) {
        ArrayList<String> sentimentsArray = helper.readFile(path);

        for (String s : sentimentsArray) {
            String[] pair = s.split(",", 2);
            this.sentimentTable.putIfAbsent(pair[0], Integer.parseInt(pair[1]));
        }
    }

    public void calcScore(Comment comment) {
        String[] words = comment.getText().split(" ");

        for (String word : words) {
            int score = this.sentimentTable.getOrDefault(word, 0);
            comment.setScore(comment.getScore() + score);
        }
    }
} */