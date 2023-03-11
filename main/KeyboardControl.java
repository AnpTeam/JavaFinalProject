package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {
	GamePanel gp;

	public boolean up, down, left, right;

	// GAME STATE
	public KeyboardControl(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
				gp.ui.titleChoice--;
				if (gp.ui.titleChoice < 0) {
					gp.ui.titleChoice = 2;
				}
			}
			if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
				gp.ui.titleChoice++;
				if (gp.ui.titleChoice > 2) {
					gp.ui.titleChoice = 0;
				}
			}
			if (key == KeyEvent.VK_ENTER) {
				if (gp.ui.titleChoice == 0) {
					gp.stopMusic();
					gp.gameState = gp.playState;
					gp.playMusic(0);
				} else if (gp.ui.titleChoice == 1) {
					// later
				} else if (gp.ui.titleChoice == 2) {
					System.exit(0);
				}
			}
			if (key == KeyEvent.VK_ENTER) {
				if (gp.ui.titleChoice == 0) {
					gp.stopMusic();
					gp.gameState = gp.playState;
					gp.playMusic(0);
				} else if (gp.ui.titleChoice == 1) {
					// later
				} else if (gp.ui.titleChoice == 2) {
					System.exit(0);
				}
			}
		} else if (gp.gameState == gp.playState) {
			// PLAYER WALK
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
			if (key == KeyEvent.VK_U) {
				gp.gameState = gp.titleState;
				gp.stopMusic();
				gp.playMusic(2);
			}
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
			if (gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			} else if (gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
