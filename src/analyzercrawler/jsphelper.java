package analyzercrawler;

import java.util.Arrays;

/* 
	This class contains all the miscellaneous methods, variables & fields that will be used in JSP environment
*/

public class jsphelper
{
	/*
	 * 	Filter the String and return as an Array
	 */
	public static String[] commentFilterReddit(String comments)
	{
		String[] commentArr;
		commentArr = new String[1000];
		comments = comments.replace("[","");
		comments = comments.replace("{","");
		comments = comments.replace("}","");
		commentArr = comments.split(",");
		return commentArr;
	}
	public static String[] commentFilterTweet(String comments)
	{
		String[] commentArr;
		commentArr = new String[1000];
		comments = comments.replace("[","");
		comments = comments.replace("{","");
		comments = comments.replace("}","");
		commentArr = comments.split(",");
		return commentArr;
	}
	/*
	 * 	Filter the String and return as an Array based on the keyword
	 */
	public static String[] commentFilterReddit(String comments, String key)
	{
		String[] newArr, commentArr;
		newArr = new String[1000];
		commentArr = new String[1000];
		int index = 0;
		comments = comments.replace("[","");
		comments = comments.replace("{","");
		comments = comments.replace("}","");
		commentArr = comments.split(",");
		for(int i = 0; i < commentArr.length; i++)
		{
			if(commentArr[i].contains(key))
			{
				newArr[index] = commentArr[i-1];
				index += 1;
				newArr[index] = commentArr[i];
				index += 1;
			}
		}
		return newArr;
	}
	public static String[] commentFilterTweet(String comments, String key)
	{
		String[] newArr, commentArr;
		newArr = new String[1000];
		commentArr = new String[1000];
		int index = 0;
		comments = comments.replace("[","");
		comments = comments.replace("]","");
		comments = comments.replace("\"","");
		comments = comments.replace("{","");
		comments = comments.replace("\\","");
		comments = comments.replace("}","");
		commentArr = comments.split(",");
		for(int i = 0; i < commentArr.length; i++)
		{
			if(commentArr[i].contains(key))
			{
				newArr[index] = commentArr[i-1];
				index += 1;
				newArr[index] = commentArr[i];
				index += 1;
			}
		}
		return newArr;
	}
	/*
	 * 	Returns the total number of comments/tweets
	 */
	public static String totalKeyword(String[] commentArr)
	{
		int amount = 0;
		for(int i = 0; i < commentArr.length; i++)
		{
			if(commentArr[i] == null)
			{
				continue;
			}
			else
			{
				amount++;
			}
		}
		return String.valueOf(amount / 2);
	}
}