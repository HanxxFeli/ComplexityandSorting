package shapes;

public abstract class Shape implements Comparable<Shape> {
	private double height;
	
	public Shape(double height) { 
		this.height = height;
	}
	
	public double getHeight() { 
		return height;
	}
	
	public abstract double calcBaseArea();
	public abstract double calcVolume();
	
	@Override 
	public int compareTo(Shape otherShape) { 
		return Double.compare(this.height, otherShape.height);
	}
}
