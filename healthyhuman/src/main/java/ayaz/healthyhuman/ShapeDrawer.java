package ayaz.healthyhuman;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ShapeDrawer implements ShapeBoundary {

	@Override
	public Shape drawShapeIntersection(double width, double height, double x, double y) {
		// TODO Auto-generated method stub
		if(drawRectangle()==true) 
		{
			return new Rectangle(x,y,width,height); 
		}
		else if(drawCircle() == true) 
		{
			return new Circle(x+(width/2), y+(height/2), width/2); 
		}
		else 
		{
			System.out.println("No such Shape");
			return null;
		}
	}
	public static boolean drawRectangle() 
	{
		return true;
	}
	public static boolean drawCircle() 
	{
		return true;
	}
}
