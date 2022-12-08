package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.Superobject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	// resolution setting 1280 x 960
	public final int originalTileSize = 16;// 16 x 16
	final int scale = 4;

	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 15;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	
	///WORLD SETTING
	public final int maxWorldCol=50;
	public final int maxWorldRow=50;
	public final int worldWidth=tileSize*maxWorldCol;
	public final int worldHeight=tileSize*maxWorldRow;
	TileManager tileM=new TileManager(this);
	KeyboardControl keyboard = new KeyboardControl();
	Thread gameThread;
	public CollisionChecker colChecker= new CollisionChecker(this);
	public AssetSetup aSetup=new AssetSetup(this);
	public Player player=new Player(this,keyboard);
	public Superobject object[] = new Superobject[10];
	
	
	// player start position
	int playerX = 100;
	int playerY = 100;
	double playerSpeed = 1;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyboard);
		this.setFocusable(true);

	}
	public void setupGame() {
		aSetup.setObject();
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public int FPS = 60;

	@Override

	public void run() {
		double drawInterval = 100000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		// running program all along program
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta >= 1) {
				update();// update
				repaint();// redraw
				delta--;
			}

		}
	}

	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//TILE
		tileM.draw(g2);
		//OBJECT
		for(int i=0;i<object.length;i++) {
			if(object[i] !=null) {
				object[i].draw(g2, this);
			}
		}
		//PLAYER
		player.draw(g2);
		g2.dispose();
	}
}
