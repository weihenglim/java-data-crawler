<%@ page language="java" import="analyzercrawler.helper"%>
<%
	String path = request.getParameter("path");
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
			<h2>Data Successfully Retrieved!</h2>
			<h3>Information from dataset:</h3>
		<%
				out.print(analyzercrawler.helper.readFile(path));
		%>
			<p>You have reached the end of the data. Click <a href="index.jsp">here</a> to return.</p>
			<br>
			<em>NOTE: If nothing is displayed above, it means that the file path is wrong.</em>
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