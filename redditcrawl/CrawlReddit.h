#pragma once
#pragma once
#include <iostream>

using namespace std;




class CrawlReddit {
private:
	
	string subreddit;
	string keyword;
	//string sort;
	string path;
	

public:
	CrawlReddit();
	CrawlReddit( string, string,string);
	void getComment();
	string postUrl();
	void setKeyword(string);
	void setSubreddit(string);
	void setPath(string);

};

