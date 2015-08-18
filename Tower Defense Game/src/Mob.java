import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Mob extends Rectangle {
	public int xC, yC;
	public int health, maxHealth = 100;
	public int healthSpace = 2, healthHeight = 5;
	public int mobSize = Screen.room.blockSize;
	public int mobID = Value.airAir;
	
	public int mobWalk = 0;
	public int upward = 0, downward = 1, right = 2, left = 3;
	public int direction = right;
	
	public static int walkSpeed;
	public static int walkSpeedAfterHalf;

	public boolean inGame = false;
	public boolean hasUpward = false;
	public boolean hasDownward = false;
	public boolean hasLeft = false;
	public boolean hasRight = false;
	
	public Mob() {
		if(Frame.BigRes) {
			healthSpace *= 2;
			healthHeight *= 2;
		}
	}
	
	public void spawnMob(int mobID) {
		for(int y = 0; y < Screen.room.block.length; y++) {
			if(Screen.room.block[y][0].groundID == Value.groundRoad) {
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, mobSize, mobSize);
				xC = 0;
				yC = y;
				
				direction = right;
				health = maxHealth;
				break;
			}
		}
		
		this.mobID = mobID;
		
		inGame = true;
	}
	
	public void deleteMob() {
		inGame = false;
		direction = right;
	}
	
	public void mobOnEndReached() {
		Screen.health -= 1;
		
		if(Screen.killsToWin > Block.lessMoney) {
			if(Math.random() > 0.50) {
				Screen.coinage += 1;	
			}	
		}
	}
	
	public void loseHealth(int amo) {
		if(Frame.BigRes) {
			health -= amo * 2;
		} else {
			health -= amo;	
		}
		
		checkDeath();
	}
	
	public void checkDeath() {
		if(health <= 0) {
			deleteMob();
			Screen.killed++;
			Screen.room.block[0][0].getMoney(mobID);
		}
	}
	
	public boolean isDead() {
		if(inGame) {
			return false;
		} else {
			return true;
		}
	}
	
	public int walkFrame = 0;
	public void physic() {
		
		if(walkSpeed > 0) {
			
				if(walkFrame >= walkSpeed) {
					if(direction == right) {
						x += 1;		
					} else if(direction == upward) {
						y -= 1;
					} else if(direction == downward) {
						y += 1;
					} else if(direction == left) {
						x -= 1;
					}
					
					mobWalk += 1;
					
					if(mobWalk == Screen.room.blockSize) {
						if(direction == right) {
							xC += 1;	
							hasRight = true;
						} else if(direction == upward) {
							yC -= 1;
							hasUpward = true;
						} else if(direction == downward) {
							yC += 1;
							hasDownward = true;
						} else if(direction == left) {
							xC -= 1;
							hasLeft = true;
						}
						
						try {
							
							if(!hasUpward) {
								int realYC = yC;
								if(Screen.room.block[yC].length < realYC) {
									realYC += 1;
								}
								if(Screen.room.block[realYC][xC].groundID == Value.groundRoad) {
									direction = downward;
								}	
							}
							
							if(!hasDownward) {
								// Problem
								int realYC = yC;
								if(realYC >= 1) {
									realYC -= 1;
								}
								//TODO
								System.out.println(realYC);
								if(Screen.room.block[realYC][xC].groundID == Value.groundRoad) {
									direction = upward;
								}	
							}
							
							if(!hasLeft) {
								if(Screen.room.block[yC][xC+1].groundID == Value.groundRoad) {
									direction = right;
								}	
							}
							
							if(!hasRight) {
								if(Screen.room.block[yC][xC-1].groundID == Value.groundRoad) {
									direction = left;
								}	
							}
							
						} catch(Exception e) {
							//TODO
							e.printStackTrace();
						}
						
						if(Screen.room.block[yC][xC].airID == Value.airCave) {
							deleteMob();
							mobOnEndReached();
						}
						
						hasUpward = false;
						hasDownward = false;
						hasLeft = false;
						hasRight = false;
						mobWalk -= Screen.room.blockSize;
					}
					
			        if(Frame.BigRes) {
						walkFrame = walkSpeed / 2 + 1;	
			        } else {
						walkFrame = 1;
			        }
				} else {
					walkFrame += 1;
				}
				
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_mob[mobID], x, y, width, height, null);
		
		//Health bar
		g.setColor(new Color(180, 50, 50));
		g.fillRect(x, y - healthSpace - healthHeight, mobSize, healthHeight);

		g.setColor(new Color(50, 180, 50));
		g.fillRect(x, y - healthSpace - healthHeight, mobSize * health / maxHealth, healthHeight);

		g.setColor(new Color(0, 0, 0));
		g.drawRect(x, y - healthSpace - healthHeight, mobSize, healthHeight);
	}
}
