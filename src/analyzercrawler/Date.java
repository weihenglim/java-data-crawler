package analyzercrawler;
import java.time.LocalDate;
import java.time.Month;

/* We would have extended from the java.util.date class however it is deprecated */
public class Date {

    private int day;
    private int year;
    private Month month;
    
    /* Constructor for date object of current date */
    public Date() {
        this.day = LocalDate.now().getDayOfMonth();
        this.year = LocalDate.now().getYear();
        this.month = LocalDate.now().getMonth();
    }

    /* Takes in a RFC3339 timestamp */
    public Date(String date) {
        String[] dateSplit = date.substring(0, 10).split("-");
        this.year = Integer.parseInt(dateSplit[0]);
        this.day = Integer.parseInt(dateSplit[2]);
        this.month = Month.of(Integer.parseInt(dateSplit[1]));
    }

    /* Self-explanatory constructor */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = Month.of(month);
        this.day = day;
    }

    /* Converts a java.time.LocalDate object to our Date object */
    public Date(LocalDate date) {
        this.year = date.getYear();
        this.month = date.getMonth();
        this.day = date.getDayOfMonth();
    }

    /* Copy constructor */ 
    public Date(Date date) {
        this.day = Integer.valueOf(date.getDay());
        this.month = date.getMonth();
        this.year = Integer.valueOf(date.getYear());
    }

    public String toStringFor(String format) {
        switch (format) {
            case "twitter":
                return String.format("%04d-%02d-%02d", this.year, this.month.getValue(), this.day);

            case "bitmex":
                return String.format("%04d-%02d-%02d", this.year, this.month.getValue(), this.day);
               
            case "reddit":
                return String.format("%s %s, %s", this.day, helper.toCamelCase(this.month.toString()), this.year);
                
            default:
                return String.format("%02d/%02d/%04d", this.day, this.month.getValue(), this.year);
        }
    }

    public Date[] lastNDays(int n) {
        Date[] dates = new Date[n + 1];
        dates[0] = this;

        for (int i = 1; i <= n; i++) {
            LocalDate temp = LocalDate.of(this.year, this.month, this.day).minusDays(i);
            dates[i] = new Date(temp);
        }

        return dates;
    }

    public Date[] nextNDays(int n) {
        Date[] dates = new Date[n + 1];
        dates[0] = this;

        for (int i = 1; i <= n; i++) {
            LocalDate temp = LocalDate.of(this.year, this.month, this.day).plusDays(i);
            dates[i] = new Date(temp);
        }

        return dates;
    }

    public Date minusDays(int n) {
        LocalDate temp = LocalDate.of(this.year, this.month, this.day).minusDays(n);
        return new Date(temp);
    }

    public Date plusDays(int n) {
        LocalDate temp = LocalDate.of(this.year, this.month, this.day).plusDays(n);
        return new Date(temp);
    }

    /* 
        Getter & Setters below
    */
    public int getDay() {
        return this.day;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonthNum() {
        return this.month.getValue();
    }

    public String getMonthName() {
        return this.month.toString();
    }

    public Month getMonth() {
        return this.month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int monthNum) {
        this.month = Month.of(monthNum);
    }

    public void setMonth(String monthName) {
        this.month = Month.valueOf(monthName.toUpperCase());
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}