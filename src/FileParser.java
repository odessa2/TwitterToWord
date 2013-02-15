import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.T;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileParser {

	public FileParser() {
		// TODO Auto-generated constructor stub

	}

	public ArrayList<Tweet> parseJSFile(File f) throws IOException,
			ParseException {

		System.out.println("Parsing file: " + f.getName());
		ArrayList<Tweet> Tweets = new ArrayList<Tweet>();
		JSONParser parser = new JSONParser();

		BufferedReader in = new BufferedReader(new FileReader(f));
		String zeile = null;
		String JSONString = "";
		System.out.println("Lese Datei ein.");
		JSONString = FileUtils.readFileToString(f);

		System.out.println(f.getName().replace(".js", ""));
		JSONString = JSONString.replace("Grailbird.data.tweets_"
				+ f.getName().replace(".js", "") + " = ", "");
		
		Object obj;

		obj = parser.parse(JSONString);
		JSONArray jarray = (JSONArray) obj;
		System.out.println("Debug: ArraySize (Tweets in that month) : "
				+ jarray.size());
		System.out.println("");
		for (int i = 0; i < jarray.size(); i++) {
			JSONObject temp = (JSONObject) jarray.get(i);
			Tweet tweet = new Tweet(temp.get("text").toString()
					.replace("&gt;", ">").replace("&", "&amp;")
					.replace("\n", " "), temp.get("created_at").toString());
			Tweets.add(tweet);
		}
		Collections.sort(Tweets);

		return Tweets;
	}
}
