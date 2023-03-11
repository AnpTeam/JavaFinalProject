package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class utilTool {
	public static BufferedImage scaleImge(BufferedImage original, int width, int height) {
		BufferedImage scaleImage = new BufferedImage(width,height, original.getType());
		Graphics2D g2 = scaleImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaleImage;
	}
}
