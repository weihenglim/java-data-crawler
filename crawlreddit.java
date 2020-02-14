package praw;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class crawlreddit extends crawler{
	protected String sb;
	protected String pf,postsearch;
	
	public crawlreddit(String url,String postsearch) {
		super(url);
		this.postsearch = postsearch;

	}
	public Document getData() {
		
		try {

		search = Jsoup.connect(this.url+this.postsearch+"&restrict_sr=1").header("Accept-Encoding", "gzip, deflate")
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
				.maxBodySize(0).timeout(600000).get();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return search;
	}
	
	public Elements getPost() {
		Elements divTag = getData().getElementsByClass("_2XDITKxlj4y3M99thqyCsO");
		return divTag;
	}
	public Elements getPostLink() {
		Elements divTag = getData().select("a[href]");// using overidding from sub class blank
		return divTag;
	}
	
}
