<%@ page language="java" import="analyzercrawler.helper, analyzercrawler.jsphelper,java.util.*"%>
<%
	String key = request.getParameter("keyword");
	String path = request.getParameter("pathLink");
	String comments;
	String[] commentArr;
	commentArr = new String[1000];
	int index = 0;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Local Dataset Analyzer</title>
	</head>
	<body>
		<h1>Local Dataset Analyzer</h1>
		<%
			if(key == "")
			{
		%>
			<h2>Input Field is Empty!</h2>
			<form>
				<input type="button" value="Try Again" onclick="history.back()">
			</form>
		<%
			}
			else
			{
		%>
			<h2>Data Successfully Filtered from</h2>
			<p><% out.print(path); %></p>
			<h3>Information Filtered:</h3>
		<%
			if(path.contains("#"))
			{
				/* Calls the method TwitterCrawler to retrieve information from text file */
				comments = analyzercrawler.helper.readFile(path);
				/* Calls commentFilter method to remove unecessary characters and return as array with specified keyword */
				commentArr = analyzercrawler.jsphelper.commentFilterTweet(comments, key);
				/* totalComments returns the numer of tweets with keyword */
				out.print("<p><em>Keyword: " + key + " | Number of Tweets with keyword: " + analyzercrawler.jsphelper.totalKeyword(commentArr) + "</em></p>");
			}
			else
			{
				/* Calls the method RedditCrawler to retrieve information from text file */
				comments = analyzercrawler.helper.readFile(path);
				/* Calls commentFilter method to remove unecessary characters and return as array with specified keyword */
				commentArr = analyzercrawler.jsphelper.commentFilterReddit(comments, key);
				/* totalComments returns the numer of comments with keyword */
				out.print("<p><em>Keyword: " + key + " | Number of Comments with keyword: " + analyzercrawler.jsphelper.totalKeyword(commentArr) + "</em></p>");
			}
			/* To print out the information contained inside the text file */
			while(true)
			{
				index++;
				out.print(commentArr[index]);
				index++;
				out.print("<br>");
				if(commentArr[index] == null)
				{
					break;
				}
			}
		%>
			<p>------------------------------------------------------------------------------------------</p>
			<p><b>You have reached the end of the data. Click <a href="index.jsp">here</a> to exit.</b></p>
			<br>
			<em>NOTE: If nothing is displayed above, it means that the file path is wrong or the file is empty</em>
		<%
			}
		%>
		<div class="navbar">
			<br>
			<a href="index.jsp" class="active">Home</a> | 
  			<a href="redditcrawler.jsp" class="active">Reddit Crawler</a> | 
  			<a href="twittercrawler.jsp" class="active">Twitter Crawler</a> | 
  			<a href="analyzelocaldata.jsp" class="active">Analyze Single Dataset</a> | 
  			<a href=analyzemultidata.jsp>Compare Two Datasets</a> | 
  			<a href=analyzexcrawldata.jsp>Crawl & Analyze BitMEX Data</a>
		</div>
	</body>
</html>