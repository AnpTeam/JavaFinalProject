package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//create window main
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Bravely Soul");
		GamePanel gamepanel =new GamePanel();
		frame.add(gamepanel);
		frame.pack();
		
		gamepanel.setupGame();
		
		gamepanel.startGameThread();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
