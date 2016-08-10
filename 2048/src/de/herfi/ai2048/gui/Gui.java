package de.herfi.ai2048.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.herfi.ai2048.Game.Board;
import de.herfi.ai2048.Game.Board2;
import de.herfi.ai2048.Game.Tile;

public class Gui {

	public Gui(Board2 board) {
		//super();
		// TODO Auto-generated constructor stub
		ArrayList<Tile> tile = new ArrayList<Tile>(Arrays.asList(board.getMyTiles()));
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setLayout( new FlowLayout(FlowLayout.LEFT) );
		
		frame.setTitle("2048 Game");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
		pane.setSize(1000, 500);
	    frame.setResizable(true);
	    
	    //GridBagLayout gbl = new GridBagLayout();
	    //GridBagConstraints gbc;
	    //JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
	    //JPanel panel = new JPanel(new GridLayout(4,4));
	    JPanel panel = board;
	    panel.setLayout( new GridLayout(4,4) );

	    //JPanel panel = new JPanel(null);

	    
	    //panel.setSize(340, 400);
	    panel.setPreferredSize(new Dimension(320, 320));

	    panel.setBackground(new Color(0xbbada0));
	    //board.setLayout( new GridLayout(1,4) );
	    //panel.setMaximumSize(panel.getSize());
	    //panel.setMinimumSize(panel.getSize());
	    panel.setBorder(BorderFactory.createLineBorder(Color.black));
	    //frame.setMinimumSize(panel.getSize());
	    
	    //tile.remove(0);
	    
	    
	    for (Tile t: tile) {
	    	
	    	panel.add(t);
	    }
	    
	    
	    //panel.add(tile.get(5));
	    
	    pane.add(panel);
	    
	    //game.add(new Tile(512));

	    JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
	    
	   
	    panel2.setSize(340, 400);
	    pane.add(panel2);
	    
	    
	    JSlider slider3 = new JSlider( 0, 11, 0 );
	    slider3.setPaintTicks( true );
	    slider3.setMajorTickSpacing( 1 );
	    slider3.setSnapToTicks(true);
	    slider3.addChangeListener( new ChangeListener() {
	    	  @Override public void stateChanged( ChangeEvent e ) {
	    	     //System.out.println( ((JSlider) e.getSource()).getValue() );
	    	     //tile.setValue((int)Math.pow(2,((JSlider) e.getSource()).getValue()));
	    	     frame.repaint();
	    	  }
	    	} );

	    panel2.add( slider3 );
	    
	    JButton button1 = new JButton( "Add Tile" );
	    panel2.add( button1 );

	    ActionListener al = new ActionListener() {
	      @Override public void actionPerformed( ActionEvent e ) {
	        board.addTile();
	        //frame.repaint();
	      }
	    };
	    button1.addActionListener( al );
	    
	    JButton button2 = new JButton( "left" );
	    panel2.add( button2 );

	    ActionListener al2 = new ActionListener() {
	      @Override public void actionPerformed( ActionEvent e ) {
	        board.evaluateAfterState(1);
	        //board.boardCommit();
	        frame.repaint();
	      }
	    };
	    button2.addActionListener( al2 );
	    
	    JButton button3 = new JButton( "right" );
	    panel2.add( button3 );

	    ActionListener al3 = new ActionListener() {
	      @Override public void actionPerformed( ActionEvent e ) {
	    	  board.evaluateAfterState(2);
	        frame.repaint();
	      }
	    };
	    button3.addActionListener( al3 );
	    
	    JButton button4 = new JButton( "up" );
	    panel2.add( button4 );

	    ActionListener al4 = new ActionListener() {
	      @Override public void actionPerformed( ActionEvent e ) {
	    	  board.evaluateAfterState(3);
	        frame.repaint();
	      }
	    };
	    button4.addActionListener( al4 );
	    
	    JButton button5 = new JButton( "down" );
	    panel2.add( button5 );

	    ActionListener al5 = new ActionListener() {
	      @Override public void actionPerformed( ActionEvent e ) {
	    	  board.evaluateAfterState(4);
	        frame.repaint();
	      }
	    };
	    button5.addActionListener( al5 );
	    
	     
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    //frame.pack();

	    
	}

}
