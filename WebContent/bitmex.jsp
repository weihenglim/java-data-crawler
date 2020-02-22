<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>BitMEX Crawler</title>
	</head>
	<body>
		<h1>BitMEX Crawler</h1>
		<h2>What do you want to find out more on?</h2>
		<form action="bitmexHandlerSV.jsp" method="POST">
			<input type="text" name="symbol" placeholder="Symbol">
			<input type="text" name="start" placeholder="Start Date">
			<input type="text" name="end" placeholder="End Date">
			<input type="text" name="path" placeholder="Where to save the file?">
			<input type="submit" value="Fetch!">
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