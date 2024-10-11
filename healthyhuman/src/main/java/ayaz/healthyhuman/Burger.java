package ayaz.healthyhuman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Burger extends GameObject{
	public Burger(String imageName, GraphicsContext gc, double x, double y, ShapeBoundary shape) {
		//calling superclass's constructor and passing it values
		super(gc, x, y, 50, 50, shape);
		// TODO Auto-generated constructor stub
		//setting image of the burger object
		img = new Image(Burger.class.getResource("resources/"+imageName).toExternalForm());
		update();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}
}
