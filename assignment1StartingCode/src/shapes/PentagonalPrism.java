package shapes;

public class PentagonalPrism extends Shape
{
	private double edgeLength;
	
	public PentagonalPrism(double height, double edgeLength) 
	{
		super(height);
		this.edgeLength = edgeLength;
	}
	
	@Override
	public double calcBaseArea()
	{
		return (5 * Math.pow(edgeLength, 2)) / (4 * Math.tan(Math.toRadians(54)));
	}
	
	@Override
	public double calcVolume()
	{
		return calcBaseArea() * getHeight();
	}
}
