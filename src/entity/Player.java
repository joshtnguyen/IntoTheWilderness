package entity;

import java.awt.Color;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
	}
	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 2;
		
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
		}
		
		if (keyH.rightPressed == true) {
			x += speed;
		}
		
	}
	
	public void draw() {
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
	}
	
}
