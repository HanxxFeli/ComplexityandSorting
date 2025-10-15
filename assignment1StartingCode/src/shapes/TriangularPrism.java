package shapes;

public class TriangularPrism extends Shape 
{
	private double edgeLength;
	
	public TriangularPrism(double height, double edgeLength)
	{
		super(height);
		this.edgeLength = edgeLength;
	}
	
	@Override
	public double calcBaseArea()
	{
		return (Math.sqrt(3) / 4) * Math.pow(edgeLength, 2);
	}
	
	@Override
	public double calcVolume() 
	{
		return calcBaseArea() * getHeight();
	}
}
