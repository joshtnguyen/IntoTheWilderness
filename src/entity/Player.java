package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
	private boolean moving = false;

	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		speed = 4;
		hb_w = 10;
		hb_h = 30;
		int scale = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "scale"));
		int blockSize = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "originalBlockSize"));
		center_x = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "screenWidth")) / 2 - (hb_w * scale / 2);
		center_y = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "screenHeight")) / 2 - (hb_h * scale / 2);
		direction = "right";
		x = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "widthLimit")) / 2 * scale * blockSize;
		y = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "heightLimit")) / 2 * scale * blockSize;
		jumpVelocity = 8;
		
		solidArea = new Rectangle(0, 0, hb_w, hb_h);

		
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
	
	public void applyGravity() {
		fallingTime++;
		y -= velocityY;
		velocityY -= fallingTime / 15;
		if (velocityY <= -1 * gp.gravityConstant) {
			velocityY = -1 * gp.gravityConstant;
		}
	}
	
	public void update() {
				
		moving = false;
		
		if (keyH.upPressed == true) {
			y -= speed;
		}
		
		if (keyH.downPressed == true) {
			y += speed;
		}
		
		if (keyH.leftPressed == true) {
			direction = "left";
			moving = true;
		}
		
		if (keyH.rightPressed == true) {
			direction = "right";
			moving = true;
		}
		
		if (keyH.spacePressed == true) {
			interact = true;
			//speed = 8;
		} else {
			interact = false;
			//speed = 2;
		}
		
		if (gp.cChecker.isOnGround(this)) {
			y = y / gp.blockSize * gp.blockSize + 1;
			velocityY = 0;
			fallingTime = 0;
			if (interact) {
				velocityY = jumpVelocity;
				applyGravity();
			}
		} else {
			applyGravity();
		}
		
		// CHECK COLLISION
		collisionOn = false;
		gp.cChecker.checkBlock(this);
		
		if (collisionOn) {
			switch(direction) {
			case "left":
				x = x / gp.blockSize * gp.blockSize + hb_w;
				break;
			case "right":
				x = x / gp.blockSize * gp.blockSize + 67 - hb_w;
				break;
			}
		} else if (moving) {
			switch (direction) {
			case "left":
				x -= speed;
				break;
			case "right":
				x += speed;
				break;
			}
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
				visual_y -= 2;
			}
		}
		
		g2.drawImage(image, visual_x, visual_y, hb_w * gp.scale, hb_h * gp.scale, null);
		
	}
	
}
