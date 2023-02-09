package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.utilTool;

public class Object_heart extends Superobject {
	
	GamePanel gp;
	
	public Object_heart(GamePanel gp) {
		name = "heart";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
			image=utilTool.scaleImge(image,gp.tileSize,gp.tileSize);
			image2=utilTool.scaleImge(image2,gp.tileSize,gp.tileSize);
			image3=utilTool.scaleImge(image3,gp.tileSize,gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
