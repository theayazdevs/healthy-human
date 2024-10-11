package ayaz.healthyhuman;

public interface Factory {
	//abstract method to create game objects
	GameObject createProduct(String imageName, double x, double y, double sizeX, double sizeY, ShapeBoundary shape);
}
