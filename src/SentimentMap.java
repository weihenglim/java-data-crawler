public class SentimentMap extends java.util.HashMap<String, Integer> {

    /* As the parent clas java.util.HashMap implements the Serializable interface, we need to include this line */
    private final static long serialVersionUID = 1L;


    public SentimentMap() {
        /* Read sentiments.csv file and get all word-score pair */
        String sentimentRaw = helper.readFile("../data/sentiments.csv");

        for (String s : sentimentRaw.split("\\n")) {
            String[] pair = s.split(",");
            this.putIfAbsent(pair[0], Integer.parseInt(pair[1]));
        }    
    }
}