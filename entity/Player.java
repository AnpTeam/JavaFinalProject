package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyboardControl;

public class Player extends Entity {
	GamePanel gp;
	KeyboardControl keyboard;

	public final int screenX;
	public final int screenY;
	int hasKey = 0;

	public Player(GamePanel gp, KeyboardControl keyboard) {

		this.gp = gp;
		this.keyboard = keyboard;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
		//collision setting
		solidArea.x = 64;//8 pixel x 4
		solidArea.y = 90 ;//16 pixel x 4
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 4;// 16 pixel x 4
		solidArea.height = 4;// 16

		setDefaultValues();
		getPlayerImage();

	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 1;
	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_3.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_3.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		direction = "down";
		if (keyboard.up == true || keyboard.down == true || keyboard.left == true || keyboard.right == true) {
			if (keyboard.up == true) {
				direction = "up";
				// worldY -= speed;
			}
			if (keyboard.down == true) {
				// worldY += speed;
				direction = "down";
			}
			if (keyboard.left == true) {
				// worldX -= speed;
				direction = "left";
			}
			if (keyboard.right == true) {
				// worldX += speed;
				direction = "right";
			}
			// collision check
			collisionOn = false;
			gp.colChecker.checkTile(this);

			// CHECK OBJECT COLLISION
			int objectIndex = gp.colChecker.checkObject(this, true);
			pickUpObject(objectIndex);

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
			if (playerStatus > 120) {
				if(direction=="left"||direction=="right")
				if (playerNumber == 1) {
					playerNumber = 2;
				} else if (playerNumber == 2) {
					playerNumber = 1;
				}
				if(direction=="up"||direction=="down")
					if (playerNumber == 1) {
						playerNumber = 2;
					} else if (playerNumber == 2) {
						playerNumber = 3;
					}
					if(playerNumber==3) {
					playerNumber=1;
				}
				playerStatus = 0;
			}
		}

	}

	public void pickUpObject(int i) {
		if (i != 999) {

			String objectName = gp.object[i].name;

			switch (objectName) {
			case "Key":
				hasKey++;
				gp.object[i] = null;
				break;
			case "Door":
				if (hasKey > 0) {
					gp.object[i] = null;
					hasKey--;
				}
			case "boots":
				
				gp.object[i] = null;
				break;

			}
		}
	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y,gp.tileSize,gp.tileSize);

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
		g2.drawImage(image, screenX, screenY, gp.tileSize*2,gp.tileSize*2, null);

	}

}
