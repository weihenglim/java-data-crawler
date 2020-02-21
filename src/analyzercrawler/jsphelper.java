package analyzercrawler;

import java.util.*;

/* 
	This class contains all the miscellaneous methods, variables & fields that will be used in JSP environment
*/

public class jsphelper
{
	public static String commentReplacer(String comments)
	{
		comments = comments.replace("[","");
		comments = comments.replace("]","");
		comments = comments.replace("{","");
		comments = comments.replace("},","<br>");
		return comments;
	}
	
	public static String[] commentFilter(String comments, String key)
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
}
