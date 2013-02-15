import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.xml.bind.JAXBException;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.json.simple.parser.ParseException;

public class TwitterToWord {

	/**
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		// ArgsHandling
		
		if (args.length != 2) { System.out.println("I need two arguments. \n" +
				"User has to specify source directory (eg. PATH-TO-UNZIPPED-FILES/data/js/tweets/) \n" +
				"User has to specify destination directory and filename (eg. PATH-TO-DESTINATIONDIR/tweets.docx)");
		System.exit(1);
		}
		
		long start = System.currentTimeMillis();

	

		File f = new File(
				args[0]);
		String WordFileNameAndPath =args[1];

		if (f.isDirectory()) {
			if (WordFileNameAndPath.substring(WordFileNameAndPath.length() - 5)
					.equals(".docx")) {
				FileHandler fh = new FileHandler();

				FileParser fp = new FileParser();
				ArrayList<Tweet> Tweets = new ArrayList<Tweet>();

				for (File file : fh.getFiles(f)) {
					Tweets.addAll((fp.parseJSFile(file)));
				}

				System.out.println("Anzahl Tweets gesamt: " + Tweets.size());
				System.out.println("Duration in ms: "
						+ (System.currentTimeMillis() - start));
				System.out.println(Tweets.get(0).Date + " "
						+ Tweets.get(Tweets.size() - 1).Date);
				

				DocxGenerator dg = new DocxGenerator();
				try {
					dg.generateDocx(Tweets, WordFileNameAndPath);
				} catch (Docx4JException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else
				System.out.println("This does not seem to be a docx Filename!");

		} else
			System.out.println("This is not a valid directory!");

	}
}
