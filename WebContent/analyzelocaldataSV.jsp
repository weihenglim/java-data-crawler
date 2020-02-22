<%@ page language="java" import="analyzercrawler.helper, analyzercrawler.jsphelper"%>
<%
	String path = request.getParameter("path");
	String comments;
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
			if(path == "")
			{
		%>
			<h2>Input Field is Empty!</h2>
			<p>Click <a href="analyzelocaldata.jsp">here</a> to try again.</p>
		<%
			}
			else
			{
		%>
			<h2>Data Successfully Retrieved from</h2>
			<p><% out.print(path); %></p>
			<h3>Information from dataset:</h3>
			<form action="analyzelocaldatasetFilter.jsp" method="POST">
				<input type="text" name="keyword" placeholder="What to find out on?">
				<input type="hidden" name="pathLink" value="<%=path %>">
				<input type="submit" value="Filter">
			</form>
			<br>
		<%
				if(path.contains("#"))
				{
					/* Calls the method TwitterCrawler to retrieve information from text file */
					comments = analyzercrawler.helper.readFile(path);
					/* Calls the method commentReplacer to remove unecessary characters from text file */
					comments = analyzercrawler.jsphelper.commentReplacerTweet(comments);
					/* totalComments returns the numer of tweets with keyword */
					out.print("<p>Total number of Tweets: " + analyzercrawler.jsphelper.totalComments(comments, "T") + "</p>");
					out.print(comments);
				}
				else
				{
					/* Calls the method RedditCrawler to retrieve information from text file */
					comments = analyzercrawler.helper.readFile(path);
					/* Calls the method commentReplacer to remove unecessary characters from text file */
					comments = analyzercrawler.jsphelper.commentReplacerReddit(comments);
					/* totalComments returns the numer of comments with keyword */
					out.print("<p>Total number of Comments: " + analyzercrawler.jsphelper.totalComments(comments, "R") + "</p>");
					out.print(comments);
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
  			<a href="analyzelocaldata.jsp" class="active">Analyze Dataset</a> | 
  			<a href="analyzemultidata.jsp" class="active">Analyze Multiple Datasets</a>
		</div>
	</body>
</html>