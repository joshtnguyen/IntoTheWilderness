package block;

import main.FileManager;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;

import java.awt.Graphics2D;

import main.GamePanel;

public class BlockManager {
	
	final int heightLimit = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "heightLimit")); // Total Map Height Block Limit
	final int widthLimit = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "widthLimit")); // Total Map Width Block Limit
	final int scale = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "scale"));
	final int blockSize = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "originalBlockSize")) * scale;
	final int renderDistance = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "renderDistance"));
	final int screenWidth = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "screenWidth"));
	final int screenHeight = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "screenHeight"));
	
	GamePanel gp;
	Block[] block;
	int[][] world = new int[widthLimit][heightLimit];
	int[][] rendered = new int[renderDistance][renderDistance];
	
	public BlockManager(GamePanel gp) {
		
		this.gp = gp;
		
		block = new Block[3];
		for (int i = 0; i < widthLimit; i += 2) {
			if (i % 4 == 0) {
				world[i][130] = 2;
			} else {
				world[i][130] = 1;
			}
			
		}
		for (int i = 0; i < widthLimit; i ++) {
			world[i][131] = 2;
			world[i][0] = 2;
		}
		
		getBlockImage();
		
	}
	
	public void getBlockImage() {
		
		try {
			
			block[0] = new Block();
			block[0].image = ImageIO.read(getClass().getResourceAsStream("/blocks/air.png"));
			
			block[1] = new Block();
			block[1].image = ImageIO.read(getClass().getResourceAsStream("/blocks/grass.png"));
			
			block[2] = new Block();
			block[2].image = ImageIO.read(getClass().getResourceAsStream("/blocks/dirt.png"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void draw(Graphics2D g2, Player p) {
		
		int onBlockX = p.x / blockSize;
		int onBlockY = p.y / blockSize;
		
		int bx = onBlockX - (renderDistance / 2);
		int by = onBlockY - (renderDistance / 2);
		
		int displacementX = p.x % blockSize;
		int displacementY = p.y % blockSize;
		
		System.out.println(bx + " / " + by);
		
		for (int y = 0; y < renderDistance; y++) {
			
			for (int x = 0; x < renderDistance; x++) {
				
				if (bx >= 0 && bx < widthLimit && by >= 0 && by < heightLimit) {
					rendered[x][y] = world[bx][by];
					if (bx == onBlockX && by == onBlockY) {
						rendered[x][y] = 9;
					}
					g2.drawImage(block[world[bx][by]].image, x * blockSize - displacementX + ((screenWidth - renderDistance * blockSize) / 2), y * blockSize - displacementY + ((screenHeight - renderDistance * blockSize) / 2), blockSize, blockSize, null);
				}
				
				bx++;
				
			}
			
			bx -= renderDistance;
			by++;
			
		}
		
		for (int y = 0; y < renderDistance; y++) {
			String t = "";
			for (int x = 0; x < renderDistance; x++) {
				t += rendered[x][y] + " ";
			}
			System.out.println(t);
		}
		
		
	}

}
