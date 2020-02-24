package analyzercrawler;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/* We would have extended from the java.util.date class however it is deprecated */
/* We also would have extended from the java.time.LocalDate class however it is a final class */
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

    /* 
    Description:
        Returns a string representation of the Date object

    Parameters:
        format - Specifies the format of Date to output
    */
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

    /* 
    Description:
        Returns a string representation of the Date object in the form yyyy-mm-dd
    */
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", this.year, this.month.getValue(), this.day);
    }

    /* 
    Description:
        Returns an array of Date objects containing all Dates between this 
        Date  and this - n days Date 
        Index 0 will be this Date object, Index 1 will be this Date - 1 day 
        and so on. Last element of the array will be this Date - n days.

    Parameters:
        n - number of days to forward count
    */
    public Date[] lastNDays(int n) {
        Date[] dates = new Date[n + 1];
        dates[0] = this;

        for (int i = 1; i <= n; i++) {
            dates[i] = new Date(this.toLocalDate().minusDays(i));
        }

        return dates;
    }

    /* 
    Description:
        Returns an array of Date objects containing all Dates between this 
        Date  and this + n days Date 
        Index 0 will be this Date object, Index 1 will be this Date + 1 day
        and so on. Last element of the array will be this Date + n days.

    Parameters:
        n - number of days to forward count
    */
    public Date[] nextNDays(int n) {
        Date[] dates = new Date[n + 1];
        dates[0] = this;

        for (int i = 1; i <= n; i++) {
            dates[i] = new Date(this.toLocalDate().plusDays(i));
        }

        return dates;
    }

    /* 
    Description:
        Returns a Date object with n days subtracted 

    Parameters:
        n - number of days to subtract
    */
    public Date minusDays(int n) {
        return new Date(this.toLocalDate().minusDays(n));
    }

    /* 
    Description:
        Returns a Date object with n days added

    Parameters:
        n - number of days to add
    */
    public Date plusDays(int n) {
        return new Date(this.toLocalDate().plusDays(n));
    }

    /* 
    Description:
        Checks if this other date is after this date

    Parameters:
        other - the other date to compare to
    */
    public boolean isAfter(Date other) {
        return this.toLocalDate().isAfter(other.toLocalDate());
    }

    /* 
    Description:
        Checks if this other date is before this date

    Parameters:
        other - the other date to compare to
    */
    public boolean isBefore(Date other) {
        return this.toLocalDate().isBefore(other.toLocalDate());
    }

    /* 
    Description:
        Checks if two dates are the same

    Parameters:
        other - the other date to compare to
    */
    public boolean isEqual(Date other) {
        return ((this.getDay() == other.getDay()) && (this.getMonthNum() == other.getMonthNum()) && (this.getYear() == other.getYear()));
    }

    /* 
    Description:
        Returns an array of Date object consisting of all Dates between this date and other date inclusive
        This date object should be the most recent date and is at index 0

    Parameters:
        other - other boundary of date to compute
    */
    public Date[] range(Date other) {
        int diffDays = (int) other.toLocalDate().until(this.toLocalDate(), ChronoUnit.DAYS);
        return this.lastNDays(diffDays);
    }

    /* 
    Description:
        Returns a java.time.LocalDate representation of this Date object
    */
    public LocalDate toLocalDate() {
         return LocalDate.of(this.year, this.month, this.day);
    }

    /* 
    ** Getter & Setters below
    */
    public int getDay() {
        return this.day;
    }

    public int getYear() {
        return this.year;
    }

    /* Returns month number (eg. 1 for January, 2 for February ...) */
    public int getMonthNum() {
        return this.month.getValue();
    }

    /* Returns full english name of the month (eg. January, February) */
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

    /* Overloaded setMonth method to work with various representations of month*/
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