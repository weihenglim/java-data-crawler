package analyzercrawler;

/* 
	This class contains all the miscellaneous methods, variables & fields that will be used in JSP environment
*/

public class jsphelper
{
	/*
	 * 	Remove unnecessary characters from the String
	 */
	public static String commentReplacerReddit(String comments)
	{
		comments = comments.replace("[","");
		comments = comments.replace("]","");
		comments = comments.replace("{","");
		comments = comments.replace("},","<br>");
		comments = comments.replace("}","");
		return comments;
	}
	public static String commentReplacerTweet(String comments)
	{
		comments = comments.replace("[","");
		comments = comments.replace("]","");
		comments = comments.replace("\"","");
		comments = comments.replace("{","");
		comments = comments.replace("\\","");
		comments = comments.replace("},","<br>");
		comments = comments.replace(",","&nbsp&nbsp&nbsp&nbsp&nbsp");
		return comments;
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
				newArr[index] = "&nbsp&nbsp&nbsp&nbsp&nbsp" + commentArr[i];
				index += 1;
			}
		}
		return newArr;
	}
	/*
	 * 	Returns the total number of comments/tweets
	 */
	public static String totalComments(String comment, String index)
	{
		String[] commentArr;
		commentArr = new String[1000];
		comment = comment.replace("<br>",",");
		commentArr = comment.split(",");
		int amount = 0;
		amount = commentArr.length;
		if(index == "R")
		{
			return String.valueOf(amount / 2);
		}
		else
		{
			return String.valueOf(amount);
		}
	}
	/*
	 * 	Returns the total number of comments/tweets with keyword
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