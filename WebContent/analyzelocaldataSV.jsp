<%@ page language="java" import="analyzercrawler.helper, analyzercrawler.jsphelper, analyzercrawler.sentimentCalculator,java.util.*,com.google.gson.Gson,com.google.gson.JsonObject"%>
<%
	String path = request.getParameter("path");
	String comments, count;
	String[] commentArr;
	commentArr = new String[1000];
	int index = 0, appear1, appear2, appear3;
	float nappear1, nappear2, nappear3;
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
				<h3>Filter based on Keyword:</h3>
				<form action="analyzelocaldatasetFilter.jsp" method="POST">
					<input type="text" name="keyword" placeholder="What to find out on?">
					<input type="hidden" name="pathLink" value="<%=path %>">
					<input type="submit" value="Filter">
				</form>
				<h3>Insights to Information Filtered:</h3>
		<%
				if(path.contains("#"))
				{
					/* Calls the method TwitterCrawler to retrieve information from text file */
					comments = analyzercrawler.helper.readFile(path);
					/* Calls commentFilter method to remove unecessary characters and return as array */
					commentArr = analyzercrawler.jsphelper.commentFilterTweet(comments);
					/* totalComments returns the numer of tweets with keyword */
					count = analyzercrawler.jsphelper.totalKeyword(commentArr);
					out.print("<p><em>Number of Tweets: " + count + "<br>");
				}
				else
				{
					/* Calls the method TwitterCrawler to retrieve information from text file */
					comments = analyzercrawler.helper.readFile(path);
					/* Calls commentFilter method to remove unecessary characters and return as array */
					commentArr = analyzercrawler.jsphelper.commentFilterReddit(comments);
					count = analyzercrawler.jsphelper.totalKeyword(commentArr);
					out.print("<p><em>Number of Comments: " + count + "<br>");
				}
				/* Calls the method sentimentCalculator to calculate current thread's sentiment */
				analyzercrawler.sentimentCalculator sentiment = new analyzercrawler.sentimentCalculator(commentArr);
				out.print(sentiment.insights() + "</em></p>");
				/* Pie Chart for better representation of data comparison */
				Gson gsonObj = new Gson();
				Map<Object,Object> map = null;
				List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
				/* Assigns the value (in percentage) of keyword appearance of each dataset */
				appear1 = sentiment.positivec();
				appear2 = sentiment.negativec();
				appear3 = sentiment.neutralc();
				nappear1 = ((float)appear1 / ((float)appear1 + (float)appear2 + (float)appear3)) * 100;
				nappear2 = ((float)appear2 / ((float)appear1 + (float)appear2 + (float)appear3)) * 100;
				nappear3 = ((float)appear3 / ((float)appear1 + (float)appear2 + (float)appear3)) * 100;
				/* Creates the pie portion object */
				map = new HashMap<Object,Object>(); map.put("label", "Positive"); map.put("y", nappear1); list.add(map);
				map = new HashMap<Object,Object>(); map.put("label", "Negative"); map.put("y", nappear2); list.add(map);
				map = new HashMap<Object,Object>(); map.put("label", "Neutral"); map.put("y", nappear3); list.add(map);
				String dataPoints = gsonObj.toJson(list);
		%>
			<!-- Configurations for the Pie Chart -->
			<script type="text/javascript">
				window.onload = function()
				{ 
					var chart = new CanvasJS.Chart("chartContainer", {
						theme: "light2",
						animationEnabled: true,
						exportFileName: "General Sentiment",
						exportEnabled: true,
						title:{
						text: "General Sentiment"
						},
						data: [{
						type: "pie",
						showInLegend: true,
						legendText: "{label}",
						toolTipContent: "{label}: <strong>{y}%</strong>",
						indexLabel: "{label} {y}%",
						dataPoints : <%out.print(dataPoints);%>
						}]
					});
					// Prints the Pie Chart on the webpage
					chart.render();
				}
			</script>
			<div id="chartContainer" style="height: 370px; width: 100%;"></div>
			<br>
			<h3>Information from dataset:</h3>
			<%
				/* To print out the information contained inside the text file */
				while(true)
				{
					index++;
					out.print(commentArr[index]);
					index++;
					out.print("<br>");
					if(index >= Integer.parseInt(count))
					{
						break;
					}
				}
			%>
			<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
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
  			<a href="analyzelocaldata.jsp" class="active">Analyze Single Dataset</a> | 
  			<a href=analyzemultidata.jsp>Compare Two Datasets</a> | 
  			<a href=analyzexcrawldata.jsp>Crawl & Analyze BitMEX Data</a>
		</div>
	</body>
</html>