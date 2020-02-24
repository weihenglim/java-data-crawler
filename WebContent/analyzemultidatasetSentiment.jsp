<%@ page language="java" import="analyzercrawler.helper, analyzercrawler.jsphelper,java.util.*,com.google.gson.Gson,com.google.gson.JsonObject, analyzercrawler.sentimentCalculator"%>
<%
	String key = request.getParameter("keyword");
	String path1 = request.getParameter("pathLink1");
	String path2 = request.getParameter("pathLink2");
	String comment, comments;
	String[] commentArr, commentsArr;
	commentArr = new String[1000];
	commentsArr = new String[1000];
	int index = 0, indexs = 0, appear1, appear2, appear3;
	float nappear1, nappear2, nappear3;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Multiple Dataset Analyzer</title>
	</head>
	<body>
		<h1>Multiple Dataset Analyzer</h1>
		<h2>Data Successfully retrieved from</h2>
		<ul>
			<li><b>Set A:</b> <% out.print(path1); %></li>
			<li><b>Set B:</b> <% out.print(path2); %></li>
		</ul>
		<h3>Overall Sentiment:</h3>
		<%
			if(path1.contains("#"))
			{
				/* Calls the method TwitterCrawler to retrieve information from text file */
				comments = analyzercrawler.helper.readFile(path1);
				/* Calls commentFilter method to remove unecessary characters and return as array */
				commentArr = analyzercrawler.jsphelper.commentFilterTweet(comments);
			}
			else
			{
				/* Calls the method RedditCrawler to retrieve information from text file */
				comments = analyzercrawler.helper.readFile(path1);
				/* Calls commentFilter method to remove unecessary characters and return as array */
				commentArr = analyzercrawler.jsphelper.commentFilterReddit(comments);
			}
			if(path2.contains("#"))
			{
				/* Calls the method TwitterCrawler to retrieve information from text file */
				comment = analyzercrawler.helper.readFile(path2);
				/* Calls commentFilter method to remove unecessary characters and return as array */
				commentsArr = analyzercrawler.jsphelper.commentFilterTweet(comment);
			}
			else
			{
				/* Calls the method TwitterCrawler to retrieve information from text file */
				comment = analyzercrawler.helper.readFile(path2);
				/* Calls commentFilter method to remove unecessary characters and return as array */
				commentsArr = analyzercrawler.jsphelper.commentFilterReddit(comment);
			}
			/* Calls the method sentimentCalculator to calculate current thread's sentiment */
			analyzercrawler.sentimentCalculator sentiment1 = new analyzercrawler.sentimentCalculator(commentArr);
			analyzercrawler.sentimentCalculator sentiment2 = new analyzercrawler.sentimentCalculator(commentsArr);
			appear1 = sentiment1.positivec() + sentiment2.positivec();
			appear2 = sentiment1.negativec() + sentiment2.negativec();
			appear3 = sentiment1.neutralc() + sentiment2.neutralc();
			out.print("<p><b>Set A: </b><em>" + sentiment1.insights() + "<br>");
			out.print("<b>Set B: </b>" + sentiment2.insights() + "</em></p>");
			if(appear1 > appear2)
			{
				out.print("<p>The overall sentiment between these two sets is positive.</p>");
			}
			else if(appear1 < appear2)
			{
				out.print("<p>The overall sentiment between these two sets is negative.</p>");
			}
			else
			{
				out.print("<p>The overall sentiment between these two sets is neutral.</p>");
			}
			/* Pie Chart for better representation of data comparison */
			Gson gsonObj = new Gson();
			Map<Object,Object> map = null;
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			/* Assigns the value (in percentage) of keyword appearance of each dataset */
			nappear1 = ((float)appear1 / ((float)appear1 + (float)appear2 + appear3)) * 100;
			nappear2 = ((float)appear2 / ((float)appear1 + (float)appear2 + appear3)) * 100;
			nappear3 = ((float)appear3 / ((float)appear1 + (float)appear2 + appear3)) * 100;
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
						exportFileName: "Overall Sentiment",
						exportEnabled: true,
						title:{
						text: "Overall Sentiment"
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
			<br>
			<div id="chartContainer" style="height: 370px; width: 100%;"></div>
			<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
			<p>------------------------------------------------------------------------------------------</p>
			<form><input type="button" value="Return" onclick="history.back()"></form>
			<p><b>You have reached the end of the data. Click <a href="index.jsp">here</a> to exit.</b></p>
			<br>
			<em>NOTE: If nothing is displayed above, it means that the file path is wrong or the file is empty</em>
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