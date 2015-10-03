package com.rahul.universe;

public class PointMass {
	private double mass;
	private Coordinate c;
	private Velocity v;
	private Acceleration a;
	
	public PointMass(){
		this.mass = 1;
		c = new Coordinate();
		a= new Acceleration();
		v = new Velocity();
	}
	
	private void changeVelocity(double delta){
		delta = delta/1000;
		v.setX(v.getX()+a.getX()*delta);
		v.setY(v.getY()+a.getY()*delta);
		v.setZ(v.getZ()+a.getZ()*delta);
	}
	
	private void changeCoordinate(double delta){
		delta = delta/1000; // convert to second
		c.setX(c.getX()+v.getX()*delta);
		c.setY(c.getY()+v.getY()*delta);
		c.setZ(c.getZ()+v.getZ()*delta);
	}
	
	public void update(double delta){
		changeVelocity(delta);
		changeCoordinate(delta);
	}
	
	@Override
	public String toString() {
		return "PointMass [mass=" + mass + ", c=" + c + ", v=" + v + ", a=" + a
				+ "]";
	}
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public Coordinate getC() {
		return c;
	}
	public void setC(Coordinate c) {
		this.c = c;
	}
	public Velocity getV() {
		return v;
	}
	public void setV(Velocity v) {
		this.v = v;
	}
	public Acceleration getA() {
		return a;
	}
	public void setA(Acceleration a) {
		this.a = a;
	}
	public PointMass(double mass) {
		super();
		this.mass = mass;
		c = new Coordinate();
		a= new Acceleration();
		v = new Velocity();
	}

	public PointMass(double mass, Velocity v) {
		super();
		this.mass = mass;
		this.v = v;

		c = new Coordinate();
		a= new Acceleration();
	}
	
	public PointMass(double mass, Coordinate c,Velocity v) {
		super();
		this.mass = mass;
		this.v = v;
		this.c = c;

		a= new Acceleration();
	}

	public PointMass(double mass, Acceleration a) {
		super();
		this.mass = mass;
		this.a = a;

		c = new Coordinate();
		
		v = new Velocity();
	}
	
	public PointMass(double mass, Coordinate a) {
		super();
		this.mass = mass;
		this.c = a;

		this.a = new Acceleration();
		
		v = new Velocity();
	}

	public PointMass(double mass, Coordinate c, Velocity v, Acceleration a) {
		super();
		this.mass = mass;
		this.c = c;
		this.v = v;
		this.a = a;
	}
	
	
}
