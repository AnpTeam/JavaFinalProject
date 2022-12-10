package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;

public class Object_door extends Superobject {
	public Object_door() {
		name="Key";
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
