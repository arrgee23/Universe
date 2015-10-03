package com.rahul.universe;


import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class AnimatedFramesDemo extends JPanel {
   // Named-constants
   static final int CANVAS_WIDTH = 640;
   static final int CANVAS_HEIGHT = 480;
   public static final String TITLE = "Gravity";
   private int frameRate = 5; 
   
   // ************************************************************
   ArrayList<PointMass> matters;
   private final double UPDATE_INTERVAL_MS=200;
 
   
   public void applyGravityToAll(){
		for(int i=0;i<matters.size();i++)
			for(int j=i+1;j<matters.size();j++)
				Gravity.applyGravity(matters.get(i), matters.get(j));
	}
	
	// updates the quantity and velocity of every mass
	public void updateAllMatters(){
		for(int i=0;i<matters.size();i++){
			matters.get(i).update(UPDATE_INTERVAL_MS);
		}
	}
	
	public void printEveryStatus(){
		for(int i=0;i<matters.size();i++){
			System.out.println(matters.get(i));
		}
	}
	public void add(PointMass m){
		matters.add(m);
	}
	
	//***************************************************************
	
   /** Constructor to set up the GUI components */
   public AnimatedFramesDemo(ArrayList<PointMass> pm) {
      matters = new ArrayList<PointMass>();
      for(int i=0;i<pm.size();i++){
    	  PointMass p = new PointMass(pm.get(i).getMass(),new Coordinate(pm.get(i).getC().getX(),pm.get(i).getC().getY(),pm.get(i).getC().getZ()));
    	  add(p);
      }
	   // Setup animation
      Thread animationThread = new Thread () {
         @Override
         public void run() {
            while (true) {
               updateAllMatters();   // update the position and image
               printEveryStatus();
               repaint();  // Refresh the display
               try {
                  Thread.sleep(1000 / frameRate); // delay and yield to other threads
               } catch (InterruptedException ex) { }
            }
         }
      };
      animationThread.start();  // start the thread to run animation
 
      // Setup GUI
      setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
   }
 
   
 
   /** Update the position based on speed and direction of the sprite */
   public void update() {
     
   }
 
   /** Custom painting codes on this JPanel */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);  // paint background
      setBackground(Color.BLACK);
      Graphics2D g2d = (Graphics2D) g;
      
      
      g2d.setPaint(Color.WHITE);

      int w = getWidth();
      int h = getHeight();
      
      //updateAllMatters(); 
      
      for (int i = 0; i < matters.size(); i++) {
          int x = (int)matters.get(i).getC().getX();
          int y = (int)matters.get(i).getC().getY();
    	  g2d.drawLine(x, y, x, y);
    	  g2d.drawString(matters.get(i).toString(), 10+0, 10+30*i); 
    	  
      }
      
   }
 
   /** The Entry main method */
   public static void main(String[] args) {
	   ArrayList<PointMass> a = new ArrayList<PointMass>();
	   PointMass m1=new PointMass(1,new Coordinate(10,10,0));
	   PointMass m2=new PointMass(2,new Coordinate(50,50,0));
	   a.add(m1);a.add(m2);
	   
      // Run the GUI codes on the Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            JFrame frame = new JFrame(TITLE);
            frame.setContentPane(new AnimatedFramesDemo(a));
            frame.pack();
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // center the application window
            frame.setVisible(true);
         }
      });
   }
}