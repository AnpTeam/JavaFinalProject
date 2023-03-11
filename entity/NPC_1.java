package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_1 extends Entity {

	public NPC_1(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getNPCImage();
	}

	public void getNPCImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setAction() {

		actionLockCounter++;
		if (actionLockCounter < 60) {
			Random random = new Random();
			int i = random.nextInt(500) + 1; // pick 1-500

//			if (i <= 125) {
//				direction = "up";
//			}
//			if (i > 125 && i <= 250) {
//				direction = "down";
//			}
			if (i > 250 && i <= 375) {
				direction = "left";
			}
			if (i > 375 && i <= 500) {
				direction = "right";
			}

			actionLockCounter = 0;
		}

	}

}
