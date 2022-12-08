package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_Chest extends Superobject {

	public Object_Chest() {
		name="Key";
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
