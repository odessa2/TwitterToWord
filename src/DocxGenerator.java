import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.P;

public class DocxGenerator {
	private String generateTweetParagraphXML(String Text, String Date) {
		String XML = "<w:p  xmlns:w =\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"006D65F3\" w:rsidRDefault=\"00CC6839\"><w:r><w:t>[Tweet]</w:t></w:r><w:r><w:br/><w:t>[Date]</w:t></w:r><w:r><w:br/><w:t>FILLER</w:t></w:r></w:p>";

		return XML.replace("[Tweet]", Text).replace("[Date]", Date)
				.replace("FILLER", "---------------------------------");

	}

	private P generateTweetParagraph(Tweet t) {

		org.docx4j.wml.P paragraph = null;
		try {
			paragraph = (P) XmlUtils.unmarshalString(generateTweetParagraphXML(
					t.Text, t.Date));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Tweet: " + t.Text);

			e.printStackTrace();
		}

		return paragraph;

	}

	void generateDocx(ArrayList<Tweet> Tweets, String FilenName)
			throws Docx4JException, JAXBException {

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
				.createPackage();

		for (Tweet t : Tweets) {

			wordMLPackage.getMainDocumentPart().addObject(
					generateTweetParagraph(t));

		}

		wordMLPackage.save(new java.io.File(FilenName));

	}

}
