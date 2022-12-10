package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class object_boots extends Superobject{
	public object_boots() {
		name="boots";
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision=true;
		
	}
}
