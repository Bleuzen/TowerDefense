import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Store {
	public static int shopWidth = 9;
	public static int buttonSize = 64;
	public static int cellSpace = 3;
	public static int awayFromRoom = 28;
	public static int iconSize = 25;
	public static int iconSpace = 7;
	public static int iconTextY = 16;
	public static int TextSize = 14;
	public static int itemIn = 4;
	public static int heldID = -1;
	public static int realID = -1;
	public static int[] buttonID = {Value.airTowerLaser, Value.airAir, Value.airAir, Value.airAir, Value.airAir, Value.airAir, Value.airAir, Value.airAir, Value.airTrashCan};
	public static int[] buttonPrice = {10, 0, 0, 0, 0, 0, 0, 0, 0};
	
	public Rectangle[] button = new Rectangle[shopWidth];
	public Rectangle buttonHealth;
	public Rectangle buttonCoins;
	public Rectangle buttonKillsToWin;	
	public Rectangle buttonLevel;
	
	public boolean holdsItem = false;
	
	public static int towerCounter;
	
	public Store() {
		define();
	}
	
	public void click(int mouseButton) {
		if(mouseButton == 1) {
			for(int i = 0; i < button.length; i++) {
				if(button[i].contains(Screen.mse)) {
					if(buttonID[i] != Value.airAir) {
						if(buttonID[i] == Value.airTrashCan) {
							canclePlace();
						} else {
							heldID = buttonID[i];
							realID = i;
							holdsItem = true;
						}	
					}
				}
			}
			
			if(holdsItem) {
				if(Screen.coinage >= buttonPrice[realID]) {
					for(int y = 0; y < Screen.room.block.length; y++) {
						for(int x = 0; x < Screen.room.block[0].length; x++) {
							if(Screen.room.block[y][x].contains(Screen.mse)) {
								if(Screen.room.block[y][x].groundID != Value.groundRoad && Screen.room.block[y][x].airID == Value.airAir) {
									Screen.room.block[y][x].airID = heldID;
									Screen.coinage -= buttonPrice[realID];
									towerCounter++;
									canclePlace();
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void canclePlace() {
		holdsItem = false;
		heldID = Value.airAir;
	}
	
	public void define() {
		if(Frame.BigRes) {
			buttonSize *= 2;
			cellSpace *= 2;
			awayFromRoom *= 2;
			iconSize *= 2;
			iconSpace *= 2;
			iconTextY *= 2;
			TextSize *= 2;
			itemIn *= 2;
		}
		for(int i = 0; i < button.length; i++) {
			button[i] = new Rectangle((Screen.myWidth/2) - ((shopWidth*(buttonSize+cellSpace))/2) + ((buttonSize+cellSpace)*i), (Screen.room.block[Screen.room.worldHeight-1][0].y) + Screen.room.blockSize + awayFromRoom, buttonSize, buttonSize);
		}
		
		buttonHealth = new Rectangle(Screen.room.block[0][0].x - 1, button[0].y, iconSize, iconSize);
		buttonCoins = new Rectangle(Screen.room.block[0][0].x - 1, button[0].y + button[0].height-iconSize, iconSize, iconSize);
		buttonKillsToWin = new Rectangle(Screen.room.block[0][new Room().worldWidth-1].x - buttonSize, button[0].y, iconSize, iconSize);
		buttonLevel = new Rectangle(Screen.room.block[0][new Room().worldWidth-1].x - buttonSize, button[0].y + button[0].height-iconSize, iconSize, iconSize);
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < button.length; i++) {
			if(button[i].contains(Screen.mse)) {
				g.setColor(new Color(255, 255, 245, 90));
				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}
			
			g.drawImage(Screen.tileset_res[0], button[i].x, button[i].y, button[i].width, button[i].height, null);
			if(buttonID[i] != Value.airAir) {
				g.drawImage(Screen.tileset_air[buttonID[i]], button[i].x + itemIn, button[i].y + itemIn, button[i].width - (itemIn*2), button[i].height - (itemIn*2), null);
			}	
			if(buttonPrice[i] > 0) {
				g.setColor(Color.YELLOW);
				g.setFont(new Font("Courier New", Font.BOLD, TextSize));
				g.drawString(String.valueOf(buttonPrice[i]), button[i].x + itemIn, button[i].y + itemIn + TextSize);	
			}
		}
		
		g.drawImage(Screen.tileset_res[1], buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null);
		g.drawImage(Screen.tileset_res[2], buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height, null);
		g.drawImage(Screen.tileset_mob[Value.mobGreeny], buttonKillsToWin.x, buttonKillsToWin.y, buttonKillsToWin.width, buttonKillsToWin.height, null);
		g.drawImage(Screen.tileset_res[3], buttonLevel.x, buttonLevel.y, buttonLevel.width, buttonLevel.height, null);
		
		g.setFont(new Font("Dialog", Font.BOLD, TextSize));
		g.setColor(new Color(250, 255, 250));
		
		g.drawString(String.valueOf(Screen.health), buttonHealth.x + buttonHealth.width + iconSpace, buttonHealth.y + iconTextY);
		g.drawString(String.valueOf(Screen.coinage), buttonCoins.x + buttonCoins.width + iconSpace, buttonCoins.y + iconTextY);
		g.drawString(String.valueOf(Screen.killsToWin - Screen.killed), buttonKillsToWin.x + buttonKillsToWin.width + iconSpace, buttonKillsToWin.y + iconTextY);
		g.drawString(String.valueOf(Screen.startingLevel), buttonLevel.x + buttonLevel.width + iconSpace, buttonLevel.y + iconTextY);
		
		if(holdsItem) {
			g.drawImage(Screen.tileset_air[heldID], Screen.mse.x - ((button[0].width - (itemIn*2))/2) + itemIn, Screen.mse.y - ((button[0].width - (itemIn*2))/2) + itemIn, button[0].width - (itemIn*2), button[0].height - (itemIn*2), null);
		}
	}
}
