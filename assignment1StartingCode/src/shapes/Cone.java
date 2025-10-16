package shapes;

public class Cone extends Shape 
{
   private double radius;

   public Cone(double height, double radius) 
   {
      super(height);
      this.radius = radius;
   }

   public double calcBaseArea() 
   {
      return Math.PI * Math.pow(this.radius, 2.0);
   }

   public double calcVolume() 
   {
      return (1.0 / 3.0) * this.calcBaseArea() * this.getHeight();
   }
}
