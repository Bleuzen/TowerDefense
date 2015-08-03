import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TextFileWriter {

	public static void writeLineinTextFile(String Name, String Text) {
		String filename = Name;
		try {
			File saveFile = new File(filename);
	        PrintWriter pWriter = new PrintWriter(new BufferedWriter(new FileWriter(saveFile)));
	            pWriter.println(Text);	
	            if (pWriter != null){
	                pWriter.flush(); 
	                pWriter.close();
	            }
		} catch (Exception e) {
			System.err.println("Fehler: " + e.getMessage());
		}
	}	
	
}
