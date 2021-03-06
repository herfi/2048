package de.herfi.ai2048.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class CopyOfBoard{

    private final static Color BG_COLOR = new Color(0xbbada0);
    private int boardsize;
    
    Integer[] tmpBoard;
    Tile board[];

	public CopyOfBoard(int size) {
		boardsize = size;
		
		 //myScore = 0;
		 //myWin = false;
		 //myLose = false;
		 board = new Tile[boardsize * boardsize];
		 for (int i = 0; i < board.length; i++) {
		   board[i] = new Tile(i,0);
		 }
		 tmpBoard = new Integer[boardsize * boardsize];
		 Arrays.fill(tmpBoard, 0);
		 //addTile();
		 //addTile();
		// TODO Auto-generated constructor stub
	}
	
	private Tile tileAt(int x, int y, Tile[] tiles) {
	    return tiles[x + y * 4];
	  }
	private Integer tileAt(int x, int y, Integer[] tiles) {
	    return tiles[x + y * 4];
	  }
	
	public Tile[] getMyTiles() {
		return board;
	}

	public void setMyTiles(Tile[] myTiles) {
		this.board = myTiles;
	}
	
	public void addTile() {
	    List<Tile> list = availableSpace();
	    if (!availableSpace().isEmpty()) {
	      int index = (int) (Math.random() * list.size()) % list.size();
	      Tile emptyTime = list.get(index);
	      emptyTime.value = Math.random() < 0.9 ? 2 : 4;
	    }
	  }
	
	private List<Tile> availableSpace() {
	    final List<Tile> list = new ArrayList<Tile>();
	    for (Tile t : board) {
	      if (t.isEmpty()) {
	        list.add(t);
	      }
	    }
	    return list;
	  }
	
	public void evaluateAfterState(){
		evaluateAfterState(board,0);
		
	}
	
	 
	 public Integer evaluateAfterState(Tile[] state, Integer action){
		 Integer reward = 0;
		 Integer[] result = new Integer[boardsize];
		 Arrays.fill(result, 0);
		 result = left(state);
		 result = mergeTiles(result);
		 for (int i = 0; i < boardsize; i++) {
			 //Arrays.fill(result, 0);
			 
			 
			 
			 

			 
			 for (int k = 0; k < boardsize; k++){
				 
				 tileAt(k, i,tmpBoard);
				Integer.valueOf(result[k]);
				 
				 
			 }
			 
		 }
		 
		 return reward;		 
	 }
	 
	 
	 
	 public Integer[] left(Tile[] state){
		 Integer[] resultBoard = new Integer[boardsize];

		 copyToTmpBoard(state);
		 
		 for (int i = 0; i < boardsize; i++) {
			 Arrays.fill(resultBoard, 0);
			 int j =0;
			 for (int k = 0; k < boardsize; k++){
					 
					
					 if (tileAt(k, i,tmpBoard) != 0 ){
						 resultBoard[j] = tileAt(k, i,tmpBoard);
						 j++;
					 }
				 
				 
			 }
		 }
		 return resultBoard;
	 }
	 
	 
	 
	 private Integer[] mergeTiles(Integer[] result){
		 Integer reward = 0;
		 
		 for (int i = 0; i < boardsize; i++) {
			 //Arrays.fill(result, 0);
			 
			 
			 for (int k = 0; k < result.length-1; k++){
				 if (result[k]!=0)
					 if (result[k]==result[k+1]){
					 
						 result[k]=+result[k+1];
						 result[k]=result[k]*2;
						 reward =+ result[k];
						 result[k+1]=0;
					 }
				 
			 }
		 }
		return result;	 
	 }
	 
	 
	 
	 public void rotate() {
		 rotate(board, 90);
	 }
	 
	 private void rotate(Tile[] state, int angle) {
		    Integer[] newTiles = new Integer[boardsize*boardsize];
		    Arrays.fill(newTiles, 0);
		    int offsetX = 3, offsetY = 3;
		    
		    if (angle == 90) {
		      offsetY = 0;
		    } else if (angle == 270) {
		      offsetX = 0;
		    }
		    

		    double rad = Math.toRadians(angle);
		    int cos = (int) Math.cos(rad);
		    int sin = (int) Math.sin(rad);
		    for (int x = 0; x < 4; x++) {
		      for (int y = 0; y < 4; y++) {
		        int newX = (x * cos) - (y * sin) + offsetX;
		        int newY = (x * sin) + (y * cos) + offsetY;
		        newTiles[(newX) + (newY) * 4] = tileAt(x, y, state).getValue();

		        //newTiles[(newX) + (newY) * 4] = tileAt(x, y, state);
		      }
		    }
		    //System.out.println("vorher: "+ newTiles );
		    for (int i = 0; i < state.length; i++) {
		    	state[i].value = newTiles[i];
		    }
		    //System.out.println("nachher: "+ state );

	 }
	 
	 
	 private void copyToTmpBoard(){
		 copyToTmpBoard(board);
	 }
	 private void copyToTmpBoard(Tile[] state){
		 for (int i = 0; i < state.length; i++) {
		    	tmpBoard[i] = state[i].getValue();
		 }
	 }
	 
	 public void boardCommit(){
		 for (int i = 0; i < board.length; i++) {
			 board[i].value = tmpBoard[i];
		 }
	 }
	
	 
	//*------------------------------------
	/*
	 public void resetGame() {
		    myScore = 0;
		    myWin = false;
		    myLose = false;
		    myTiles = new Tile[4 * 4];
		    for (int i = 0; i < myTiles.length; i++) {
		      myTiles[i] = new Tile();
		    }
		    addTile();
		    addTile();
		  }

		  public void left() {
		    boolean needAddTile = false;
		    for (int i = 0; i < 4; i++) {
		      Tile[] line = getLine(i);
		      Tile[] merged = mergeLine(moveLine(line));
		      setLine(i, merged);
		      if (!needAddTile && !compare(line, merged)) {
		        needAddTile = true;
		      }
		    }

		    if (needAddTile) {
		      myTilesTmp = myTiles;
		      addTile();
		    }
		  }

		  public void right() {
		    myTiles = rotate(180);
		    left();
		    myTiles = rotate(180);
		  }

		  public void up() {
		    myTiles = rotate(270);
		    left();
		    myTiles = rotate(90);
		  }

		  public void down() {
		    myTiles = rotate(90);
		    left();
		    myTiles = rotate(270);
		  }


		  private void addTile() {
		    List<Tile> list = availableSpace();
		    if (!availableSpace().isEmpty()) {
		      int index = (int) (Math.random() * list.size()) % list.size();
		      Tile emptyTime = list.get(index);
		      emptyTime.value = Math.random() < 0.9 ? 2 : 4;
		    }
		  }

		  private List<Tile> availableSpace() {
		    final List<Tile> list = new ArrayList<Tile>(16);
		    for (Tile t : myTiles) {
		      if (t.isEmpty()) {
		        list.add(t);
		      }
		    }
		    return list;
		  }

		  private boolean isFull() {
		    return availableSpace().size() == 0;
		  }

		  boolean canMove() {
		    if (!isFull()) {
		      return true;
		    }
		    for (int x = 0; x < 4; x++) {
		      for (int y = 0; y < 4; y++) {
		        Tile t = tileAt(x, y);
		        if ((x < 3 && t.value == tileAt(x + 1, y).value)
		          || ((y < 3) && t.value == tileAt(x, y + 1).value)) {
		          return true;
		        }
		      }
		    }
		    return false;
		  }

		  private boolean compare(Tile[] line1, Tile[] line2) {
		    if (line1 == line2) {
		      return true;
		    } else if (line1.length != line2.length) {
		      return false;
		    }

		    for (int i = 0; i < line1.length; i++) {
		      if (line1[i].value != line2[i].value) {
		        return false;
		      }
		    }
		    return true;
		  }

		  private Tile[] rotate(int angle) {
		    Tile[] newTiles = new Tile[4 * 4];
		    int offsetX = 3, offsetY = 3;
		    if (angle == 90) {
		      offsetY = 0;
		    } else if (angle == 270) {
		      offsetX = 0;
		    }

		    double rad = Math.toRadians(angle);
		    int cos = (int) Math.cos(rad);
		    int sin = (int) Math.sin(rad);
		    for (int x = 0; x < 4; x++) {
		      for (int y = 0; y < 4; y++) {
		        int newX = (x * cos) - (y * sin) + offsetX;
		        int newY = (x * sin) + (y * cos) + offsetY;
		        newTiles[(newX) + (newY) * 4] = tileAt(x, y);
		      }
		    }
		    return newTiles;
		  }

		  private Tile[] moveLine(Tile[] oldLine) {
		    LinkedList<Integer> l = new LinkedList<Integer>();
		    for (int i = 0; i < 4; i++) {
		      if (!oldLine[i].isEmpty())
		        l.addLast(oldLine[i].value);
		      
		    	  
		    }
		    if (l.size() == 0) {
		      return oldLine;
		    } else {
		      //Tile[] newLine = new Tile[4];
		      //ensureSize(l, 4);
		      for (int i = 0; i < 4; i++) {
		        newLine[i] = l.removeFirst();
		      }
		      return newLine;
		    }
		  }

		  private Tile[] mergeLine(Tile[] oldLine) {
		    LinkedList<Tile> list = new LinkedList<Tile>();
		    for (int i = 0; i < 4 && !oldLine[i].isEmpty(); i++) {
		      int num = oldLine[i].value;
		      if (i < 3 && oldLine[i].value == oldLine[i + 1].value) {
		        num *= 2;
		        myreward = num;
		        myScore += num;
		        int ourTarget = 2048;
		        if (num == ourTarget) {
		          myWin = true;
		        }
		        i++;
		      }
		      list.add(new Tile(num));
		    }
		    if (list.size() == 0) {
		      return oldLine;
		    } else {
		      ensureSize(list, 4);
		      return list.toArray(new Tile[4]);
		    }
		  }

		  private static void ensureSize(java.util.List<Tile> l, int s) {
		    while (l.size() != s) {
		      l.add(new Tile());
		    }
		  }

		  private Tile[] getLine(int index) {
		    Tile[] result = new Tile[4];
		    for (int i = 0; i < 4; i++) {
		      result[i] = tileAt(i, index);
		    }
		    return result;
		  }

		  private void setLine(int index, Tile[] re) {
		    System.arraycopy(re, 0, myTiles, index * 4, 4);
		  }
	
	*/
	
}
