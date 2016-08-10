package de.herfi.ai2048.Game;

public class State {
	
	Integer leftReward;
	Integer rightReward;
	Integer upReward;
	Integer downReward;
	Integer[][] stateArray;


	public State(Integer[][] in_stateArray) {
		// TODO Auto-generated constructor stub
		stateArray = in_stateArray;
	}

}
