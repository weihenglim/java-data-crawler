#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
#include "oauthlib.h"
#include "twitcurl.h"
#include <json.hpp>

using namespace std;
using json = nlohmann::json;

FILE _iob[] = { *stdin, *stdout, *stderr };
extern "C" FILE * __cdecl __iob_func(void) { return _iob; }
extern "C" void __imp__set_output_format(void) {};

void write(string FileName, string line);

int main(void) {
	twitCurl twitterObj;
	string replyMsg;

	int pageCount = 2;
	int entryPerPage = 100;
	string searchKeyword = "bitcoin";
	string sinceDate = "2020-03-20";
	string untilDate = "2020-03-21";

	long long int last_id = LLONG_MAX;

	string consumerKey = std::string("xLoe9hZYpNDthiFEZ2ffGAuhf");
	string consumerKeySecret = std::string("hRzq2LJYvU7mnN5q9IJceiQvNnlMmzlxPBRFAzXXcwFBx6oRcm");
	string myOAuthAccessTokenKey = std::string("826091506591150080-zhJRmDUg7XFsOe0Ku4YaQaB1M1Tcc8B");
	string myOAuthAccessTokenKeySecret = std::string("cLzg9NmHykEZbvoNNP1DvZKgcy5PSbtdjj7kxBGMY3ILU");

	twitterObj.getOAuth().setConsumerKey(consumerKey);
	twitterObj.getOAuth().setConsumerSecret(consumerKeySecret);
	twitterObj.getOAuth().setOAuthTokenKey(myOAuthAccessTokenKey);
	twitterObj.getOAuth().setOAuthTokenSecret(myOAuthAccessTokenKeySecret);

	string searchQuery = searchKeyword + " since:" + sinceDate + " until:" + untilDate;

	remove("output.txt");
	int count = 1;

	for (int i = 0; i < pageCount; ++i) {
		if (i == 0) {
			twitterObj.search(searchQuery, to_string(entryPerPage));
		}
		else {
			last_id--;
			twitterObj.search(searchQuery, to_string(entryPerPage), to_string(last_id));
		}

		twitterObj.getLastWebResponse(replyMsg);
		auto result = json::parse(replyMsg);

		for (auto& status : result["statuses"]) {
			if (status["id"] < last_id) {
				last_id = status["id"];
			}

			string outputStr = to_string(count++) + ". " + to_string(status["text"]) + "\n";
			write("output.txt", outputStr);
		}
	}

	cout << "end of Program" << endl;
	return 0;
}

void write(string FileName, string line)
{
	fstream myFile(FileName, fstream::app);

	if (myFile.is_open())
	{
		myFile << line;
		myFile.close();
	}
}