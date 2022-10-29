package main;

import java.io.FileNotFoundException;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import block.BlockManager;
import entity.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable {
	
	// SCREEN SETTINGS
	final int originalBlockSize = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "originalBlockSize")); // 16x16 block
	public final int scale = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "scale"));
	
	
	public final int blockSize = originalBlockSize * scale; // 64x64 block
	final int maxScreenCol = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "maxScreenCol"));
	final int maxScreenRow = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "maxScreenRow"));
	final int screenWidth = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "screenWidth"));
	final int screenHeight = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "screenHeight"));
	
	// FPS
	int FPS = 120;
	int currentFPS;
	
	public final int gravityConstant = Integer.parseInt(FileManager.getOption("options/mapoptions.txt", "gravityConstant"));
	
	BlockManager blockW = new BlockManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	Player player = new Player(this, keyH);
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.red);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >- 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				currentFPS = drawCount;
				drawCount = 0;
				timer = 0;
			}
			
		}
		
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		blockW.draw(g2, player);
		player.draw(g2);
		
		
		//if (keyH.f3Pressed) {
			int debugX = 16;
			int debugY = 12;
			
			g.drawString("FPS: " + currentFPS, debugX, debugY += 12);
			g.drawString("Pixel Map x: " + player.x, debugX, debugY += 12);
			g.drawString("Pixel Map y: " + player.y, debugX, debugY += 12);
			g.drawString("Block Map x: " + player.x / blockSize, debugX, debugY += 12);
			g.drawString("Block Map y: " + player.y / blockSize, debugX, debugY += 12);
			g.drawString("Game Block Map x: " + ((player.x / blockSize) - (blockW.widthLimit / 2)), debugX, debugY += 12);
			g.drawString("Game Block Map y: " + (blockW.heightLimit - (player.y / blockSize)), debugX, debugY += 12);
		//}
		
		
		g2.dispose();
		
	}

}
