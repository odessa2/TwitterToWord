import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FileHandler {

	ArrayList<File> getFiles(File f) {
		ArrayList<File> JSFileList = new ArrayList<File>();
		if (f.isDirectory()) {

			File[] listOfFiles = f.listFiles();

			System.out.println("Found " + listOfFiles.length
					+ " Files in the specified directory.");
			if (listOfFiles != null) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].getName().contains(".js"))
						JSFileList.add((listOfFiles[i]));
				}
			}
			Collections.sort(JSFileList);

		}
		return JSFileList;
	}
}
