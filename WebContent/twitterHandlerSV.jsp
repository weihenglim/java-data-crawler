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
				try
				{
					/* Calls the RedditCrawler method with user's choice of subreddit, flair and directory to store data */
					analyzercrawler.TwitterCrawler userInput = new analyzercrawler.TwitterCrawler();
					userInput.TwitterCrawlerP(path);
					userInput.searchTweetsByKeyword(key, cDate);
		%>
					<h2>Data Successfully Crawled!</h2>
					<p>Click <a href="analyzelocaldata.jsp">here</a> to view data.</p>
					<br>
					<em>NOTE: Twitter only allows crawling up to 7 days back</em>
		<%
				}
				catch(Exception e)
				{
					out.print("<h2>Please ensure that the input information are correct!!</h2>");
		%>
					<p>Click <a href="redditcrawler.jsp">here</a> to try again.</p>
		<%
				}
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