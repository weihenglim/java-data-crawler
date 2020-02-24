package analyzercrawler;

import java.util.Arrays;

/* 
	This class contains all the miscellaneous methods, variables & fields that will be used for the BitMEX Market Analysis
*/

public class marketanalyzerHelper
{
	private String[] analysisArr = new String[50];
	/*
	 * 	Default Constructor
	 */
	public marketanalyzerHelper()
	{}
	/*
	 * 	Main Constructor
	 */
	public marketanalyzerHelper(String analysis)
	{
		
		analysis = analysis.replace("[","");
		analysis = analysis.replace("]","");
		analysis = analysis.replace("{","");
		analysis = analysis.replace("}","");
		this.analysisArr = analysis.split(",");
	}
	/*
	 * 	Below are the methods that return the values required
	 */
	public Integer twitterScore(int day)
	{
		String placeholder = this.analysisArr[0 + (day * 7)].replaceAll("\\D+","");
		return Integer.parseInt(placeholder);
	}
	public String date(int day)
	{
		return this.analysisArr[1 + (day * 7)];
	}
	public Double highStock(int day)
	{
		String placeholder = this.analysisArr[2 + (day * 7)].replaceAll("[^0-9?!\\.]","");
		return Double.parseDouble(placeholder);
	}
	public Double lowStock(int day)
	{
		String placeholder = this.analysisArr[3 + (day * 7)].replaceAll("[^0-9?!\\.]","");
		return Double.parseDouble(placeholder);
	}
	public Double closeStock(int day)
	{
		String placeholder = this.analysisArr[4 + (day * 7)].replaceAll("[^0-9?!\\.]","");
		return Double.parseDouble(placeholder);
	}
	public Integer redditScore(int day)
	{
		String placeholder = this.analysisArr[5 + (day * 7)].replaceAll("\\D+","");
		return Integer.parseInt(placeholder);
	}
	public Double openStock(int day)
	{
		String placeholder = this.analysisArr[6 + (day * 7)].replaceAll("[^0-9?!\\.]","");
		return Double.parseDouble(placeholder);
	}
}