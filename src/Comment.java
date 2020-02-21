import org.json.JSONObject;

public abstract class Comment {
    private String text;
    private int score = 0;

    public Comment(String text) { 
        this.text = new String(text);
    }

    public JSONObject toJSON() { return new JSONObject(this); }

    public String getText() { return this.text; }
    
    public int getScore() { return this.score; }

    public void setText(String text) { this.text = text; }

    public void setScore(int score) { this.score = score; }
}

class RedditComment extends Comment {
    public RedditComment(String text) {
        super(text);
    }
}

class Tweet extends Comment {
    public Tweet(String text) {
        super(text);
    }
}