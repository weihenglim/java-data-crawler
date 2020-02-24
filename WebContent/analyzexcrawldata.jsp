<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Bitcoin Market</title>
	</head>
	<body>
		<h1>Bitcoin Market Analyzers x Crawler</h1>
		<h2>Take a look at how's the market going for the past 7 days</h2>
		<form action="analyzexcrawldataSV.jsp" method="POST">
			<input type="text" name="path" placeholder="Where to save the file?" required>
			<input type="submit" value="Analyze!">
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