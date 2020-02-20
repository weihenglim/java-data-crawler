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
			<h3>Results:</h3>
		<%
				analyzercrawler.RedditCrawler userInput = new analyzercrawler.RedditCrawler(subreddit, flair, "top", analyzercrawler.helper.pathRewrite(path));
				out.print(userInput.findComments(key));
		%>
			<p>You have reached the end of the data. Click <a href="index.jsp">here</a> to return.</p>
		<%
			}
		%>
		<div class="navbar">
			<br>
  			<a href="index.jsp" class="active">Home</a> |
  			<a href="twittercrawler.jsp" class="active">Twitter Crawler</a> |
  			<a href="analyzelocaldata.jsp" class="active">Analyze Data Set</a> |
  			<a href="" class="active">Analyze BitCoin Market</a> |
		</div>
	</body>
</html>