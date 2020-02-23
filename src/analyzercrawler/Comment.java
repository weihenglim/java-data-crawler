package analyzercrawler;
import org.json.JSONObject;

public abstract class Comment implements JSONSerializable {
    private String text;
    private int score;

    public Comment(String text) { 
        /* self explanatory */
        this.text = new String(text);
        this.score = 0;
    }

    public Comment(String text, int score) {
        this.text = new String(text);
        this.score = score;
    }

    public JSONObject toJSON() { return new JSONObject(this); }

    public String toJSONString() { return this.toJSON().toString(); }

    public String getText() { return this.text; }
    
    public int getScore() { return this.score; }
   
    public void setText(String text) { this.text = text; }
    
    public void setScore(int score) { this.score = score; }
    
    public void addScore(int score) { this.score += score; }
}

class RedditComment extends Comment {
    public RedditComment(String text) {
        super(text);
    }

    public RedditComment(String text, int score) {
        super(text, score);
    }
}

class Tweet extends Comment {
    public Tweet(String text) {
        super(text);
    }

    public Tweet(String text, int score) {
        super(text, score);
    }
}

/*
    This JSONSerializable interface specifies that any object or class that 
    implements this interface can be converted to its JSON representation
*/
interface JSONSerializable {
    public abstract JSONObject toJSON();
    public abstract String toJSONString();
}