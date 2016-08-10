package de.herfi.ai2048.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class Board{

    private final static Color BG_COLOR = new Color(0xbbada0);
    private int boardsize;
    
    Tile myTiles[];
	Integer reward;


	public Board(int size) {
		boardsize = size;
		reward = 0;
		 //myScore = 0;
		 //myWin = false;
		 //myLose = false;
		 myTiles = new Tile[boardsize * boardsize];
		 for (int i = 0; i < myTiles.length; i++) {
		   myTiles[i] = new Tile(i,0);
		 }
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
	
	public void evaluateAfterState(Integer action){
		evaluateAfterState(myTiles,action);
		
	}
	
	 
	 public Integer evaluateAfterState(Tile[] state, Integer action){
		 Integer[] result; // = new Integer[boardsize];
		 //Arrays.fill(result, 0);
		 
		 switch (action){
		 
		 	case 1:
		 		result = left(state);
		 	break;
		 	
		 	case 2:
		 		result = right(state);
		 	break;
			 	
		 	case 3:
		 		result = up(state);
		 	break;
			 	
		 	case 4:
		 		result = down(state);
		 	break;
		 
		 }
		 
		
		 
		 return reward;		 
	 }
	 
	public Integer[] left(Tile[] state) {

		Integer[] result = new Integer[boardsize];
		Integer[] result2 = new Integer[boardsize];

		for (int i = 0; i < boardsize; i++) {
			Arrays.fill(result, 0);
			int j = 0;
			for (int k = 0; k < boardsize; k++) {

				if (tileAt(k, i, state).getValue() != 0) {
					result[j] = tileAt(k, i, state).getValue();
					j++;
				}

			}

			for (int k = 0; k < result.length - 1; k++) {
				if (result[k] != 0)
					if (result[k] == result[k + 1]) {

						// result[k]=+result[k+1];
						result[k] = result[k] * 2;
						reward = +result[k];
						result[k + 1] = 0;
						// for (int m = k+2; m < result.length; m++){
						// result[k+1] = result[k+2];
						// }
						// if (k+2 < (result.length))
						// if(result[k+2] != 0)

					}

			}

			for (int k = 0; k < boardsize; k++) {

				tileAt(k, i, state).value = result[k];

			}

			Arrays.fill(result, 0);
			int n = 0;
			for (int k = 0; k < boardsize; k++) {

				if (tileAt(k, i, state).getValue() != 0) {
					result[n] = tileAt(k, i, state).getValue();
					n++;
				}

			}

			for (int k = 0; k < boardsize; k++) {

				tileAt(k, i, state).value = result[k];

			}
		}
		return result;
	}
	 
	 public Integer[] right(Tile[] state){
		 rotate(state,180);
		 left(state);
		 rotate(state,180);

		 
		return null;
		 
	 }
	 
	 public Integer[] down(Tile[] state){
		 rotate(state,90);
		 left(state);
		 rotate(state,270);

		 
		return null;
		 
	 }
	 public Integer[] up(Tile[] state){
		 rotate(state,270);
		 left(state);
		 rotate(state,90);

		 
		return null;
		 
	 }

	 
	 public void rotate() {
		 rotate(myTiles, 90);
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
