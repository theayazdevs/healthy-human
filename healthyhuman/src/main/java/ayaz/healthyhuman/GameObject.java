package ayaz.healthyhuman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class GameObject {
	protected Image img; 
	protected double x, y; 
	protected GraphicsContext gc; 
	//declaring the size of the objects
	protected double objWidth, objHeight;
	//declaring the intersection shape boundary
	protected ShapeBoundary shape; 
	public GameObject(GraphicsContext gc, double x, double y, double objWidth, double objHeight, ShapeBoundary shape) {
		super();
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.objWidth = objHeight;
		this.objHeight = objHeight;
		this.shape = shape;
	}
	public void update() { 
		//showing image on canvas
		if(img != null) 
		{
			gc.drawImage(img, x, y, objWidth ,objHeight);
		}
	}
	//returning the intersection area of the shape
	public Shape getShapeBoundary() {
		return shape.drawShapeIntersection(objWidth, objHeight, x, y);
	}
	//updating the size of objects
	public void setSize(double width, double height) {
		this.objWidth = width;
		this.objHeight = height;
	}

}
