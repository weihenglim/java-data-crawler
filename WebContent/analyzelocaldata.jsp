<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Local Dataset Analyzer</title>
	</head>
	<body>
		<h1>Local Dataset Analyzer</h1>
		<h2>Please enter the directory of the data: </h2>
		<form action="analyzelocaldataSV.jsp" method="POST">
			<input type="file" name="path" size="40"/>
			
			<input type="submit" value="Analyze!">
		</form>
		<br>
		<em>NOTE: Process may take quite some time, please wait for the page to load</em>
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