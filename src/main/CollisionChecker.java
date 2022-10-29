package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	int heightLimit = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "heightLimit"));
	int widthLimit = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "widthLimit"));
	
	public CollisionChecker(GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	public boolean isOnGround(Entity entity) {
		
		int entityLeftX = entity.x - entity.hb_w;
		int entityRightX = entity.x + entity.hb_w - 4;
		int entityMiddleX = entity.x;
		int entityBottomY = entity.y + entity.hb_h * gp.scale / 2;
		
		int entityLeftCol = entityLeftX/gp.blockSize;
		int entityRightCol = entityRightX/gp.blockSize;
		int entityMiddleCol = entityMiddleX/gp.blockSize;
		int entityBottomRow = entityBottomY/gp.blockSize;
		
		int block1 = 0;
		int block2 = 0;
		int block3 = 0;
		
		if (entity.velocityY <= 0) {
			entityBottomRow = (entityBottomY + 2) / gp.blockSize;
			if (entityBottomRow >= 0 && entityBottomRow < heightLimit) {
				if (entityLeftCol >= 0 && entityLeftCol < widthLimit) {
					block1 = gp.blockW.world[entityLeftCol][entityBottomRow];
				}
				if (entityRightCol >= 0 && entityRightCol < widthLimit) {
					block2 = gp.blockW.world[entityRightCol][entityBottomRow];
				}
				if (entityMiddleCol >= 0 && entityMiddleCol < widthLimit) {
					block3 = gp.blockW.world[entityMiddleCol][entityBottomRow];
				}
				if (gp.blockW.block[block1].collision == true || gp.blockW.block[block2].collision == true || gp.blockW.block[block3].collision == true) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public void checkBlock(Entity entity) {
		
		int entityLeftX = entity.x - entity.hb_w;
		int entityRightX = entity.x + entity.hb_w;
		int entityTopY = entity.y - entity.hb_h * gp.scale / 2;
		int entityBottomY = entity.y + entity.hb_h * gp.scale / 2 - 5;
		int entityMiddleY = entity.y;
		
		int entityLeftCol = entityLeftX/gp.blockSize;
		int entityRightCol = entityRightX/gp.blockSize;
		int entityTopRow = entityTopY/gp.blockSize;
		int entityBottomRow = entityBottomY/gp.blockSize;
		int entityMiddleRow = entityMiddleY/gp.blockSize;
		
		int block1 = 0;
		int block2 = 0;
		int block3 = 0;
		
		switch(entity.direction) {
		case "left":
			entityLeftCol = (entityLeftX - 1) / gp.blockSize;
			if (entityLeftCol >= 0 && entityLeftCol < widthLimit) {
				if (entityTopRow >= 0 && entityTopRow < heightLimit) {
					block1 = gp.blockW.world[entityLeftCol][entityTopRow];
				}
				if (entityBottomRow >= 0 && entityBottomRow < heightLimit) {
					block2 = gp.blockW.world[entityLeftCol][entityBottomRow];
				}
				if (entityMiddleRow >= 0 && entityMiddleRow < heightLimit) {
					block3 = gp.blockW.world[entityLeftCol][entityMiddleRow];
				}
				if(gp.blockW.block[block1].collision == true || gp.blockW.block[block2].collision == true || gp.blockW.block[block3].collision == true) {
					entity.collisionOn = true;
				}
			}
			break;
		case "right":
			entityRightCol = (entityRightX - 1) / gp.blockSize;
			if (entityRightCol >= 0 && entityRightCol < widthLimit) {
				if (entityTopRow >= 0 && entityTopRow < heightLimit) {
					block1 = gp.blockW.world[entityRightCol][entityTopRow];
				}
				if (entityBottomRow >= 0 && entityBottomRow < heightLimit) {
					block2 = gp.blockW.world[entityRightCol][entityBottomRow];
				}
				if (entityMiddleRow >= 0 && entityMiddleRow < heightLimit) {
					block3 = gp.blockW.world[entityRightCol][entityMiddleRow];
				}
				if(gp.blockW.block[block1].collision == true || gp.blockW.block[block2].collision == true || gp.blockW.block[block3].collision == true) {
					entity.collisionOn = true;
				}
			}
			break;
		}
		
	}

}
