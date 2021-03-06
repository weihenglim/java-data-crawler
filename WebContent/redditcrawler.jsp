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
			<input type="text" name="subreddit" placeholder="Subreddit" required>
			<input type="text" name="flair" placeholder="Flair" required>
			<input type="text" name="keyword" placeholder="What to find out on?" required>
			<input type="text" name="path" placeholder="Where to save the file?" required>
			<input type="submit" value="Fetch!">
		</form>
		<br>
		<em>NOTE: Process may take quite some time, please wait for the page to load</em>
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