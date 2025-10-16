package shapes;

public class Pyramid extends Shape 
{
   private double side;

   public Pyramid(double height, double side) 
   {
      super(height);
      this.side = side;
   }

   public double calcBaseArea() 
   {
      return Math.pow(this.side, 2.0);
   }

   public double calcVolume() 
   {
      return (1.0 / 3.0) * this.calcBaseArea() * this.getHeight();
   }
}
