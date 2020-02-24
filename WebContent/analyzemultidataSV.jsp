<%@ page language="java" import="analyzercrawler.helper, analyzercrawler.jsphelper"%>
<%
	String path1 = request.getParameter("path1");
	String path2 = request.getParameter("path2");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Multiple Dataset Analyzer</title>
	</head>
	<body>
		<h1>Multiple Dataset Analyzer</h1>
		<%
			if(path1 == "" || path2 == "")
			{
		%>
			<h2>Input Field is Empty!</h2>
			<p>Click <a href="analyzemultidata.jsp">here</a> to try again.</p>
		<%
			}
			else
			{
		%>
			<h2>Data Successfully Retrieved from</h2>
			<ul>
				<li><% out.print(path1); %></li>
				<li><% out.print(path2); %></li>
			</ul>
			<h3>What do you want to do with the datasets?</h3>
			<form action="analyzemultidatasetFilter.jsp" method="POST">
				<input type="text" name="keyword" placeholder="What to find out on?">
				<input type="hidden" name="pathLink1" value="<%=path1 %>">
				<input type="hidden" name="pathLink2" value="<%=path2 %>">
				<input type="submit" value="Filter">
			</form>
			<br>
			<form action="analyzemultidatasetSentiment.jsp" method="POST">
				<input type="hidden" name="pathLink1" value="<%=path1 %>">
				<input type="hidden" name="pathLink2" value="<%=path2 %>">
				<input type="submit" value="Compare Sentiment">
			</form>
			<br>
		<%
			}
		%>
		<div class="navbar">
			<br>
  			<a href="index.jsp" class="active">Home</a> | 
  			<a href="redditcrawler.jsp" class="active">Reddit Crawler</a> | 
  			<a href="twittercrawler.jsp" class="active">Twitter Crawler</a> | 
  			<a href="analyzelocaldata.jsp" class="active">Analyze Dataset</a> | 
  			<a href=analyzemultidata.jsp>Compare Two Datasets</a> | 
  			<a href=analyzexcrawldata.jsp>Crawl & Analyze BitMEX Data</a>
		</div>
	</body>
</html>