package ayaz.healthyhuman;

import javafx.scene.canvas.GraphicsContext;

public class FactoryProduct implements Factory{
	private GraphicsContext gc;
	
	public FactoryProduct(GraphicsContext gc) {
		super();
		this.gc = gc;
	}

	@Override
	public GameObject createProduct(String imageName, double x, double y, double sizeX, double sizeY,
			ShapeBoundary shape) {
		// TODO Auto-generated method stub
		//create objects according to the object name
				if(imageName.equals("burger.png")) { 
					return new Burger(imageName, gc, x, y, shape);
				} 
				else if(imageName.equals("donut.png")) 
				{
					//System.out.println(imageName + gc + x + y + shape);
					return new Donut(imageName, gc, x, y, shape);
				}
				else 
				{
					return new Fruits(imageName, gc, x, y, sizeX, sizeY, shape);
				}
	}

}
