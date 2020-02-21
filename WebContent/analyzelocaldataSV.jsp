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
				comments = analyzercrawler.helper.readFile(path);
				out.print(analyzercrawler.jsphelper.commentReplacer(comments));
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
  			<a href="twittercrawler.jsp" class="active">Twitter Crawler</a> |
  			<a href="analyzelocaldata.jsp" class="active">Analyze Data Set</a> |
  			<a href="" class="active">Analyze BitCoin Market</a> |
		</div>
	</body>
</html>