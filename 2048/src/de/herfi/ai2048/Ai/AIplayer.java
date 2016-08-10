package de.herfi.ai2048.Ai;

import de.herfi.ai2048.Game.Board;
import de.herfi.ai2048.Game.Board2;

public class AIplayer {
	Board2 board;
	public AIplayer(Board2 in_board) {
		// TODO Auto-generated constructor stub
		board = in_board;
	}
	
	public void play(){
		board.addTile();
		board.addTile();
	}
}
