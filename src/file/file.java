package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class file {

	static String[] array = new String[main.Final.StringArrayMaxSize];

	public static String[] ToStringArray(String absolutePath) {

		String line = null, fileName = absolutePath;
		int linecounter = 0;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				array[linecounter++] = line;
			}
			String[] StringArray = new String[linecounter];
			for (int i = 0; i < linecounter; i++) {
				StringArray[i] = array[i];
			}
			// Always close files.
			bufferedReader.close();
			return StringArray;
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		return null;
	}

}
