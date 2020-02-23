<%@ page language="java" import="analyzercrawler.TwitterCrawler,analyzercrawler.Date"%>
<%
	String date = request.getParameter("date");
	analyzercrawler.Date cDate = new analyzercrawler.Date(date);
	String key = request.getParameter("keyword");
	String path = request.getParameter("path");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Twitter Crawler</title>
	</head>
	<body>
		<h1>Twitter Crawler</h1>
		<%
			if(date == "" || key == "" || path == "")
			{
		%>
			<h2>Input Field (date/search/save directory) is Empty!</h2>
			<p>Click <a href="twittercrawler.jsp">here</a> to try again.</p>
		<%
			}
			else
			{
		%>
			<h2>Data Successfully Crawled!</h2>
		<%
				analyzercrawler.TwitterCrawler userInput = new analyzercrawler.TwitterCrawler();
				userInput.searchTweetsByKeyword(key, cDate,path);
		%>
			<p>Click <a href="analyzelocaldata.jsp">here</a> to view data.</p>
		<%
			}
		%>
		<div class="navbar">
			<br>
  			<a href="index.jsp" class="active">Home</a> | 
  			<a href="redditcrawler.jsp" class="active">Reddit Crawler</a> | 
  			<a href="twittercrawler.jsp" class="active">Twitter Crawler</a> | 
  			<a href="analyzelocaldata.jsp" class="active">Analyze Data Set</a>
		</div>
	</body>
</html>