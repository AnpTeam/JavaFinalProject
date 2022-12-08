package entity;

import java.awt.image.BufferedImage;

import java.awt.Rectangle;

public class Entity {
	//move setting
	public int worldX,worldY;
	public int speed;
	//player picture
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public String direction;
	public int playerStatus=0;
	public int playerNumber=1;
	////////collision///////
	public Rectangle solidArea;
	public boolean collisionOn=false;
	
	
}
