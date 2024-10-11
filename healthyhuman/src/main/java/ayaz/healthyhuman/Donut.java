package ayaz.healthyhuman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Donut extends GameObject{
	public Donut(String imageName, GraphicsContext gc, double x, double y, ShapeBoundary shape) {
		super(gc, x, y, 60, 40, shape);
		// TODO Auto-generated constructor stub
		//setting image of donut
		img = new Image(Donut.class.getResource("resources/"+imageName).toExternalForm()); 
		update();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}
}
