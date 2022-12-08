package main;

import object.Object_Chest;
import object.Object_Key;
import object.Object_door;

public class AssetSetup {
	
	GamePanel gp;
	public AssetSetup(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObject() {
		gp.object[0]= new Object_Key();
		gp.object[0].worldX=gp.tileSize*23;
		gp.object[0].worldY=gp.tileSize*7;
		
		gp.object[1]= new Object_Key();
		gp.object[1].worldX=gp.tileSize*23;
		gp.object[1].worldY=gp.tileSize*40;
		
		gp.object[2]= new Object_door();
		gp.object[2].worldX=gp.tileSize*10;
		gp.object[2].worldY=gp.tileSize*11;
		
		gp.object[3]= new Object_Chest();
		gp.object[3].worldX=gp.tileSize*10;
		gp.object[3].worldY=gp.tileSize*7;
		
	}
}
