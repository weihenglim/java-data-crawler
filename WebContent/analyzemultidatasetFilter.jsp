<%@ page language="java" import="analyzercrawler.helper, analyzercrawler.jsphelper,java.util.*,com.google.gson.Gson,com.google.gson.JsonObject"%>
<%
	String key = request.getParameter("keyword");
	String path1 = request.getParameter("pathLink1");
	String path2 = request.getParameter("pathLink2");
	String comment, comments;
	String[] commentArr, commentsArr;
	commentArr = new String[1000];
	commentsArr = new String[1000];
	int index = 0, indexs = 0, appear1, appear2;
	float nappear1, nappear2;
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
			if(key == "")
			{
		%>
			<h2>Input Field is Empty!</h2>
			<form>
				<input type="button" value="Try Again" onclick="history.back()">
			</form>
		<%
			}
			else
			{
		%>
			<h2>Data Successfully Filtered from</h2>
			<ul>
				<li><b>Set A:</b> <% out.print(path1); %></li>
				<li><b>Set B:</b> <% out.print(path2); %></li>
			</ul>
			<h3>Information Filtered:</h3>
		<%
			if(path1.contains("#"))
			{
				/* Calls the method TwitterCrawler to retrieve information from text file */
				comments = analyzercrawler.helper.readFile(path1);
				/* Calls commentFilter method to remove unecessary characters and return as array with specified keyword */
				commentArr = analyzercrawler.jsphelper.commentFilterTweet(comments, key);
				out.print("<h4>Results from: Set A</h4>");
				/* totalComments returns the numer of tweets with keyword */
				appear1 = Integer.valueOf(analyzercrawler.jsphelper.totalKeyword(commentArr));
				out.print("<p><em>Keyword: " + key + " | Number of Tweets with keyword: " + appear1 + "</em></p>");
			}
			else
			{
				/* Calls the method RedditCrawler to retrieve information from text file */
				comments = analyzercrawler.helper.readFile(path1);
				/* Calls commentFilter method to remove unecessary characters and return as array with specified keyword */
				commentArr = analyzercrawler.jsphelper.commentFilterReddit(comments, key);
				out.print("<h4>Results from: Set A</h4>");
				/* totalComments returns the numer of tweets with keyword */
				appear1 = Integer.valueOf(analyzercrawler.jsphelper.totalKeyword(commentArr));
				out.print("<p><em>Keyword: " + key + " | Number of Comments with keyword: " + appear1 + "</em></p>");
			}
			/* To print out the information contained inside the text file */
			while(true)
			{
				out.print(commentArr[index]);
				index++;
				out.print(commentArr[index]);
				index++;
				out.print("<br>");
				if(commentArr[index] == null)
				{
					break;
				}
			}
			if(path2.contains("#"))
			{
				/* Calls the method TwitterCrawler to retrieve information from text file */
				comment = analyzercrawler.helper.readFile(path2);
				/* Calls commentFilter method to remove unecessary characters and return as array with specified keyword */
				commentsArr = analyzercrawler.jsphelper.commentFilterTweet(comment, key);
				out.print("<h4>Results from: Set B</h4>");
				/* totalComments returns the numer of tweets with keyword */
				appear2 = Integer.valueOf(analyzercrawler.jsphelper.totalKeyword(commentsArr));
				out.print("<p><em>Keyword: " + key + " | Number of Tweets with keyword: " + appear2 + "</em></p>");
			}
			else
			{
				/* Calls the method TwitterCrawler to retrieve information from text file */
				comment = analyzercrawler.helper.readFile(path2);
				/* Calls commentFilter method to remove unecessary characters and return as array with specified keyword */
				commentsArr = analyzercrawler.jsphelper.commentFilterReddit(comment, key);
				out.print("<h4>Results from: Set B</h4>");
				/* totalComments returns the numer of comments with keyword */
				appear2 = Integer.valueOf(analyzercrawler.jsphelper.totalKeyword(commentsArr));
				out.print("<p><em>Keyword: " + key + " | Number of Tweets with keyword: " + appear2 + "</em></p>");
			}
			/* To print out the information contained inside the text file */
			while(true)
			{
				out.print(commentsArr[indexs]);
				indexs++;
				out.print(commentsArr[indexs]);
				indexs++;
				out.print("<br>");
				if(commentsArr[indexs] == null)
				{
					break;
				}
			}
			/* Pie Chart for better representation of data comparison */
			Gson gsonObj = new Gson();
			Map<Object,Object> map = null;
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			/* Assigns the value (in percentage) of keyword appearance of each dataset */
			nappear1 = ((float)appear1 / ((float)appear1 + (float)appear2)) * 100;
			nappear2 = ((float)appear2 / ((float)appear1 + (float)appear2)) * 100;
			/* Creates the pie portion object */
			map = new HashMap<Object,Object>(); map.put("label", "SetA"); map.put("y", nappear1); list.add(map);
			map = new HashMap<Object,Object>(); map.put("label", "SetB"); map.put("y", nappear2); list.add(map);
			String dataPoints = gsonObj.toJson(list);
		%>
			<h3>Insights to Information Filtered:</h3>
			<!-- Configurations for the Pie Chart -->
			<script type="text/javascript">
				window.onload = function()
				{ 
					var chart = new CanvasJS.Chart("chartContainer", {
						theme: "light2",
						animationEnabled: true,
						exportFileName: "Keyword Appearance",
						exportEnabled: true,
						title:{
						text: "Keyword Appearance Rate"
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
  			<a href="analyzelocaldata.jsp" class="active">Analyze Dataset</a> | 
  			<a href="analyzemultidata.jsp" class="active">Analyze Multiple Datasets</a>
		</div>
	</body>
</html>