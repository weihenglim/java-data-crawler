package praw;

import org.jsoup.nodes.Element;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.helper.Validate;

public class crawler {
	protected String url,postsearch;
	
	protected Document post, search; 
	public crawler(String url) {
		this.url = url;
		//this.postsearch = postsearch;
	}
	public Document getData() {
		
		try {
		search = Jsoup.connect(url).header("Accept-Encoding", "gzip, deflate")
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
				.maxBodySize(0).timeout(600000).get();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return search;
	}
	public Document getDataComment(String sb) {
		 	
		try {
		
		search = Jsoup.connect(url+"?sort="+sb).header("Accept-Encoding", "gzip, deflate")
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
				.maxBodySize(0).timeout(600000).get();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return search;
	}

	
	public Elements getPost() {
		Elements divTag = getData().getElementsByClass("_3tw__eCCe7j-epNCKGXUKk");// using overidding from sub class blank
		return divTag;
	}
	public Elements getPostLink() {
		Elements divTag = getData().getElementsByClass("");// using overidding from sub class blank
		return divTag;
	}

	
	
	
}
