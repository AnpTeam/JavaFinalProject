package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {

	GamePanel gp;

	// move setting
	public int worldX, worldY;
	public int speed;
	// player picture
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, right1, right2;
	public String direction;
	public int playerStatus = 0;
	public int playerNumber = 1;
	//////// collision///////
	public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	//setNPCMovement
	public int actionLockCounter=0;
	

	// PLAYER STATUS
	public int max_life;
	public int life;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenX
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenX) {

			BufferedImage image = null;
			switch (direction) {
			case "up":
				if (playerNumber == 1) {
					image = up1;
				}
				if (playerNumber == 2) {
					image = up2;
				}
				if (playerNumber == 3) {
					image = up3;
				}
				break;
			case "down":
				if (playerNumber == 1) {
					image = down1;
				}
				if (playerNumber == 2) {
					image = down3;
				}
				if (playerNumber == 3) {
					image = down2;
				}
				break;
			case "left":
				if (playerNumber == 1) {
					image = left1;
				}
				if (playerNumber == 2) {
					image = left2;
				}

				break;
			case "right":
				if (playerNumber == 1) {
					image = right1;
				}
				if (playerNumber == 2) {
					image = right2;
				}
				break;

			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

		}
	}

	public void setAction() {
	}

	public void update() {
		setAction();

		collisionOn = false;
		gp.colChecker.checkTile(this);

		// if collision is false,player can move
		if (collisionOn == false) {
			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		playerStatus++;
		if (playerStatus > 500) {
			if (direction == "left" || direction == "right")
				if (playerNumber == 1) {
					playerNumber = 2;
				} else if (playerNumber == 2) {
					playerNumber = 1;
				}
			if (direction == "up" || direction == "down")
				if (playerNumber == 1) {
					playerNumber = 2;
				} else if (playerNumber == 2) {
					playerNumber = 3;
				}
			if (playerNumber == 3) {
				playerNumber = 1;
			}
			playerStatus = 0;
		}
	}
}
