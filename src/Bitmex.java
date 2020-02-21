import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* We using builder design for this class */
public class Bitmex {

    public static final String BitmexURL = "https://www.bitmex.com/api/v1";

    private String symbol = "XBTUSD";
    private String binSize = "1d";
    private Date endDate = new Date();
    private Date startDate = endDate.minusDays(7);
    private int count = 500;
    private boolean reverse = true;
    private boolean partial = false;

    /* We don't need a constructor really, all fields are pre-defined */
    public Bitmex() {} 

    public Bitmex symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Bitmex binSize(String binSize) {
        this.binSize = binSize;
        return this;
    }

    public Bitmex startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Bitmex endDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Bitmex count(int count) {
        this.count = count;
        return this;
    }

    public Bitmex reverse(boolean reverse) {
        this.reverse = reverse;
        return this;
    }

    public Bitmex partial(boolean partial) {
        this.partial = partial;
        return this;
    }

    public ArrayList<Bitmex.Price> getPrices() {
        ArrayList<Bitmex.Price> prices = new ArrayList<Bitmex.Price>();

        String startTime = this.startDate.toStringFor("bitmex");
        String endTime = this.endDate.toStringFor("bitmex");
        String url = "%s/trade/bucketed?binSize=%s&partial=%s&symbol=%s&count=%s&reverse=%s&startTime=%s&endTime=%s";
        url = String.format(url, Bitmex.BitmexURL, this.binSize, this.partial, this.symbol, this.count, this.reverse, startTime, endTime);

        JSONArray result = new JSONArray(helper.getJson(url));

        for (int i = 0; i < result.length(); i++) {
            Price p = new Price();
            p.setDate(new Date(result.getJSONObject(i).getString("timestamp")));
            p.setSymbol(result.getJSONObject(i).getString("symbol"));
            p.setLow(result.getJSONObject(i).getDouble("low"));
            p.setOpen(result.getJSONObject(i).getDouble("open"));
            p.setHigh(result.getJSONObject(i).getDouble("high"));
            p.setClose( result.getJSONObject(i).getDouble("close"));
            p.setVolume(result.getJSONObject(i).getInt("volume"));

            prices.add(p);
        }

        return prices;
    }

    class Price {
        private Date date;
        private String symbol;
        private double open;
        private double high;
        private double low;
        private double close;
        private int volume;

        public Price() {}

        public String toJson() {
            JSONObject jobj = new JSONObject();
            jobj.put("timestamp", this.date.toStringFor("bitmex"));
            jobj.put("symbol", this.symbol);
            jobj.put("open", this.open);
            jobj.put("high", this.high);
            jobj.put("low", this.low);
            jobj.put("close", this.close);
            jobj.put("volume", this.volume);

            return jobj.toString();
        }

        public double getClose() { return this.close; }

        public void setClose(double close) { this.close = close; }

        public double getHigh() { return this.high; }

        public void setHigh(double high) { this.high = high; }

        public double getLow() { return this.low; }

        public void setLow(double low) { this.low = low; }

        public double getOpen() { return this.open; }

        public void setOpen(double open) { this.open = open; }

        public int getVolume() { return this.volume; }

        public void setVolume(int volume) { this.volume = volume; }

        public String getSymbol() { return this.symbol; }

        public void setSymbol(String symbol) { this.symbol = symbol; }

        public Date getDate() { return this.date; }

        public void setDate(Date date) { this.date = new Date(date); }
    }

    class Message extends Comment {
        
        private int id;
        private String user;

        public Message(int id, String user, String text) {
            super(text);
            this.id = id;
            this.user = user;
        }

        public int getId() { return this.id; }

        public String getUser() { return this.user; }
    }

}