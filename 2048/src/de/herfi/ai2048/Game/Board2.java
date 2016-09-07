package de.herfi.ai2048.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class Board2 extends JPanel{

    private final static Color BG_COLOR = new Color(0xbbada0);
    private final static String FONT_NAME = "Arial";

    private int boardsize;
    
    Tile myTiles[];
    Integer[][] board;
    Integer[][] tmpBoard;
	Integer reward;
	private boolean myLose;
	private boolean myWin;
	private int myScore;


	public Board2(int size) {
		boardsize = size;
		reward = 0;
		myScore = 0;
		myWin = false;
		myLose = false;
		 
		 myTiles = new Tile[boardsize * boardsize];
		 for (int i = 0; i < myTiles.length; i++) {
		   myTiles[i] = new Tile(i,0);
		 }
		 
		 board = new Integer[boardsize][boardsize];
		 copyToTmpBoard();
		 //Arrays.fill(tmpBoard, 0);
		 //addTile();
		 //addTile();
		// TODO Auto-generated constructor stub
	}
	
	private Tile tileAt(int x, int y, Tile[] tiles) {
	    return tiles[x + y * 4];
	  }
	
	public Tile[] getMyTiles() {
		return myTiles;
	}

	public void setMyTiles(Tile[] myTiles) {
		this.myTiles = myTiles;
	}
	
	
	
	
	/*
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
	    for (Tile t : myTiles) {
	      if (t.isEmpty()) {
	        list.add(t);
	      }
	    }
	    return list;
	  }
	
*/	
	
	
	
	
	
	public void addTile() {
		
		
	    List<Integer> list = availableSpace();
	    if (!availableSpace().isEmpty()) {
	      int index = (int) (Math.random() * list.size()) % list.size();
	      
	      Integer index2 = list.get(index);
	      
	      int x = index2 % boardsize;
	      int y = (index2 - x) / boardsize;
	      //emptyTime.value = Math.random() < 0.9 ? 2 : 4;
	      //System.out.println("index: "+index+"x: "+x+"y: "+y);
	      board[x][y] = Math.random() < 0.9 ? 1 : 2;
	      //commit();
	    }
	  }
	
	private List<Integer> availableSpace() {
	    final List<Integer> list = new ArrayList<Integer>();
	    for (int x = 0; x < boardsize; x++) {
			for (int y = 0; y < boardsize; y++) {
				if (board[x][y] == 0) {
					list.add(x+y*boardsize);
				}
			}
	    }
	    return list;
	  }
	
	private boolean isFull() {
	    return availableSpace().size() == 0;
	  }

	private boolean canMove() {
	    if (!isFull()) {
	      return true;
	    }
	    for (int x = 0; x < 4; x++) {
	      for (int y = 0; y < 4; y++) {
	        Integer t = board[x][y];
	        if ((x < 3 && t == board[x+1][y])
	          || ((y < 3) && t == board[x][y+1])) {
	          return true;
	        }
	      }
	    }
	    return false;
	  }
	  
	private boolean isEqual(Integer[][] state0, Integer[][] state1){

		for (int x = 0; x < 4; x++) {
		      for (int y = 0; y < 4; y++) {
		        if (state0[x][y]!=state1[x][y]) {
		          return false;
		        }
		      }
		    }
		 return true;
		
	}
	
	
	
	public void evaluateAfterState(Integer action){
		evaluateAfterState(board,action);
		
	}
	
	 
	 public Integer evaluateAfterState(Integer[][] state, Integer action){
		 Integer[] result; // = new Integer[boardsize];
		 //Arrays.fill(result, 0);
		 //copyToTmpBoard(state);
		 reward=0;
		 board = state;
		 tmpBoard = board;
		 switch (action){
		 
		 	case 1:
		 		result = left();
		 	break;
		 	
		 	case 2:
		 		result = right();
		 	break;
			 	
		 	case 3:
		 		result = up();
		 	break;
			 	
		 	case 4:
		 		result = down();
		 	break;
		 
		 }
		
		
		//if (!isEqual(board, tmpBoard)){
		//	addTile();
		//}
	 	
	 
		myLose = !canMove();
	    System.out.println("myLose:"+ myLose);
	    System.out.println("reward:"+ reward);
	    System.out.println("myScore:"+ myScore);

		commit();
		return reward;		 
	 }
	 
	public Integer[] left() {

		Integer[] result = new Integer[boardsize];
		
		
		for (int i = 0; i < boardsize; i++) {
			Arrays.fill(result, 0);
			int j = 0;
			for (int k = 0; k < boardsize; k++) {

				if (board[k][i] != 0) {
					result[j] = board[k][i];
					j++;
				}

			}

			for (int k = 0; k < result.length - 1; k++) {
				if (result[k] != 0)
					if (result[k] == result[k + 1]) {

						// result[k]=+result[k+1];
						result[k] = result[k] + 1;
						reward += (int)Math.pow(2, result[k]);
						
						result[k + 1] = 0;
						// for (int m = k+2; m < result.length; m++){
						// result[k+1] = result[k+2];
						// }
						// if (k+2 < (result.length))
						// if(result[k+2] != 0)

					}
					
			}

			for (int k = 0; k < boardsize; k++) {

				board[k][i] = result[k];

			}

			Arrays.fill(result, 0);
			int n = 0;
			for (int k = 0; k < boardsize; k++) {

				if (board[k][i] != 0) {
					result[n] = board[k][i]; //tileAt(k, i, state).getValue();
					n++;
				}

			}

			for (int k = 0; k < boardsize; k++) {

				board[k][i] = result[k];

			}
		}
		myScore += reward;
		return result;
	}
	 
	 public Integer[] right(){
		 rotate(180);
		 left();
		 rotate(180);

		 
		return null;
		 
	 }
	 
	 public Integer[] down(){
		 rotate(90);
		 left();
		 rotate(270);

		 
		return null;
		 
	 }
	 public Integer[] up(){
		 rotate(270);
		 left();
		 rotate(90);

		 
		return null;
		 
	 }

	 
	 public void rotate() {
		 rotate(90);
	 }
	 
	private void rotate(int angle) {
		Integer[][] newTiles = new Integer[boardsize][boardsize];

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
				newTiles[newX][newY] = board[x][y];

			}
		}
		
		board = newTiles;
	}
	
	public ArrayList<Integer[][]> evaluateAfterAfterState(Integer[][] state){
		
		List<Integer> list = availableSpace();
		ArrayList<Integer[][]> AfterAfterStateList = new ArrayList<Integer[][]>();
		
	    if (!availableSpace().isEmpty()) {
	      for (Integer l : list){
	    	  
	    	  int x = l % boardsize;
		      int y = (l - x) / boardsize;
		      
		      for (int i=1; i < 3; i++){
		    	  Integer[][] cpyOfState = state;
		    	  cpyOfState[x][y]=i;
		    	  AfterAfterStateList.add(cpyOfState);
		      }
	      }    
	    }
		
		return AfterAfterStateList;
		
	}
	
	private void copyToTmpBoard(){
		copyToTmpBoard(myTiles);
	}
	
	private void copyToTmpBoard(Tile[] state){
		
		for (int x = 0; x < boardsize; x++) {
			for (int y = 0; y < boardsize; y++) {
				
				board[x][y]=tileAt(x, y, state).getValue();
				
			}
		}
		
	}
	
	public void commit(){
		
		commit(myTiles);
	}
	
	private void commit(Tile[] state){
		
		for (int x = 0; x < boardsize; x++) {
			for (int y = 0; y < boardsize; y++) {
				
				tileAt(x, y, state).setValue(board[x][y]); //value = tmpBoard[x][y];
				
			}
		}
		super.repaint();
	}
	 
	protected void paintComponent( Graphics g )
    {
      Graphics2D g2 = ((Graphics2D) g);
      
      if (myWin || myLose) {
    	  for (int i = 0; i < myTiles.length; i++) {
   		   myTiles[i].setVisible(false);
   		 }
          g2.setColor(new Color(255, 255, 255, 30));
          g2.fillRect(0, 0, getWidth(), getHeight());
          g2.setColor(new Color(78, 139, 202));
          g2.setFont(new Font(FONT_NAME, Font.BOLD, 48));
          if (myWin) {
            g2.drawString("You won!", 68, 150);
          }
          if (myLose) {
            g2.drawString("Game over!", 50, 130);
            g2.drawString("You lose!", 64, 200);
          }
          if (myWin || myLose) {
            g2.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
            g2.setColor(new Color(128, 128, 128, 128));
            g2.drawString("Press ESC to play again", 80, getHeight() - 40);
          }
        }
        g2.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
        g2.drawString("Score: " + myScore, 200, 365);
        
        
        super.paintComponent( g2 );
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
