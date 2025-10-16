package shapes;

public class Cylinder extends Shape 
{
   private double radius;

   public Cylinder(double height, double radius) 
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
      return this.calcBaseArea() * this.getHeight();
   }
}