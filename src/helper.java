import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* 
    This class will contain all the miscallenous methods 
*/
class helper {
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

    /*
        Opens a CSV file and return an ArrayList with each element representing each line
    */
    public static ArrayList<String> readFile(String path) {
        ArrayList<String> output = new ArrayList<String>();
        try {
          BufferedReader reader = new BufferedReader(new FileReader(path));
          String line;
          while ((line = reader.readLine()) != null) {
            output.add(line);
          }
          reader.close();
          return output;
        }
        catch (Exception e) {
          e.printStackTrace();
          return null;
        }
    }

    public static void writeFile(String path, ArrayList<String> input) {
        try {
            FileWriter writer = new FileWriter(path);
            
            for (String s : input) {
                writer.write(s);
                writer.write(System.getProperty("line.separator"));
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}