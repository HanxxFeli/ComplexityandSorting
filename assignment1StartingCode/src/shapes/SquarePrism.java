package shapes;

public class SquarePrism extends Shape 
{
	private double edgeLength;
	
	public SquarePrism(double height, double edgeLength) 
	{
		super(height);
		this.edgeLength = edgeLength;
	}
	
	@Override 
	public double calcBaseArea()
	{
		return edgeLength * edgeLength;
	}
	
	@Override
	public double calcVolume() 
	{
		return calcBaseArea() * getHeight();
	}
}
