package newsom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	public static String readFileContents(String filePath) {
		 String path = filePath;
		 String contents = "";

	        // Try-with-resources automatically closes both readers
	        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                contents += line + "\n";
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading the file: " + e.getMessage());
	        }
	        
	        return contents;
	}
}
