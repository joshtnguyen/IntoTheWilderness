package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.FileManager;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	private int center_x;
	private int center_y;
	private boolean interact = false;

	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		speed = 2;
		hb_w = 10;
		hb_h = 30;
		int scale = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "scale"));
		int blockSize = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "originalBlockSize"));
		center_x = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "screenWidth")) / 2 - (hb_w * scale / 2);
		center_y = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "screenHeight")) / 2 - (hb_h * scale / 2);
		direction = "right";
		x = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "widthLimit")) / 2 * scale * blockSize;
		y = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "heightLimit")) / 2 * scale * blockSize;

		
	}
	
	public void getPlayerImage() {
		
		try {
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_left_2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_left_3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_left_4.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_right_2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_right_3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_right_4.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		if (keyH.upPressed == true) {
			y -= speed;
		}
		
		if (keyH.downPressed == true) {
			y += speed;
		}
		
		if (keyH.leftPressed == true) {
			x -= speed;
			direction = "left";
		}
		
		if (keyH.rightPressed == true) {
			x += speed;
			direction = "right";
		}
		
		if (keyH.spacePressed == true) {
			interact = true;
			speed = 8;
		} else {
			interact = false;
			speed = 2;
		}
		
		spriteCounter++;
		if (spriteCounter >= 18) {
			spriteCounter = 0;
			spriteNumber++;
			if (spriteNumber >= 5) {
				spriteNumber = 1;
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
						
		BufferedImage image = null;
		int visual_x = center_x;
		int visual_y = center_y;
		
		switch(direction) {
		case "right":
			if (spriteNumber == 1) {
				image = right1;
			} else if (spriteNumber == 2) {
				image = right2;
			} else if (spriteNumber == 3) {
				image = right3;
			} else {
				image = right4;
			}
			break;
		case "left":
			if (spriteNumber == 1) {
				image = left1;
			} else if (spriteNumber == 2) {
				image = left2;
			} else if (spriteNumber == 3) {
				image = left3;
			} else {
				image = left4;
			}
			break;
		}
		
		if (interact) {
			if (spriteNumber == 1 || spriteNumber == 3) {
				visual_y += 2;
			}
		}
		
		g2.drawImage(image, visual_x, visual_y, hb_w * gp.scale, hb_h * gp.scale, null);
		
	}
	
}
