#include "CrawlReddit.h"
#define CURL_STATICLIB
#include <iostream>
#include <string>
#include <stdlib.h>
#include "json/json.h"
#include <cstdlib>
#include <fstream>
#pragma warning(disable: 4996)

#include "curl/curl.h"


using namespace std;

CrawlReddit::CrawlReddit() {
    this->subreddit = "BitcoinMarkets";
    this->keyword = "april_15_2019";
    this->sort = "top";

}
CrawlReddit::CrawlReddit( string subreddit, string keyword,string path) {

        // replace date space to undersocre 
    for (int i = 0; i < keyword.length(); i++)
    {
        if (keyword[i] == ' ')
            keyword[i] = '_';
    }
	this->subreddit = subreddit;
	this->keyword = keyword;
	//this->sort = sort;
	this->path = path;

}
size_t my_write(void* buffer, size_t size, size_t nmemb, void* param)
{

    string& text = *static_cast<string*>(param);
    size_t totalsize = size * nmemb;
    text.append(static_cast<char*>(buffer), totalsize);
    return totalsize;
}


// is to get the daily discussion url 
string CrawlReddit::postUrl() {

    CURL* curl;
    CURLcode res = CURLE_OK;
    string result;
    string url = "https://www.reddit.com/r/" + this->subreddit + "/search.json?q=" + this->keyword + "&restrict_sr=1";
    curl = curl_easy_init();
    if (curl) {
        curl_easy_setopt(curl, CURLOPT_URL, url.c_str());// http the json url

        //example.com is redirected, so we tell libcurl to follow redirection 
        curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, my_write);//write
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &result);// print out
        // Perform the request, res will get the return code 
        res = curl_easy_perform(curl);
        // Check for errors 
        if (res != CURLE_OK)
            fprintf(stderr, "curl_easy_perform() failed: %s\n",
                curl_easy_strerror(res));

        // always cleanup 
        curl_easy_cleanup(curl);
    }
   
    Json::Reader reader;
    Json::Value root;

    const auto rawJsonLength = static_cast<int>(result.length());
    constexpr bool shouldUseOldWay = false;
    JSONCPP_STRING err;


    if (shouldUseOldWay) {

        reader.parse(result, root);
    }
    else {
        Json::CharReaderBuilder builder;
        const std::unique_ptr<Json::CharReader> reader(builder.newCharReader());
        if (!reader->parse(result.c_str(), result.c_str() + rawJsonLength, &root,
            &err)) {
            cout << "error" << endl;

        }
    }

    const string newURL = root["data"]["children"][0]["data"]["url"].asString(); // after conert find the object array to take in the values.
    //  cout << "what is my url" << url << endl;

    return newURL;
}

// get the comment and write to text file including the score 
void CrawlReddit::getComment() {
    CURL* curl;
    CURLcode res = CURLE_OK;
   
    string url= CrawlReddit::postUrl() + ".json?sort=top";
    //string url = "https://www.reddit.com/r/BitcoinMarkets/comments/bf8a28/daily_discussion_saturday_april_20_2019/.json?sort=top";
    string result;
    curl = curl_easy_init();
    if (curl) {
        curl_easy_setopt(curl, CURLOPT_URL, url.c_str());

        //example.com is redirected, so we tell libcurl to follow redirection 
        curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, my_write);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &result);
        // Perform the request, res will get the return code 
        res = curl_easy_perform(curl);
        // Check for errors 
        if (res != CURLE_OK)
            fprintf(stderr, "curl_easy_perform() failed: %s\n",
                curl_easy_strerror(res));

        // always cleanup 
        curl_easy_cleanup(curl);
    }
    //cout << "FINAL RESULT HERER" << result << endl;
    Json::Reader reader;
    Json::Value root,arr;
   

    const auto rawJsonLength = static_cast<int>(result.length());
    constexpr bool shouldUseOldWay = false;
    JSONCPP_STRING err;


    if (shouldUseOldWay) {

        reader.parse(result, root);
    }
    else {
        Json::CharReaderBuilder builder;
        const std::unique_ptr<Json::CharReader> reader(builder.newCharReader());
        if (!reader->parse(result.c_str(), result.c_str() + rawJsonLength, &root,
            &err)) {
            cout << "error" << endl;
        }
    }
/*    for (int i = 1; i < root[1]["data"]["children"].size(); i++)
   {
        
    const string body = root[1]["data"]["children"][i]["data"]["body"].asString();
    arr[i]= body;
    //cout << "comment:" << i<< endl;
   // cout << body<<endl;
     
    }*/
    string arrStats[42];
    string arrScore[42];
 // open sentiments files
    fstream fin;
    fin.open("sentiments.csv", ios::in);
    if (!fin.is_open())
        cout << "error file open" << endl;
    string stats;
    string score;
    size_t  count =0;
   while (fin.good())
    {
        getline(fin, stats,',');
        getline(fin, score, '\n');
        arrStats[count]= stats;
        arrScore[count] = score;
        count++;
     //   cout << "status: " << arrStats[count] << " " << arrScore[count] << endl;
    }
 int totalscore = 0;
 ofstream newfile;

 //write comment to file 
 newfile.open(this->subreddit + "_" + this->keyword +"_"+ this->path, ofstream::out | ofstream::app);
 newfile << "["<< endl;
            for (int i = 1; i < root[1]["data"]["children"].size(); i++)// loop the whole text comment
            {     
            
                const string body = root[1]["data"]["children"][i]["data"]["body"].asString();
            
                if ((body.compare("[removed]")) == 0|| (body.compare("[deleted]")) == 0) { // remove del n remove comment 
                  
      
                }
                else {
                    for (int i = 0; i < 40; i++) // compare the sentiments and add score 
                    {
                      if (body.find(arrStats[i]) != std::string::npos) {
                          totalscore = totalscore + stoi(arrScore[i]);
                    }
                      //  cout <<arrStats[i] <<"score" << arrScore[i]<<endl;
                    }
                   // cout << "what is the score " << totalscore<<"commenting: "<< body<< endl;
                 
                    newfile << "{ score: " << totalscore << "\n";
                     newfile << "text:\t" << body << "}\n";
                    totalscore = 0;
                }
               
               
                newfile << endl;
            
            }
            newfile << "]\n" << endl;
            newfile.close();
  
   
}
void CrawlReddit::setKeyword(string keyword) {
    for (int i = 0; i < keyword.length(); i++)
    {
        if (keyword[i] == ' ')
            keyword[i] = '_';
    }
    this->keyword = keyword;
}
void  CrawlReddit::setSubreddit(string subreddit) {
    this->subreddit = subreddit;
}
void  CrawlReddit::setPath(string path) {
    this->path = path;
}

