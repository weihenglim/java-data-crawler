<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Reddit Crawler</title>
	</head>
	<body>
		<h1>Reddit Crawler</h1>
		<h2>What do you want to find out more on?</h2>
		<form action="redditHandlerSV.jsp" method="POST">
			<input type="text" name="subreddit" placeholder="Subreddit">
			<input type="text" name="flair" placeholder="Flair">
			<input type="text" name="keyword" placeholder="What to find out on?">
			<input type="text" name="path" placeholder="Where to save the file?">
			<input type="submit" value="Fetch!">
		</form>
		<br>
		<em>NOTE: Process may take quite some time, please wait for the page to load</em>
		<div class="navbar">
			<br>
  			<a href="index.jsp" class="active">Home</a> | 
  			<a href="twittercrawler.jsp" class="active">Twitter Crawler</a> | 
  			<a href="analyzelocaldata.jsp" class="active">Analyze Data Set</a> | 
  			<a href="" class="active">Analyze BitCoin Market</a>
		</div>
	</body>
</html>