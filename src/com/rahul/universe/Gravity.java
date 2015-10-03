package com.rahul.universe;

public class Gravity {
	private static final double G=1;
	
	private static final double DISTANCE_ACCURACY = 0.5;
	/*
	 * calculates the gravity and applies it to two masses
	 * i.e. changes the acceleration of them
	 */
	public static void applyGravity(PointMass m1,PointMass m2){
		double x1 = m1.getC().getX();
		double y1 = m1.getC().getY();
		double z1 = m1.getC().getZ();
		
		double x2 = m2.getC().getX();
		double y2 = m2.getC().getY();
		double z2 = m2.getC().getZ();
		
		
		double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) + (z1-z2)*(z1-z2));
		
		// don't let each other cross
		//if(distance < DISTANCE_ACCURACY)
			//return;
		
		double forceMod = G*(m1.getMass()*m2.getMass())/(distance*distance);
		
		// with respect to m2 change sign for m1
		double forceX = (x1-x2)*forceMod/distance;
		double forceY = (y1-y2)*forceMod/distance;
		double forceZ = (z1-z2)*forceMod/distance;
		
		// set acceleration for m2
		m2.getA().setX(m2.getA().getX()+forceX/m2.getMass());
		m2.getA().setY(m2.getA().getY()+forceY/m2.getMass());
		m2.getA().setZ(m2.getA().getZ()+forceZ/m2.getMass());
		
		// for m1
		m1.getA().setX(m1.getA().getX()-forceX/m1.getMass());
		m1.getA().setY(m1.getA().getY()-forceY/m1.getMass());
		m1.getA().setZ(m1.getA().getZ()-forceZ/m1.getMass());
	}
}
