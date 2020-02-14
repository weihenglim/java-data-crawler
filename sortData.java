package praw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
public class sortData {
	private String mm; 
	private String dd; 
	private String yy; 
	
	public sortData(String mm,String dd,String yy) {
	
		this.mm = mm;
		this.dd = dd;
		this.yy = yy;
	
	}
	public List<String> getSevendays() throws ParseException {
		   List<String> totalDates = new ArrayList<>();
		String data = mm+"/"+dd+"/"+yy;
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);// pattern formating for year 1st 
		Date date1=new SimpleDateFormat("MMMM/dd/yyyy").parse(data);  
		String enddate = simpleDateFormat.format(date1);
		//System.out.println(enddate);
		//Date date=new SimpleDateFormat("MMMM/dd/yyyy").parse(data);  
		//System.out.println("//////////////////////////////////////////////////");
	    LocalDate localdate = LocalDate.parse(enddate); 
	    LocalDate perviousdates = localdate.minusDays(6); 
		String startdate = perviousdates.toString();
		//   System.out.println(startdate);  // get the start date
		 
	
		   LocalDate start = LocalDate.parse(startdate);
		   LocalDate end = LocalDate.parse(enddate);
	
		   while (!start.isAfter(end)) {	//find the start and end date
			   String formattedDate = start.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"));
			   //String formattedDate = start.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)); // convert back to the date format day/month/year
		       totalDates.add(formattedDate);
		       start = start.plusDays(1);
		     //  System.out.println(formattedDate);  
		   }
	
		//   System.out.println(totalDates.size());  
		 return totalDates;
		 
	}
	
	
}
