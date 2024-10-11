package ayaz.healthyhuman;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;

public class HealthyHuman extends Application {
	//creating main stage fields 
	private Stage mainStage;
	//creating fields for the start screen, which the user sees when he starts the application
	private Pane startPane;
	private Scene startScene;
	private ImageView gameTitle;
	private ImageView backImage;
	private Button playBtn;
	private Button howToPlayBtn;
	private Button exitBtn;
	//creating fields for the how to play screen which appears when the user clicks on how to play button
	private Pane howToPlayPane;
	private Scene howToPlayScene;
	private ImageView howToPlayImage;
	private Button howToPlayBackBtn;
	//creating fields for the main playable game screen
	private Pane mainGamePane;
	private Scene mainGameScene;
	private Button mainGameBackBtn;
	private Label mainGameTitle;
	private Button okBtn; 
	private ParallelTransition mainGameTransition;
	private FruitDescription fruitInfo;
	//creating canvas to be used by Pane and 2D graphics context used by canvas
	private Canvas canvas;
	private GraphicsContext gc;
	//factory interface to create game objects
	private Factory factory;
	//field for the main moving character
	private Human superHuman; 
	//array list to store game objects such as fruit objects
	private ArrayList<GameObject> mainGameObjects; 
	//arraylist to store the names of the fruits
	private ArrayList<String> fruitNames;
	//declaring booleans for the movement of the human
	private boolean humanLeft;
	private boolean humanRight;
	private boolean humanUp;
	private boolean humanDown;
	//creating keyboard key event handlers to handle key presses and releases
	private EventHandler<KeyEvent> stopHandler;
	private EventHandler<KeyEvent> movementHandler;
	//declaring animation timer which is called in every frame
	private AnimationTimer timer;
	//creating fields for the screen which appears when the user eats unhealthy food
	private Pane gameOverPane;
	private Scene gameOverScene;
	private ImageView gameOverImage;
	private Label gameOverText;
	private Button gameOverRestartBtn;
	private Button menuBtn;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//setting primary stage to a variable so that the stage can be update with relevant scenes
		mainStage = primaryStage;
		//stopping user from resizing the game window
		primaryStage.setResizable(false);
		//initialising the fields which are used by the start screen
		startPane = new Pane();
		startScene = new Scene(startPane, 800,600);
		//setting background image
		backImage = new ImageView(new Image(HealthyHuman.class.getResource("resources/background1.png").toExternalForm()));
		backImage.setOpacity(0.9);
		backImage.setFitHeight(600);
		backImage.setFitWidth(800);
		//setting the name of the game at top
		gameTitle = new ImageView(new Image(HealthyHuman.class.getResource("resources/gameName.png").toExternalForm()));
		gameTitle.relocate(25, 50);
		//setting the button which starts the main playable game
		playBtn = new Button();
		playBtn.setLayoutX(50);
		playBtn.setLayoutY(200);
		playBtn.setGraphic(new ImageView(new Image(HealthyHuman.class.getResource("resources/playBtn.png").toExternalForm())));
		playBtn.setBackground(null);
		//handling the play button click
		playBtn.setOnAction(e -> { 
									//create objects, start the game and show the main game screen
									toDefault();
									playGame(); 
									mainStage.setScene(mainGameScene);
		});
		//setting the button which shows the how to play screen
		howToPlayBtn = new Button();
		howToPlayBtn.setLayoutX(50);
		howToPlayBtn.setLayoutY(300);
		howToPlayBtn.setGraphic(new ImageView(new Image(HealthyHuman.class.getResource("resources/howToPlayBtn.png").toExternalForm())));
		howToPlayBtn.setBackground(null);
		//handling how to play button
		howToPlayBtn.setOnAction(e -> 
									//showing the how to play screen
									mainStage.setScene(howToPlayScene)
								);
		//initialising the exit button
		exitBtn = new Button(); 
		exitBtn.setLayoutX(50);
		exitBtn.setLayoutY(400);
		exitBtn.setGraphic(new ImageView(new Image(HealthyHuman.class.getResource("resources/exitBtn.png").toExternalForm())));
		exitBtn.setBackground(null);
		exitBtn.setOnAction(e -> 
								//exiting the game completely
								Platform.exit()
							); 
		//inserting the elements on the start game screen
		startPane.getChildren().addAll(backImage, gameTitle, playBtn, howToPlayBtn, exitBtn);

		//initialising the how to play screen fields
		howToPlayPane = new Pane();
		howToPlayScene = new Scene(howToPlayPane, 1200,800);
		howToPlayImage = new ImageView(new Image(HealthyHuman.class.getResource("resources/howToPlay.png").toExternalForm()));
		howToPlayImage.relocate(0,0);
		//initialising the back button which appears on the how to play screen
		howToPlayBackBtn = new Button();
		howToPlayBackBtn.setLayoutX(1050);
		howToPlayBackBtn.setLayoutY(5);
		howToPlayBackBtn.setGraphic(new ImageView(HealthyHuman.class.getResource("resources/backBtn.png").toExternalForm()));
		howToPlayBackBtn.setBackground(null);
		howToPlayBackBtn.setOnAction(e -> 
										//showing the start screen
										mainStage.setScene(startScene)
									); 
		//inserting elements on the how to play screen
		howToPlayPane.getChildren().addAll(howToPlayImage, howToPlayBackBtn);
		
		//initialising the fields of the main playable game -------------------------------------------------------------------- Game Screen
		mainGamePane = new Pane();
		mainGameScene = new Scene(mainGamePane, 1200, 800);
		//initialising the back button which appears on the main game screen
		mainGameBackBtn = new Button();
		mainGameBackBtn.setLayoutX(1050);
		mainGameBackBtn.setLayoutY(0);
		mainGameBackBtn.setGraphic(new ImageView(new Image(HealthyHuman.class.getResource("resources/backBtn.png").toExternalForm())));
		mainGameBackBtn.setBackground(null);
		//handling the back button on main game screen
		mainGameBackBtn.setOnAction(e -> { 
											//stopping the timer, clearing game objects and showing the start screen
											timer.stop();
											mainGameObjects.clear();
											mainStage.setScene(startScene);
										 });
		//initialising title label that appears on the main game screen
		mainGameTitle = new Label();
		mainGameTitle.setLayoutX(400);
		mainGameTitle.setLayoutY(10);
		mainGameTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
		mainGameTitle.setTextFill(Color.DARKGREEN);
		//setting button which appears after the character eats a fruit
		okBtn = new Button();
		okBtn.setLayoutX(350);
		okBtn.setLayoutY(700);
		okBtn.setGraphic(new ImageView(new Image(HealthyHuman.class.getResource("resources/learned.png").toExternalForm()))); 
		okBtn.setBackground(null);
		//images for the animation on the main game screen
		ImageView cloudAnimation = new ImageView(new Image(HealthyHuman.class.getResource("resources/moveBackground1.png").toExternalForm()));
		cloudAnimation.setFitHeight(100);
		cloudAnimation.setFitWidth(1200);
		cloudAnimation.setLayoutX(0);
		cloudAnimation.setLayoutY(700);
		ImageView cloudAnimationA = new ImageView(new Image(HealthyHuman.class.getResource("resources/moveBackground1.png").toExternalForm()));
		cloudAnimationA.setFitHeight(100);
		cloudAnimationA.setFitWidth(1200);
		cloudAnimationA.setLayoutX(0);
		cloudAnimationA.setLayoutY(700);
		//repeated animation on the main game screen
	    TranslateTransition clouds = new TranslateTransition(Duration.seconds(60), cloudAnimation);
	    clouds.setFromX(0);
	    clouds.setToX(1200);
	    clouds.setInterpolator(Interpolator.LINEAR);
	    clouds.setCycleCount(Animation.INDEFINITE);
	    TranslateTransition cloudsAgain = new TranslateTransition(Duration.seconds(60), cloudAnimationA);
	    cloudsAgain.setFromX(-1200);
	    cloudsAgain.setToX(0);
	    cloudsAgain.setInterpolator(Interpolator.LINEAR);
	    cloudsAgain.setCycleCount(Animation.INDEFINITE);
	    mainGameTransition = new ParallelTransition(clouds,cloudsAgain);
		//creating canvas and graphics context for the main game pane
		canvas = new Canvas(1200, 800);
		gc = canvas.getGraphicsContext2D();
		//inserting elements on the main game screen
		mainGamePane.getChildren().addAll(canvas,cloudAnimation, cloudAnimationA, mainGameBackBtn, mainGameTitle, okBtn);
		//key event handlers for the movement of the human character
		movementHandler = new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent key) 
			{
				//if D is pressed, set the value of movement to right to true
				if(key.getCode() == KeyCode.D )
				{
					humanRight = true;
				}
				//if A is pressed, set the value of movement to left to true
				if(key.getCode() == KeyCode.A )
				{
					humanLeft = true;
					//System.out.println("pressed LEFT");
				}
				//if W is pressed, set the value of movement to up to true
				if(key.getCode() == KeyCode.W)
				{
					humanUp = true;
				}
				//if S is pressed, set the value of movement to down to true
				if(key.getCode() == KeyCode.S)
				{
					humanDown = true;
				}
				//System.out.println(key.getCode());
			}	
		};
		//initialising the key release handlers to to stop the movement when the key is no longer pressed
		stopHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				//when the user releases the key, stop the movement of the character
				if(key.getCode() == KeyCode.D)
					humanRight = false;
				if(key.getCode() == KeyCode.A)
					humanLeft = false;
				if(key.getCode() == KeyCode.W)
					humanUp = false;
				if(key.getCode() == KeyCode.S)
					humanDown = false;
			}
		};
		//handling key presses on the main game screen
		mainGameScene.setOnKeyPressed(movementHandler);
		mainGameScene.setOnKeyReleased(stopHandler);
		//initialising the array list to store the game objects
		mainGameObjects = new ArrayList<GameObject>();
		//initialising the screen to show when the user eats unhealthy food
		gameOverPane = new Pane();
		gameOverScene = new Scene(gameOverPane, 1200,800);
		//image to show on the game over screen
		gameOverImage = new ImageView(new Image(HealthyHuman.class.getResource("resources/gameOver.png").toExternalForm()));
		//initialising the label which shows the cause of game over
		gameOverText = new Label();
		gameOverText.setLayoutX(320);
		gameOverText.setLayoutY(180);
		gameOverText.setFont(new Font("Verdana", 30));
		gameOverText.setTextFill(Color.WHITE);
		//initialising the restart button for game over screen
		gameOverRestartBtn = new Button();
		gameOverRestartBtn.setLayoutX(400);
		gameOverRestartBtn.setLayoutY(300);
		gameOverRestartBtn.setGraphic(new ImageView(new Image(HealthyHuman.class.getResource("resources/restart.png").toExternalForm())));
		gameOverRestartBtn.setBackground(null);
		gameOverRestartBtn.setOnAction(e -> {
												//resetting the game to default values, creating the objects again and then showing the main game screen
												toDefault();
												playGame(); 
												mainStage.setScene(mainGameScene);
											}); 
		//back to menu or start screen button
		menuBtn = new Button(); 
		menuBtn.setLayoutX(440);
		menuBtn.setLayoutY(400);
		menuBtn.setGraphic(new ImageView(new Image(HealthyHuman.class.getResource("resources/menuBtn.png").toExternalForm())));
		menuBtn.setBackground(null);
		menuBtn.setOnAction(e -> 
								//showing the start screen
								mainStage.setScene(startScene)
						   ); 
		//inserting elements on the game over screen
		gameOverPane.getChildren().addAll(gameOverImage, gameOverText, gameOverRestartBtn, menuBtn);
		//adding the icon of to the game application
		mainStage.getIcons().add(new Image(HealthyHuman.class.getResource("resources/cherry.png").toExternalForm()));
		//setting the title bar text
		mainStage.setTitle("Healthy Human");
		//showing the start screen
		mainStage.setScene(startScene);
		mainStage.show();
	}
	//create objects and start the timer, to start the game
	private void playGame() { 
			//creating the game objects
			createGameElements(); 
			//showing the text on main game
			mainGameTitle.setText("EAT HEALTHY FOOD");
			//hiding the learned button which only appears when the character eats a fruit
			okBtn.setVisible(false);
			//resetting the human character position to default
			superHuman.defaultPosition();
			mainGameTransition.play();
			//initialising the animation timer for the main game
			timer = new AnimationTimer() { 
				@Override
				public void handle(long now) {
					
						//refill canvas after character movement
						gc.setFill(Color.WHITE);
						gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
						//move the human character based on the key pressed
						if(humanLeft) 
						{
							superHuman.moveLeft();
							//superHuman.update("LEFT");
							//System.out.println("pressed LEFT");
						}
						else if(humanRight) 
						{
							superHuman.moveRight();
						}
						else if(humanUp) 
						{
							superHuman.moveUp();
						}
						else if(humanDown)
						{
							superHuman.moveDown();
						}
						//update the human instance
						superHuman.update();
						boolean fruit = false;
						String overText = "";
						String fruitAte = "";
						//updating the instance of game objects
						for(GameObject gameObj: mainGameObjects) {
							if(gameObj != null) {
								gameObj.update();
								//condition to check for intersection between food and the human
								if(gameObj.getShapeBoundary().getBoundsInParent().intersects(superHuman.getShapeBoundary().getBoundsInParent())){
									//checking if intersected with the burger
									if(gameObj instanceof Burger) 
									{
										overText = "OH NO! You ate an unhealthy Burger.";
									}
									//checking if intersected with the donut
									else if(gameObj instanceof Donut) 
									{ 
										overText = "OH NO! You ate a very sweet Donut.";
									}
									//checking if intersected with the fruit
									else if(gameObj instanceof Fruits) 
									{
										if(fruitNames.contains(((Fruits) gameObj).getFruitImage()))
										{
											fruitAte = fruitNames.get(fruitNames.indexOf(((Fruits) gameObj).getFruitImage()));
											//System.out.println(fruitAte);
											fruit=true;
										}
										else 
										{
												System.out.println("No such fruit!");
										}
									}
								}
							}
						}
						
						//System.out.println(fruitAte);
						//if a fruit is eaten then display that fruit's specific description
						if(fruit) { 
							//stopping the animation timer
							timer.stop();
							//declaring and intialising the fruit description class
							fruitInfo = new FruitDescription(fruitAte);
							String fruitImage = fruitInfo.fruitDescription();
							//System.out.println(fruitImage);
							//show fruit's description
							ImageView fruitDescription = new ImageView(new Image(HealthyHuman.class.getResource("resources/"+fruitImage).toExternalForm()));
							fruitDescription.setFitHeight(600);
							fruitDescription.setFitWidth(600);
							fruitDescription.relocate(300, 100);
							//show description on the main game screen
							mainGamePane.getChildren().addAll(fruitDescription);
							//show the learned button
							okBtn.setVisible(true);
							//handling button that restarts the game after learning about a fruit
					        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
					            public void handle(ActionEvent e)
					            {
					            	playGame();
					            	fruitDescription.setVisible(false);
					            }
					            
					        };
					        // when learned button is pressed
					        okBtn.setOnAction(event);
						}
						//showing the game over screen with the text and buttons
						if(!(overText.equals(""))) { 
							timer.stop();
							mainGameObjects.clear();
							gameOverText.setText(overText);
							mainStage.setScene(gameOverScene);
						}
			}};
			//starting the animation timer again
			timer.start();
	}
	
	//resetting game fields so that the game starts from default values
	private void toDefault() {
		//default array list values
		fruitNames = new ArrayList<String>(Arrays.asList("cherry.png", "apple.png", "grapes.png", "strawberry.png", "banana.png", "orange.png","pomegranate.png", "mango.png"));
		//default movement
		humanLeft = false;
		humanRight = false;
		humanUp = false;
		humanDown = false;
	}
	
	private void createGameElements() {
		//creating the objects that appear on the main game screen
		//making sure there is only one instance of the human instance
		ShapeDrawer.drawRectangle();
		superHuman = Human.getInstance(gc, 1100, 350, new ShapeDrawer());
		//factory pattern to create the objects that appear on the game screen
		factory = new FactoryProduct(gc);
		//showing the fruits on the screen
		int x= 125;
		int y= 200; 
		for( String fruit : fruitNames) 
		{
			//create the fruit object
			ShapeDrawer.drawCircle();
			mainGameObjects.add(factory.createProduct(fruit, x, y, 50, 50, new ShapeDrawer()));
			if(y==200) 
			{
				y=500;
				//System.out.println(y);
			}
			else if(y==500)
			{
				y=200;
				//System.out.println(y);
			}
			x+=125;
		}
		//showing burgers and donuts on the screen
		int xA= 100;
		int yB= 600;
		String unhealthy = "burger.png";
		for(int i = 0; i < 4; i++) 
		{
			ShapeDrawer.drawCircle();
			mainGameObjects.add(factory.createProduct(unhealthy, xA, yB, 0, 0, new ShapeDrawer()));
			if(yB==600 && unhealthy=="burger.png") 
			{
				yB=100;
				unhealthy="donut.png";
				//System.out.println(yB  + " :IF: " + unhealthy);
				mainGameObjects.add(factory.createProduct("donut.png", xA, 350, 0, 0, new ShapeDrawer()));
			}
			else if(yB==100 && unhealthy=="donut.png")
			{
				yB=600;
				unhealthy="burger.png";
				//System.out.println(yB + ":elseIF : " + unhealthy);
				mainGameObjects.add(factory.createProduct("burger.png", xA, 350, 0, 0, new ShapeDrawer()));
			}
			xA+=300;
		}
	}
}
