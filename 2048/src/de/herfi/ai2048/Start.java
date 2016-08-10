package de.herfi.ai2048;

import de.herfi.ai2048.Ai.AIplayer;
import de.herfi.ai2048.Game.Board;
import de.herfi.ai2048.Game.Board2;
import de.herfi.ai2048.gui.Gui;

public class Start {
	final static Integer BOARD_SIZE = 4;
	
	//static Board board;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board2 board = new Board2(BOARD_SIZE);
		Gui gui = new Gui(board);
		AIplayer player = new AIplayer(board);
		player.play();
	}

}
