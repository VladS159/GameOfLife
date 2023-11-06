package theGameOfLife;

import java.util.Random;

public class FoodUnit
{
//	- atribute -> x
//			   -> y
//			   -> static - numberOf
	private final int nutritionalValue = 3;
	private Coordinates position;
	private static int numberOfFoodUnits = 0;
	
	public FoodUnit()
	{
		Random random = new Random();
		
		this.numberOfFoodUnits++;
		
		this.position.setXCoordinate(random.nextInt(Grid.getXCoordUpperRightCorner() - Grid.getXCoordLowerLeftCorner() + 1) + Grid.getXCoordLowerLeftCorner());
		this.position.setYCoordinate(random.nextInt(Grid.getYCoordUpperRightCorner() - Grid.getYCoordLowerLeftCorner() + 1) + Grid.getYCoordLowerLeftCorner());
	}
}
