import java.util.Random;

public class FoodUnit
{
//	- atribute -> x
//			   -> y
//			   -> static - numberOf
	private static final int tFull = 8;
	private Coordinates position = new Coordinates(0,0);
	private static int numberOfFoodUnits = 0;
	
	public FoodUnit(Grid g)
	{
		Random random = new Random();
		
		this.numberOfFoodUnits++;
		
		this.position.setXCoordinate(random.nextInt(g.getXCoordUpperRightCorner() - g.getXCoordLowerLeftCorner() + 1) + g.getXCoordLowerLeftCorner());
		this.position.setYCoordinate(random.nextInt(g.getYCoordUpperRightCorner() - g.getYCoordLowerLeftCorner() + 1) + g.getYCoordLowerLeftCorner());
	}

	public static int getTFull(){
		return tFull;
	}

	public Coordinates getFoodPosition(){
		return this.position;
	}
}