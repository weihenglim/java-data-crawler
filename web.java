package praw;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class web {
	public static void main(String args[]) throws ParseException {
		String testDate = "February 13 2019";
		ArrayList<crawler> crawlSearchpost = new ArrayList<crawler>();
		sortData date = new sortData("january", "4", "2019");// this now is static but if we add to the search function
																// we have to split

		// crawlSearchpost.add(new
		// crawlreddit("https://www.reddit.com/r/BitcoinMarkets/search/?q=", "February
		// 12 2019"));

		for (int i = 0; i < date.getSevendays().size(); i++) {
			crawlSearchpost.add(
					new crawlreddit("https://www.reddit.com/r/BitcoinMarkets/search/?q=", date.getSevendays().get(i)));
			System.out.println(date.getSevendays().get(i));
		}

		// crawler crawl = new
		// crawlreddit("https://www.reddit.com/r/BitcoinMarkets/search/?q=", testDate);
		// // create the
		// object
		// and fid
		// the date
		Set<String> postLink = new LinkedHashSet<>();

		// String title = crawlSearchpost.getData().title();
		String firstPost = "";
		String firstLink = "";
		// Elements test = crawl.getData();

		// System.out.println(title);
		System.out.println("----------------------- find post-------------------");

		for (crawler newcrawls : crawlSearchpost) {
			for (Element searchPost : newcrawls.getPost()) {
				firstPost = searchPost.text();
				if (firstPost.contains("[Daily Discussion]")) {
					// here if u wan to add to arraylist
					System.out.println("*this post*" + firstPost + "\n");
				}
			}
		}

		/*
		 * for (Element searchPost : crawl.getPost()) { firstPost=searchPost.text(); if
		 * (firstPost.contains("[Daily Discussion]")) {
		 * //searchresults.add(weatherTags.text()); // here if u wan to add to arraylist
		 * System.out.println("*this post*"+firstPost+"\n"); } }
		 */
		System.out.println("----------------------- find post link-------------------");
		for (crawler newcrawls : crawlSearchpost) {
			for (Element link : newcrawls.getPostLink()) {

				String s = link.absUrl("href");
				if (s.contains("/comments/") && s.contains("daily_discussion")) { // find the list of date
					postLink.add(s);
				}
			}
		}
		for (String searchlink : postLink) {// find the search comment link

			System.out.println("this link* " + searchlink);// the links for all data changing to array
		}

		System.out.println("----------------------- find comment -------------------");

		// loop from the link given 
		for (String searchlink : postLink) {
			System.out.println(searchlink+" WHAT LINK IS THIS");
			crawler crawlcomment = new crawler(searchlink);
			Document editeddata = crawlcomment.getDataComment("top");
			Elements comments = editeddata.getElementsByClass("_3tw__eCCe7j-epNCKGXUKk");
			// 2nd loop all the comment 
		
				for (int i = 0; i < 10; i++) {
					System.out.println("*this comment* " + comments.get(i).text() + "\n");
				}
		 //here if u wan to add to arraylist FOR COMMENTS
				
			
			System.out.println("----------------------- pull 1 day data end -------------------");
		}
		System.out.println("----------------------- FINISH -------------------");
	}
}
