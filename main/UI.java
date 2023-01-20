package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.*;

import object.Object_Key;

public class UI {
	DecimalFormat frm = new DecimalFormat("###");

	GamePanel gp;
	Graphics2D g2;
	
	//FONT
	Font arial_40, arial_80;
	
	//IMAGE
	BufferedImage keyImage;
	
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	double playTime;

	public UI(GamePanel gp) {
		this.gp = gp;

		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80 = new Font("Arial", Font.BOLD, 80);
		Object_Key key = new Object_Key();
		keyImage = key.image;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void drawPauseScreen() {
		String text = "PAUSE";
		
		int x = getCenterText(text);
		int y=gp.screenHeight/2;
	
		g2.drawString(text,x,y);
	}
	
	public int getCenterText(String text) {

		int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x=gp.screenWidth/2-length/2;
		return x;
	}
	
	public void draw(Graphics2D g2) {
		
		//GAME STATE
		this.g2 =g2;
		
		g2.setFont(arial_80);
		g2.setColor(Color.PINK);
		
		if(gp.gameState == gp.playState) {
			
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		//INSIDE
		if (gameFinished == true) {

			g2.setFont(arial_40);
			g2.setColor(Color.white);

			String text="";
			int textLength;
			int x = getCenterText(text);
			int y=gp.screenHeight/2;

			text = "You found a treasure";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 - (gp.tileSize * 3);
			g2.drawString(text, x + 50, y);

			g2.setFont(arial_80);
			g2.setColor(Color.yellow);
			text = "Congratulation";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 - (gp.tileSize * 2);
			g2.drawString(text, x + 40, y);

			gp.gameThread = null;
		} else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize / 2, (gp.tileSize / 3) - 10, gp.tileSize, gp.tileSize, null);
			g2.drawString("x = " + gp.player.hasKey, 100, 40);

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
