import java.awt.Graphics;

public class Room {
	private int worldSizeGeneral = 10;
	public int worldWidth = worldSizeGeneral * 2;
	public int worldHeight = worldSizeGeneral;
	public int blockSize = 56;
	
	public Block[][] block;
	
	public Room() {
		define();
	}
	
	public void define() {
		block = new Block[worldHeight][worldWidth];

		if(Frame.BigRes) {
			blockSize *= 2;
		}
		
		for(int y=0; y < block.length; y++) {
			for(int x=0; x < block[0].length; x++) {
				block[y][x] = new Block((Screen.myWidth/2) - ((worldWidth*blockSize)/2) + (x * blockSize), y * blockSize, blockSize, blockSize, Value.groundGrass, Value.airAir);
			}
		}
	}
	
	public void physic() {
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x].physic();
			}
		}
	}
	
	public void draw(Graphics g) {
		for(int y=0; y < block.length; y++) {
			for(int x=0; x < block[0].length; x++) {
				block[y][x].draw(g);
			}
		}
		
		for(int y=0; y < block.length; y++) {
			for(int x=0; x < block[0].length; x++) {
				block[y][x].fight(g);
			}
		}
	}
}
