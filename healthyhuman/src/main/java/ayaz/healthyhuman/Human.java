package ayaz.healthyhuman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//Singleton pattern is used to create human instance
public class Human extends GameObject{ 
	//field to hold instance of the class 
	private static Human instance = null; 
	public Human(GraphicsContext gc, double x, double y, ShapeBoundary shape) {
		super(gc, x, y, 70, 40, shape);
		// TODO Auto-generated constructor stub
		img = new Image(Human.class.getResource("resources/humanLeft.png").toExternalForm());
	}
	public static Human getInstance(GraphicsContext gc, double x, double y, ShapeBoundary shape) {
		//if there is no instance already then creating one
		if(instance == null) 
		{
			instance = new Human(gc, x, y, shape);
		}
		//if already exists then return that specific instance
		return instance; 
	}
	//default human position
	public void defaultPosition() {
		x = 1100;
		y = 350;
		moveLeft();
	}
	//moving the human character left
	public void moveLeft() {
		//call game object method
		setSize(70, 40); 
		img = new Image(Human.class.getResource("resources/humanLeft.png").toExternalForm());
		if(x > 0) { 
			x-=3;
		}
	}
	//moving the human character right
	public void moveRight() {
		setSize(70, 40);
		img = new Image(Human.class.getResource("resources/humanRight.png").toExternalForm());
		if(x < 1125) {
			x+=3;
		}
	}
	//moving the human character up
	public void moveUp() {
		setSize(40, 70);
		img = new Image(Human.class.getResource("resources/humanUp.png").toExternalForm());
		if(y > 50) {
			y -= 3;
		}
	}
	//moving the human character down
	public void moveDown() { 
		setSize(40, 70);
		img = new Image(Human.class.getResource("resources/humanDown.png").toExternalForm());
		if(y < 600) {
			y += 3;
		}
	}

	
}
