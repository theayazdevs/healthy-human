package ayaz.healthyhuman;

public class FruitDescription {
	private String fruit;
	//create fruit description based on the fruit
	public FruitDescription(String fruit) {
		super();
		this.fruit = fruit;
	}
	//return specific fruit's description
	public String fruitDescription() 
	{
		//show the correct image describing the Fruit
		switch (fruit) {							 
        // Case 1
        case "apple.png":
            // Print statement corresponding case
            //System.out.println("You Ate Apple");								 
            return "appleDescription.png";
		// Case 2
        case "banana.png":								 
            // Print statement corresponding case
            //System.out.println("You Ate Banana");
            return "bananaDescription.png";					 
        // Case 3
        case "cherry.png":								 
            // Print statement corresponding case
            //System.out.println("You Ate Cherry");
            return "cherryDescription.png";				 
        // Case 4
        case "grapes.png":
        	// Print statement corresponding case
            //System.out.println("You Ate Grapes");
            return "grapesDescription.png";
         // Case 5
        case "mango.png":
        	// Print statement corresponding case
            //System.out.println("You Ate Mango");
            return "mangoDescription.png";
         // Case 6
        case "orange.png":
        	// Print statement corresponding case
            //System.out.println("You Ate Orange");
            return "orangeDescription.png";
         // Case 7
        case "pomegranate.png":
        	// Print statement corresponding case
            //System.out.println("You Ate Pomegranate");
            return "pomegranateDescription.png";
         // Case 8
        case "strawberry.png":
        	// Print statement corresponding case
            //System.out.println("You Ate Strawberry");
            return "strawberryDescription.png";
        // Default case
        default:								 
            // Print statement corresponding case
            //System.out.println("no match");
            return "New Fruit";
        }
		
	}

}
