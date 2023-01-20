package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {
	GamePanel gp;
	
	public boolean up, down, left, right;
	
	//GAME STATE
	public KeyboardControl(GamePanel gp) {
		this.gp=gp;
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			up = true;
		}
		if (key == KeyEvent.VK_A) {
			left = true;
		}
		if (key == KeyEvent.VK_S) {
			down = true;
		}
		if (key == KeyEvent.VK_D) {
			right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			up = false;
		}
		if (key == KeyEvent.VK_A) {
			left = false;
		}
		if (key == KeyEvent.VK_S) {
			down = false;
		}
		if (key == KeyEvent.VK_D) {
			right = false;
		}
		if (key == KeyEvent.VK_M) {
			if(gp.gameState== gp.playState) {
				gp.gameState=gp.pauseState;
			}
			else if(gp.gameState== gp.pauseState) {
				gp.gameState=gp.playState;
			} 
		}
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
