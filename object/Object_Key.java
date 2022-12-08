package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_Key extends Superobject{
	
	public Object_Key() {
		name="Key";
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
