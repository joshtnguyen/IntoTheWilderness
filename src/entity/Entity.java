package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;
	public int speed;
	public int hb_w, hb_h;
	public int velocityY;
	public int fallingTime;
	public int jumpVelocity;
	
	public BufferedImage left1, left2, left3, left4, right1, right2, right3, right4;
	public String direction;
	
	public int spriteNumber = 1;
	public int spriteCounter = 0;
	
	public Rectangle solidArea;
	public boolean collisionOn = false;
	
}
