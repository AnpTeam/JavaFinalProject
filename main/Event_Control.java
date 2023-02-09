package main;

import java.awt.Rectangle;

public class Event_Control {

	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX , eventRectDefaultY;
	
	public Event_Control(){
		this.gp=gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width=2;
		eventRect.height=2;
		eventRectDefaultX=eventRect.x;
		eventRectDefaultY=eventRect.y;
		
	}
	
	public boolean hit(int eventCol , int eventRow , String reqDirection) {
		boolean hit = false;
		
		gp.player.solidArea.x=gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y=gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol * gp.tileSize + eventRect.x;
		eventRect.y = eventCol * gp.tileSize + eventRect.y;
		
		if(gp.player.solidArea.intersects(eventRect)) {
		
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit=true;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	
	
	public void checkEvent() {
		if(hit(23,21,"right")==true) {
			damagePit();
		}
	}
	public void damagePit() {
		gp.player.life -=1;
	}
	
}
