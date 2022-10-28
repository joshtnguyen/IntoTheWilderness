package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	public void checkBlock(Entity entity) {
		
		int entityLeftX = entity.x - entity.hb_w * gp.scale / 2;
		int entityRightX = entity.x + entity.hb_w * gp.scale / 2;
		int entityTopY = entity.y + entity.hb_h * gp.scale / 2;
		int entityBottomY = entity.y + entity.hb_h * gp.scale / 2;
		
		int entityLeftCol = entityLeftX/gp.blockSize;
		int entityRightCol = entityRightX/gp.blockSize;
		int entityTopRow = entityTopY/gp.blockSize;
		int entityBottomRow = entityBottomY/gp.blockSize;
		
		int block1, block2;
		
		switch(entity.direction) {
		case "left":
			entityLeftCol = (entityLeftX - entity.speed) / gp.blockSize;
			block1 = gp.blockW.world[entityLeftCol][entityTopRow];
			block2 = gp.blockW.world[entityLeftCol][entityBottomRow];
			if(gp.blockW.block[block1].collision == true || gp.blockW.block[block2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightX - entity.speed) / gp.blockSize;
			block1 = gp.blockW.world[entityRightCol][entityTopRow];
			block2 = gp.blockW.world[entityRightCol][entityBottomRow];
			System.out.println(block1);
			if(gp.blockW.block[block1].collision == true || gp.blockW.block[block2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
		
	}

}
