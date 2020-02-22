<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Multiple Dataset Analyzer</title>
	</head>
	<body>
		<h1>Multiple Dataset Analyzer</h1>
		<h2>Please enter the directory of the two data: </h2>
		<form action="analyzemultidataSV.jsp" method="POST">
			<p> Dataset 1 Path
			 	<input type="file" name="path1"  placeholder="Dataset 1 Path" size="40" />
			</p>
			<p> Dataset 2 Path
				<input type="file" name="path2"  placeholder="Dataset 2 Path" size="40" />
			</p>
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