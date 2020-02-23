import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* 
    No known updated Java library for Bitmex API hence we have to build our own.
    We using builder design pattern for this class.
    Not all API calls will be implemented for this project, we don't need all of them.

    Default settings are as follows:
    -> Past 7 days prices
    -> Daily timeframe
    -> Prices for XBTUSD (Bitcoin Inverese Perpetual Swaps)
    -> Sorted in ascending order (by time)

    Example usage:

        * Create a new Bitmex object *
        Bitmex bm = new Bitmex();

        * To get prices with default settings *
        ArrayList<Bitmex.Price> prices = bm.getPrices();

        * To get prices for last 365 days *
        Date today = new Date();
        Date lastYear = today.minusDays(365);
        prices = bm.endDate(today).startDate(lastYear).getPrices();

        * Works with RFC3339 timestamp as well *
        prices = bm.endDate("2020-02-22T15:54:00.000Z").startDate("2020-02-22T15:54:00.000Z").getPrices();

        * To get prices for different symbol (eg. ETHUSD)
        prices = bm.symbol("ETHUSD").getPrices();
*/
public class Bitmex {

    /* URL to Bitmex's API endpoint */
    public static final String BitmexURL = "https://www.bitmex.com/api/v1";

    private String symbol = "XBTUSD";
    private String binSize = "1d";
    private Date endDate = new Date();
    private Date startDate = endDate.minusDays(7);
    private int count = 500;
    private boolean reverse = true;
    private boolean partial = true;

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
        if (startDate.isAfter(this.endDate)) {
            this.startDate = this.endDate;
            this.endDate = startDate;
        } else {
            this.startDate = startDate;
        }
        return this;
    }

    public Bitmex startDate(String startDate) {
        this.startDate = new Date(startDate);
        return this;
    }

    public Bitmex endDate(Date endDate) {
        if (endDate.isBefore(this.startDate)) {
            this.endDate = this.startDate;
            this.startDate = endDate;
        } else {
            this.endDate = endDate;
        }
        return this;
    }

    public Bitmex endDate(String endDate) {
        this.endDate = new Date(endDate);
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
        String path = String.format("../data/Bitmex/%s/%s-%s.txt", this.symbol, startTime, endTime);
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

            prices.add(p);
        }

        helper.writeToFile(path, result.toString(3));

        return prices;
    }

    public class Price implements JSONSerializable {
        private Date date;
        private String symbol;
        private double open;
        private double high;
        private double low;
        private double close;

        public Price() {}

        public JSONObject toJSON() { return new JSONObject(this); }

        public String toJSONString() { return this.toJSON().toString(); }

        public double getClose() { return this.close; }

        public void setClose(double close) { this.close = close; }

        public double getHigh() { return this.high; }

        public void setHigh(double high) { this.high = high; }

        public double getLow() { return this.low; }

        public void setLow(double low) { this.low = low; }

        public double getOpen() { return this.open; }

        public void setOpen(double open) { this.open = open; }

        public String getSymbol() { return this.symbol; }

        public void setSymbol(String symbol) { this.symbol = symbol; }

        public Date getDate() { return this.date; }

        public void setDate(Date date) { this.date = new Date(date); }
    }

    public class Message extends Comment {
        
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