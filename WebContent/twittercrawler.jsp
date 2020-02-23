<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Twitter Crawler</title>
	</head>
	<body>
		<h1>Twitter Crawler</h1>
		<h2>What do you want to find out more on?</h2>
		<form action="twitterHandlerSV.jsp" method="POST">
			<input type="text" name="date" placeholder="Date? E.g. 2020-02-17">
			<input type="text" name="keyword" placeholder="What to find out on?">
			<input type="text" name="amount" placeholder="How many tweets?">
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
  			<a href="analyzelocaldata.jsp" class="active">Analyze Data Set</a>
		</div>
	</body>
</html>