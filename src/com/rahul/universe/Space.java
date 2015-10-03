package com.rahul.universe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Space{
	ArrayList<PointMass> matters;
	private final double UPDATE_INTERVAL_MS=10;
	JFrame frame;
    DrawPanel drawPanel;
    static final int CANVAS_WIDTH = 1024;
    static final int CANVAS_HEIGHT = 768;
    
    
	public Space() {
		matters = new ArrayList<PointMass>();
	}
	
	public void add(PointMass m){
		matters.add(m);
	}
	
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
	
	public void draw(){
		double time=0;
		while(time<1){
			try {
				time += UPDATE_INTERVAL_MS/1000;
				System.out.println("Time:"+time);
				applyGravityToAll();
				updateAllMatters();
				printEveryStatus();
				System.out.println("******************************************************************");
				Thread.sleep((long) UPDATE_INTERVAL_MS);
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		PointMass m1=new PointMass(1,new Coordinate(100,100,0),new Velocity(3,0,0));
		PointMass m2=new PointMass(20,new Coordinate(512,384,0),new Velocity(0,0,0));
		PointMass m3=new PointMass(30,new Coordinate(612,384,0),new Velocity(0,0,0));
		Space s = new Space();
		s.add(m1);
		s.add(m2);
		s.add(m3);
		//s.draw();
		s.go();
	}

	private void go() {
		frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setResizable(true);
        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        moveIt();
		
	}

	class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g)
        {
        	Graphics2D g2d = (Graphics2D) g;
        	setBackground(Color.BLUE);
            g2d.setPaint(Color.BLACK);

            int w = getWidth();
            int h = getHeight();
            
            for (int i = 0; i < matters.size(); i++) {
                int x = (int)matters.get(i).getC().getX();
                int y = (int)matters.get(i).getC().getY();
          	  	//g2d.drawLine(x, y, x, y); 
          	  	
          	  	//g2d.drawArc(x, y, 2, 2, startAngle, arcAngle);
          	  	g2d.drawOval(x, y, 4, 4);
            }
        }
    }

    private void moveIt()
    {
        while (true)
        {
        	applyGravityToAll();
			updateAllMatters();
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }
}
