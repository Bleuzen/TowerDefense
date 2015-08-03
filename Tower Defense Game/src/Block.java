import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


@SuppressWarnings("serial")
public class Block extends Rectangle {
	private static boolean isFirst = true;
	public static int towerSqareSize = 112; //In Mission
	public Rectangle towerSqare;
	public int groundID;
	public int airID;
	public int loseTime = 80, loseFrame = 0;
	
	public static int lessMoney;
	
	public int shotMob = -1;
	public boolean shooting = false;
	
	public Block(int x, int y, int width, int height, int groundID, int airID) {
		if(isFirst) {
			isFirst = false;
			if(Frame.BigRes) {
				towerSqareSize *= 2;
			}
		}
		setBounds(x, y, width, height);
		towerSqare = new Rectangle(x - (towerSqareSize/2), y - (towerSqareSize/2), width + towerSqareSize, height + towerSqareSize);
		this.groundID = groundID;
		this.airID = airID;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_ground[groundID], x, y, width, height, null);
		
		if(airID != Value.airAir) {
			g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);
		}
	}
	
	public void physic() {
		if (shotMob != -1 && towerSqare.intersects(Screen.mobs[shotMob])) {
			shooting = true;
		} else {
			shooting = false;
		}
		
		if(!shooting) {
			//Neue Tower hier hinzufügen
			if (airID == Value.airTowerLaser) {
				for (int i = 0; i < Screen.mobs.length; i++) {
					if (Screen.mobs[i].inGame) {
						if (towerSqare.intersects(Screen.mobs[i])) {
							shotMob = i;
						}
					}
				}
			}
		}	
		
		if(shooting) {
			if(loseFrame >= loseTime) {
				//IDEE
				/*
				 * Dunkles Graß = doppelter Schaden
				 */
				Screen.mobs[shotMob].loseHealth(1);
				
				loseFrame = 0;
			} else {
				loseFrame += 1;
			}
			
			if(Screen.mobs[shotMob].isDead()) {
				shooting = false;
				shotMob = -1;
				
				//Screen.killed++;
				
				Screen.hasWon();
			}	
		}
	}

	public void getMoney(int mobID) {
		if(Store.towerCounter >= lessMoney) {
			Screen.coinage += (Value.deathReward[mobID] / (Store.towerCounter / lessMoney));	
		} else {
			Screen.coinage += (Value.deathReward[mobID]);
		}
	}
	
	public void fight(Graphics g) {
		//DEBUG
		/*if(airID == Value.airTowerLaser) {
			g.drawRect(towerSqare.x, towerSqare.y, towerSqare.width, towerSqare.height);
		}*/
		
		if(shooting) {
			g.setColor(Color.YELLOW);
			g.drawLine(x + (width/2), y + (height/2), Screen.mobs[shotMob].x + (Screen.mobs[shotMob].width/2), Screen.mobs[shotMob].y + (Screen.mobs[shotMob].height/2));
			if(Frame.BigRes) {
				g.drawLine(x + (width/2) + 1, y + (height/2) + 1, Screen.mobs[shotMob].x + (Screen.mobs[shotMob].width/2) + 1, Screen.mobs[shotMob].y + (Screen.mobs[shotMob].height/2) + 1);	
			}
		}
	}
}
