<%@page import="analyzercrawler.marketanalyzerHelper"%>
<%@ page language="java" import="analyzercrawler.helper, java.util.*,com.google.gson.Gson,com.google.gson.JsonObject, analyzercrawler.sentimentCalculator, analyzercrawler.Analyzer, analyzercrawler.Date"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Bitcoin Market</title>
	</head>
	<body>
		<h1>Bitcoin Market Analyzers x Crawler</h1>
		<h2>Analysis of the Bitcoin Market over the past 7 days</h2>
		<%
			String path = request.getParameter("path");
			String comments;
			try
			{
				/* Calls the method to crawl all the neccessary data if they are not present in directory */
				analyzercrawler.Analyzer bitcoin = new analyzercrawler.Analyzer();
				bitcoin.analyze(path);
				/* Calls the Date method to retrieve today's date */
				analyzercrawler.Date tdate = new analyzercrawler.Date();
				/* Calls the method TwitterCrawler to retrieve information from text file */
				comments = analyzercrawler.helper.readFile(path + "\\analysis-" + tdate.toStringFor("bitmex") + ".txt");
				/* Calls the method marketanalyzerHelper to get the necessary information */
				analyzercrawler.marketanalyzerHelper analysis = new marketanalyzerHelper(comments);
		%>
				<script type="text/javascript">
				window.onload = function () {
					var chart = new CanvasJS.Chart("chartContainer",
					{
						title:{
							text: "Bitcoin Market Value (Week)",
							fontFamily: "times new roman"
						},
						zoomEnabled: true,
						exportEnabled: true,
						axisY: {
							includeZero:false,
							title: "Prices",
							prefix: "$ "
						},
						data: [
						{
							type: "candlestick",
							risingColor: "green",
							color: "red",
							dataPoints: [
								{y:[<%=analysis.openStock(0)%>, <%=analysis.highStock(0)%>, <%=analysis.lowStock(0)%>, <%=analysis.closeStock(0)%>], label: "Day 0"},
								{y:[<%=analysis.openStock(1)%>, <%=analysis.highStock(1)%>, <%=analysis.lowStock(1)%>, <%=analysis.closeStock(1)%>], label: "Day 1"},
								{y:[<%=analysis.openStock(2)%>, <%=analysis.highStock(2)%>, <%=analysis.lowStock(2)%>, <%=analysis.closeStock(2)%>], label: "Day 2"},
								{y:[<%=analysis.openStock(3)%>, <%=analysis.highStock(3)%>, <%=analysis.lowStock(3)%>, <%=analysis.closeStock(3)%>], label: "Day 3"},
								{y:[<%=analysis.openStock(4)%>, <%=analysis.highStock(4)%>, <%=analysis.lowStock(4)%>, <%=analysis.closeStock(4)%>], label: "Day 4"},
								{y:[<%=analysis.openStock(5)%>, <%=analysis.highStock(5)%>, <%=analysis.lowStock(5)%>, <%=analysis.closeStock(5)%>], label: "Day 5"},
								{y:[<%=analysis.openStock(6)%>, <%=analysis.highStock(6)%>, <%=analysis.lowStock(6)%>, <%=analysis.closeStock(6)%>], label: "Day 6"},
								{y:[<%=analysis.openStock(7)%>, <%=analysis.highStock(7)%>, <%=analysis.lowStock(7)%>, <%=analysis.closeStock(7)%>], label: "Day 7"}
							]
						}
						]
					});
					chart.render();
				}
				</script>
				<div id="chartContainer" style="height: 370px; width: 100%;"></div>
				<h2>Analysis of overall sentiment over past 7 days</h2>
				<ul>
		<%
				int total = 0;
				for(int i = 0; i < 8; i++)
				{
					if((analysis.twitterScore(i) + analysis.redditScore(i)) > 0)
					{
						out.print("<li>The general sentiment of day " + i + " is positive</li>");
					}
					else if((analysis.twitterScore(i) + analysis.redditScore(i)) < 0)
					{
						out.print("<li>The general sentiment of day " + i + " is negative</li>");
					}
					else
					{
						out.print("<li>The general sentiment of day " + i + " is neutral</li>");
					}
					total = analysis.twitterScore(i) + analysis.redditScore(i);
				}
				if(total > 0)
				{
					out.print("<p>The overall sentiment of the past 7 days is positive.</p>");
				}
				else if(total < 0)
				{
					out.print("<p>The overall sentiment of the past 7 days is negative.</p>");
				}
				else
				{
					out.print("<p>In general, the overall sentiment of the past 7 days is neutral.</p>");
				}
		%>
				</ul>
		<%
			}
			catch(Exception e)
			{
		%>
				<h2>This analyzer only works after GMT(+8) 12pm due to the thread in Reddit not being created.</h2>
				<p>Click <a href="analyzexcrawldata.jsp">here</a> to try again.</p>
		<%
			}
		%>
		<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
		<p>------------------------------------------------------------------------------------------</p>
		<p><b>You have reached the end of the data. Click <a href="index.jsp">here</a> to exit.</b></p>
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