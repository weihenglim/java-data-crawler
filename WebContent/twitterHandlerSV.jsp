<%@ page language="java" import="analyzercrawler.TwitterCrawler"%>
<%
	String date = request.getParameter("date");
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
			<h3>Results:</h3>
		<%
				analyzercrawler.TwitterCrawler userInput = new analyzercrawler.TwitterCrawler(key, date, path);
				out.print(userInput.findComments());
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