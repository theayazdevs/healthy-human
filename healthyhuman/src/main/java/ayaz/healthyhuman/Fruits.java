package ayaz.healthyhuman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class Fruits extends GameObject{
	private String imageName;
	public Fruits(String imageName, GraphicsContext gc, double x, double y, double sizeX, double sizeY, ShapeBoundary shape) {
		super(gc, x, y, sizeX, sizeY, shape);
		// TODO Auto-generated constructor stub
		img = new Image(Fruits.class.getResource("resources/"+imageName).toExternalForm());
		this.imageName = imageName;
		update();
	}
	@Override
	public Shape getShapeBoundary() {
		// TODO Auto-generated method stub
		//return super.getIntersectShape();
		return super.getShapeBoundary(); 
	}
	//return image name
		public String getFruitImage() {
			return imageName;
		}

}
