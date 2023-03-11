package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.utilTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNumber[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;

		tile = new Tile[50];
		mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];

		tileSetup();
		loadMap("/map/worldV2.txt");
	}

	public void tileSetup() {
		// COPY AND PASTE
		getTileImage(0, "grass00", false);
		getTileImage(1, "water01", false);
		getTileImage(2, "grass00", false);
		getTileImage(3, "grass00", false);
		getTileImage(4, "grass00", false);
		getTileImage(5, "grass00", false);
		getTileImage(6, "grass00", false);
		getTileImage(7, "grass00", false);
		getTileImage(8, "grass00", false);
		getTileImage(9, "grass00", false);

		// SETUP
		getTileImage(10, "grass00", false);
		getTileImage(11, "grass01", false);
		getTileImage(12, "water00", false);
		getTileImage(13, "water01", false);
		getTileImage(14, "water02", true);
		getTileImage(15, "water03", true);
		getTileImage(16, "water04", true);
		getTileImage(17, "water05", true);
		getTileImage(18, "water06", true);
		getTileImage(19, "water07", true);
		getTileImage(20, "water08", true);
		getTileImage(21, "water09", true);
		getTileImage(22, "water10", true);
		getTileImage(23, "water11", true);
		getTileImage(24, "water12", true);
		getTileImage(25, "water13", false);
		getTileImage(26, "road00", false);
		getTileImage(27, "road01", false);
		getTileImage(28, "road02", false);
		getTileImage(29, "road03", false);
		getTileImage(30, "road04", false);
		getTileImage(31, "road05", false);
		getTileImage(32, "road06", false);
		getTileImage(33, "road07", false);
		getTileImage(34, "road08", false);
		getTileImage(35, "road09", false);
		getTileImage(36, "road10", false);
		getTileImage(37, "road11", false);
		getTileImage(38, "road12", false);
		getTileImage(39, "earth", false);
		getTileImage(40, "wall", true);
		getTileImage(41, "tree", true);
	}

	public void getTileImage(int index, String nameTile, boolean collisionT) {
		utilTool uTool = new utilTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + nameTile + ".png"));
			tile[index].image = uTool.scaleImge(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collisionT;

			// TEST
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));
			tile[1].image = uTool.scaleImge(tile[1].image, gp.tileSize, gp.tileSize);
			tile[1].collision = collisionT;
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
			tile[2].image = uTool.scaleImge(tile[2].image, gp.tileSize, gp.tileSize);
			tile[2].collision = collisionT;
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));
			tile[3].image = uTool.scaleImge(tile[3].image, gp.tileSize, gp.tileSize);
			tile[3].collision = collisionT;
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
			tile[4].image = uTool.scaleImge(tile[4].image, gp.tileSize, gp.tileSize);
			tile[4].collision = collisionT;
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
			tile[5].image = uTool.scaleImge(tile[5].image, gp.tileSize, gp.tileSize);
			tile[5].collision = collisionT;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String maps) {
		try {
			InputStream is = getClass().getResourceAsStream(maps);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();

				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNumber[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
		}
	}

	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tilenum = mapTileNumber[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenX
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenX) {

				g2.drawImage(tile[tilenum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}

}
