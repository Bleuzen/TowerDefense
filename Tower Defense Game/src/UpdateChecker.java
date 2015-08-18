

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateChecker {
	
	static String link = "https://www.dropbox.com/s/zogmmqrxzo2tiz7/onlineVersion?dl=1";
	
	String downloadLink = "https://bitbucket.org/Bleuzen/bleuzen-s-tower-defense/downloads/BTowerDefenseSetup.exe";
	
	static String version;
	
	public static boolean updateNeeded() {
		
		boolean update = false;
		
		try {
			
			URL url = new URL(link);
			URLConnection con = url.openConnection();
			InputStreamReader reader = new InputStreamReader(con.getInputStream());
			BufferedReader buffer = new BufferedReader(reader);
			String text = buffer.readLine();
			version = text.replaceAll("[a-zA-Z]", "").replace(".", "");
			
			int localVersion = Integer.valueOf(InfoFrame.version.replaceAll("[a-zA-Z]", "").replace("/", "").replace(".", ""));
			int onlineVersion = Integer.valueOf(version);	
			
			if(onlineVersion > localVersion) {
				update = true;	
			}
		} catch (Exception e) {
			System.err.println("Fehler");
		}
		
		return update;
	}
	
}
