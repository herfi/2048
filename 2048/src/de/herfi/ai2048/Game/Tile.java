package de.herfi.ai2048.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Tile extends JPanel{
    int value;
    int position;
    int x = 0;
    int y = 0;
    int xOffset = 0;
    int yOffset = 0;
    
    private final static Color BG_COLOR = new Color(0xbbada0);
    private final static String FONT_NAME = "Arial";
    private final static int TILE_SIZE = 64;
    private final static int TILES_MARGIN = 8;

    public Tile(int p) {
      this.value=0;
      position = p;
      //pToXY();
      xOffset = offsetCoors(x);
      yOffset = offsetCoors(y);
      //System.out.println("x:"+xOffset+"; y:"+yOffset);
      setBounds(xOffset, yOffset, TILE_SIZE+TILES_MARGIN, TILE_SIZE+TILES_MARGIN);
    }

    public Tile(int p, int num) {
      value = num;
      position = p;
      //pToXY();
      xOffset = offsetCoors(x);
      yOffset = offsetCoors(y);
      //System.out.println("o_x:"+xOffset+"; o_y:"+yOffset);
      setBounds(xOffset, yOffset, TILE_SIZE+TILES_MARGIN, TILE_SIZE+TILES_MARGIN);
    }

    public boolean isEmpty() {
      return value == 0;
    }
    
    @Override
    protected void paintComponent( Graphics g )
    {
      Graphics2D g2 = ((Graphics2D) g);
      
      
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
      int value = this.value;
      //pToXY();
      xOffset = offsetCoors(x);
      yOffset = offsetCoors(y);
      g2.setColor(getBackground());
      //System.out.println("o_x:"+xOffset+"; o_y:"+yOffset);
      g2.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
      //g2.fillRoundRect(xOffset+TILE_SIZE, yOffset+TILE_SIZE, TILE_SIZE, TILE_SIZE, 14, 14);

      g2.setColor(getForeground());
      final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
      final Font font = new Font(FONT_NAME, Font.BOLD, size);
      g2.setFont(font);

      String s = String.valueOf((int)Math.pow(2, value));
      final FontMetrics fm = getFontMetrics(font);

      final int w = fm.stringWidth(s);
      final int h = -(int) fm.getLineMetrics(s, g2).getBaselineOffsets()[2];

      if (value != 0)
        g2.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
/*
      if (myWin || myLose) {
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
      */
      
      //super.paintComponent( g2 );
    }

    private static int offsetCoors(int arg) {
        return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
      }
    
    public Color getForeground() {
      return value < 16 ? new Color(0x776e65) :  new Color(0xf9f6f2);
    }

    public Color getBackground() {
      switch (value) {
        case 1:    return new Color(0xeee4da);
        case 2:    return new Color(0xede0c8);
        case 3:    return new Color(0xf2b179);
        case 4:   return new Color(0xf59563);
        case 5:   return new Color(0xf67c5f);
        case 6:   return new Color(0xf65e3b);
        case 7:  return new Color(0xedcf72);
        case 8:  return new Color(0xedcc61);
        case 9:  return new Color(0xedc850);
        case 10: return new Color(0xedc53f);
        case 11: return new Color(0xedc22e);
      }
      return new Color(0xcdc1b4);
    }
    
    private void pToXY(){
    	x = position % 4;
    	y = (position -x) / 4;
    	//System.out.println("x:"+x+"; y:"+y);
    }

	public int getValue() {
		return value;
	}
	
	public int getLog2Value() {
		return (int)(Math.log(value) >= 0 ? Math.log(value):0 / Math.log(2));
	}
    
	public void setValue(int v) {
			value = v;
	}
    
  }
