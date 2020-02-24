package analyzercrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/* 
    This class will contain all the miscallenous methods, variables & fields that will be used project-wide
*/
public class helper {
    public static final String SentimentsFilePath = "../data/sentiments.csv";
    public static final String UserAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0";
    public static final int HTTPMaxBodySize = 0;
    public static final int HTTPTimeout = 600000;

    /* 
        Returns the html of a webpage given a URL
    */
    public static Document parseHttp(String url) {
        try {
            return Jsoup.connect(url).header("Accept-Encoding", "gzip, deflate").userAgent(helper.UserAgent)
                    .maxBodySize(helper.HTTPMaxBodySize).timeout(helper.HTTPTimeout).get();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getJson(String url) {
        try {
        	/* Setting the userAgent forces the web server to recognize this application as a web browser instead of a bot */
        	return Jsoup.connect(url).userAgent("Mozilla/5.0").ignoreContentType(true).execute().body();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public static void writeToFile(String path, String input) {
        File file = new File(path);
        file.getParentFile().mkdirs();

        try {
          
            FileWriter fOut = new FileWriter(file);            
            fOut.write(input);
            fOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public static String readFile(String path) {
        String output = "";
        File file = new File(path);
        int i;

        try {
            FileReader fIn = new FileReader(file);
            
            while ( (i = fIn.read()) != -1)
                output += (char) i;

            fIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static String clean(String comment) {
        /* Remove URL since we can't parse those */
        comment = comment.replaceAll("http?[\\S]+", " ");

        /* Remove trailing and leading space */
        comment = comment.trim();

        comment = comment.replaceAll("[ ]{2,}", "");

        /* Remove any symbols, punctuations */
        comment = comment.replaceAll("[^a-zA-Z0-9\\s]", "");

        return comment;
    }

    public static void createFileIfNotExist(String path) {
        File target = new File(path);
        target.getParentFile().mkdirs();
    }

    public static String toCamelCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}