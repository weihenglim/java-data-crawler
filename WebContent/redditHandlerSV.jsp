<%@ page language="java" import="analyzercrawler.RedditCrawler,analyzercrawler.helper"%>
<%
	String subreddit = request.getParameter("subreddit");
	String flair = request.getParameter("flair");
	String key = request.getParameter("keyword");
	String path = request.getParameter("path");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Reddit Crawler</title>
	</head>
	<body>
		<h1>Reddit Crawler</h1>
		<%
			if(subreddit == "" || key == "" || path == "")
			{
		%>
			<h2>Input Field (subreddit/search/save directory) is Empty!</h2>
			<p>Click <a href="redditcrawler.jsp">here</a> to try again.</p>
		<%
			}
			else
			{
		%>
			<h2>Data Successfully Crawled!</h2>
		<%
				/* Calls the RedditCrawler method with user's choice of subreddit, flair and directory to store data */
				analyzercrawler.RedditCrawler userInput = new analyzercrawler.RedditCrawler(subreddit, flair, "top", path);
				userInput.searchCommentsByKeyword(key);
		%>
			<p>Click <a href="analyzelocaldata.jsp">here</a> to view data.</p>
		<%
			}
		%>
		<br>
			<em>NOTE: If nothing is saved to your directory, it means that the file path is wrong</em>
		<div class="navbar">
			<br>
  			<a href="index.jsp" class="active">Home</a> | 
  			<a href="redditcrawler.jsp" class="active">Reddit Crawler</a> | 
  			<a href="twittercrawler.jsp" class="active">Twitter Crawler</a> | 
  			<a href="analyzelocaldata.jsp" class="active">Analyze Dataset</a> | 
  			<a href="analyzemultidata.jsp" class="active">Analyze Multiple Datasets</a>
		</div>
	</body>
</html>