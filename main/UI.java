package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.*;

import javax.imageio.ImageIO;

import object.Object_Key;
import object.Object_heart;
import object.Superobject;

public class UI {
	DecimalFormat frm = new DecimalFormat("###");

	GamePanel gp;
	Graphics2D g2;

	KeyboardControl keyboard = new KeyboardControl(gp);

	// TT_CHOICE
	public int titleChoice = 0;

	// FONT
	Font arial_20, arial_30;

	// IMAGE
	BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank;

	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	double playTime;

	public UI(GamePanel gp) {
		this.gp = gp;

		arial_20 = new Font("Arial", Font.PLAIN, 20);
		arial_30 = new Font("Arial", Font.BOLD, 30);
		Object_Key key = new Object_Key();
		keyImage = key.image;

		// HUD OBJECT
		Superobject heart = new Object_heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;

	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void drawPauseScreen() {
		String text = "PAUSE";

		int x = getCenterText(text);
		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);
	}

	public int getCenterText(String text) {

		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

	public void draw(Graphics2D g2) {

		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			g2.setColor(new Color(70, 161, 25));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

			String text = "BRAVELY SOUL THE GAME";
			g2.setFont(arial_30);
			// SHADOW
			g2.setColor(Color.BLACK);
			g2.drawString(text, gp.tileSize * 4+1, gp.tileSize+1 );
			// TITLE
			g2.setColor(Color.white);
			g2.drawString(text, gp.tileSize * 4, gp.tileSize);

			// CHARACTORIMG
			int x = gp.screenWidth / 2 -125;
			int y = gp.tileSize;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*5, gp.tileSize*5, null);

			// MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 33F));
			text = "START GAME";
			y += gp.tileSize*3  + 150;
			g2.setColor(Color.BLACK);
			g2.drawString(text, x + 3, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(text, x, y);
			if (titleChoice == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "LOAD";
			x += 70;
			y += gp.tileSize + 15;
			g2.setColor(Color.BLACK);
			g2.drawString(text, x + 3, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(text, x, y);
			if (titleChoice == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "QUIT";
			y += gp.tileSize + 15;
			g2.setColor(Color.BLACK);
			g2.drawString(text, x + 3, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(text, x, y);
			if (titleChoice == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}

		} else {
			// GAME STATE
			this.g2 = g2;
			if (gp.gameState == gp.playState) {
				drawPlayerLife();
			}
			if (gp.gameState == gp.pauseState) {
				g2.setFont(new Font("Arial", Font.PLAIN, 50));
				g2.setColor(Color.PINK);
				drawPauseScreen();
				drawPlayerLife();
			}

			// INSIDE
			if (gameFinished == true) {

				g2.setFont(arial_20);
				g2.setColor(Color.white);

				String text = "";
				int textLength;
				int x = getCenterText(text);
				int y = gp.screenHeight / 2;

				text = "You found a treasure";
				textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

				x = gp.screenWidth / 2 - textLength / 2;
				y = gp.screenHeight / 2 - (gp.tileSize * 3);
				g2.drawString(text, x + 50, y);

				g2.setFont(arial_30);
				g2.setColor(Color.yellow);
				text = "Congratulation";
				textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

				x = gp.screenWidth / 2 - textLength / 2;
				y = gp.screenHeight / 2 - (gp.tileSize * 2);
				g2.drawString(text, x + 40, y);

				// MENU
				g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38F));
				text = "PRESS U TO EXIT TO MAIN MENU";
				y += gp.tileSize * 5 + 100;
				g2.setColor(Color.BLACK);
				g2.drawString(text, x + 3, y + 3);
				g2.setColor(Color.WHITE);
				g2.drawString(text, x, y);

			} else {
				g2.setFont(arial_30);
				g2.setColor(Color.white);
				g2.drawImage(keyImage, gp.tileSize / 2, (gp.tileSize / 3) +80, gp.tileSize, gp.tileSize, null);
				g2.drawString("x = " + gp.player.hasKey, 80, 130);

				// TIME
				playTime += (double) 1 / 300;
				g2.drawString("Time: " + frm.format(playTime), gp.tileSize * 16, 40);

				// MESSAGE
				if (messageOn == true) {
					g2.setFont(g2.getFont().deriveFont(30F));
					g2.drawString(message, gp.tileSize + 525, gp.tileSize * 7);

					messageCounter++;
					if (messageCounter > 300) {
						messageCounter = 0;
						messageOn = false;
					}
				}
			}
		}
	}

	public void drawPlayerLife() {
		
		
		//DRAW BLANK HEART
		int x = gp.tileSize /2;
		int y = gp.tileSize / 2;
		int i = 0;
		while (i < gp.player.max_life / 2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		//DRAW CURRENT LIFE*(RESET)
		 x = gp.tileSize /2;
		 y = gp.tileSize / 2;
		 i = 0;
		 
		 //DRAW LIFE
		 while(i < gp.player.life) {
			 g2.drawImage(heart_half,x,y,null);
			 i++;
			 if(i < gp.player.life) {
				 g2.drawImage(heart_full,x,y,null);
			 }
			 i++;
			 x+=gp.tileSize;
			 
		 }
		 
	}

}
