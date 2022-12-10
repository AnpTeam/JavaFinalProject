package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNumber[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;

		tile = new Tile[50];
		mapTileNumber=new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void loadMap(String maps) {
		try {
		InputStream is =getClass().getResourceAsStream("/map/world01.txt");
		BufferedReader br =new BufferedReader(new InputStreamReader(is));
		
		int col=0;
		int row=0;
		while(col < gp.maxWorldCol && row< gp.maxWorldRow) 
		{
			String line = br.readLine();
			
			while(col < gp.maxWorldCol) {
				String numbers [] =line.split(" ");
				int num =Integer.parseInt(numbers[col]);
				mapTileNumber[col][row]=num;
				col++;
				
			}
			if(col==gp.maxWorldCol) {
				col=0;
				row++;
			}
		}
		br.close();
		}catch(Exception e) {
			
		}
		
	}

	public void getTileImage() {
		

		try {
			
			//////////////////////////////////////////GRASS/////////////////////////////////////////////////////
			//grass_withPIxel
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass01.png"));
			//////////////WALL//////////
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
			tile[1].collision=true;
			//////////////WATER/////////////
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water01.png"));
			///////////////EARTH//////////
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/earth.png"));
			//////////////////////TREE///////////////
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));
			tile[4].collision=true;
			//////////////////////SAND/////////////////////////
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g2) {
		
		int worldCol=0;
		int worldRow=0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tilenum= mapTileNumber[worldCol][worldRow];
			
			int worldX=worldCol*gp.tileSize;
			int worldY=worldRow*gp.tileSize;
			int screenX=worldX-gp.player.worldX+gp.player.screenX;
			int screenY=worldY-gp.player.worldY+gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenX &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenX ) {
				if(tilenum==4) {
					g2.drawImage(tile[tilenum].image, screenX, screenY,gp.tileSize,gp.tileSize,null);
				}
				else g2.drawImage(tile[tilenum].image, screenX, screenY,gp.tileSize,gp.tileSize,null);
			}
			worldCol++;
			
				if(worldCol==gp.maxWorldCol) {
					worldCol=0;
					worldRow++;
				}
		}
	}

}
