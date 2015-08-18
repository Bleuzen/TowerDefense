import java.io.File;
import java.util.Scanner;


public class Save {
	private Scanner loadScanner;

	public void loadSave(File loadPath) {
		try {
			
			if(loadPath.exists()) {
				
				loadScanner = new Scanner(loadPath);
				
				while(loadScanner.hasNext()) {

					Screen.spawnTime = loadScanner.nextInt();
					Mob.walkSpeed = (loadScanner.nextInt());
					Screen.killsToWin = loadScanner.nextInt();
					Block.lessMoney = loadScanner.nextInt();
					Screen.coinage = loadScanner.nextInt();
					
					Store.buttonPrice[0] = loadScanner.nextInt();
					
					Value.deathReward[0] = loadScanner.nextInt();
					
					for(int y = 0; y < Screen.room.block.length; y++) {
						for(int x = 0; x < Screen.room.block[0].length; x++) {
							Screen.room.block[y][x].groundID = loadScanner.nextInt();
						}
					}
					
					for(int y = 0; y < Screen.room.block.length; y++) {
						for(int x = 0; x < Screen.room.block[0].length; x++) {
							Screen.room.block[y][x].airID = loadScanner.nextInt();
						}
					}
				}
				
				loadScanner.close();
				
			} else {
				Screen.isGameEnding = true;
			}
	
		} catch (Exception e) {
			System.err.println("Fehler: " + e.getMessage());
			System.exit(100);
		}
	}
}
