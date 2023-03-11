package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.Superobject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	// resolution setting 1280 x 960
	public final int originalTileSize = 16;// 16 x 16
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	/// WORLD SETTING
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	// TILE
	TileManager tileM = new TileManager(this);

	// CONTROL
	KeyboardControl keyboard = new KeyboardControl(this);

	// UI
	public UI ui = new UI(this);

	//EVENT
	public Event_Control event=new Event_Control();
	
	// THREAD
	Thread gameThread;

	// COLLISION OJ SETUP AND ENTITY
	public CollisionChecker colChecker = new CollisionChecker(this);
	public AssetSetup aSetup = new AssetSetup(this);
	public Player player = new Player(this, keyboard);
	public Superobject object[] = new Superobject[10];
	public Entity npc[] = new Entity[10];
	
	// GAME STATE
	public final int titleState = 2;
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 0;

	// SOUND
	sound sound = new sound();
	public Graphics2D g2;

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
		aSetup.setNPC();
		playMusic(2);
		gameState = titleState;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public int FPS = 60;

	public int drawCount = 0;

	public void run() {
		double drawInterval = 100000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		// running program all along program
		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();// update
				repaint();// redraw
				delta--;
				drawCount++;
			}
			if (timer >= 100000000) {
				drawCount = 0;
			}

		}
	}

	public void update() {
		// STATE
		if (gameState == playState) {
			player.update();
			
			//NPC
			for(int i = 0 ; i < npc.length;i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
		}
		if (gameState == pauseState) {
			// NOTHING
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// TITLE SCREEN
		if (gameState == titleState) {
			ui.draw(g2);
		} else {
			// TILE
			tileM.draw(g2);
			// OBJECT
			for (int i = 0; i < object.length; i++) {
				if (object[i] != null) {
					object[i].draw(g2, this);
				}
			}
			
			//NPC
			for(int i =0;i<npc.length;i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			// PLAYER
			player.draw(g2);
			// UI
			ui.draw(g2);
			g2.dispose();
		}

	}

	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}

	public void stopMusic() {
		sound.stop();
	}

	public void playEFfect(int i) {
		sound.setFile(i);
		sound.play();
	}
}
