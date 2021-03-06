package com.rahul.universe;

public class Velocity {
	private double x;
	private double y;
	private double z;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Velocity [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Velocity(){
		x=y=z=0;
	}
	public Velocity(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
}
