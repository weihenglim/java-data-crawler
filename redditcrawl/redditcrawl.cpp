#define CURL_STATICLIB
#include <iostream>
#include <string>
#include <stdlib.h>
#include "json/json.h"
#include <cstdlib>
#include <fstream>
#pragma warning(disable: 4996)
#include "CrawlReddit.h"
#include "curl/curl.h"
using namespace std;

int main() {
    CrawlReddit reddit("BitcoinMarkets", "august 11 2019","reddit.txt");// create obj (subreddit,date and filename)
  // cout << reddit.postUrl();
  reddit.getComment();// get comment n write to file
  
}