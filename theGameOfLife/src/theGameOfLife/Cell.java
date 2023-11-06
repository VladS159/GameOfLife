package theGameOfLife;

import java.util.Random;

public abstract class Cell
{
//	- atribute -> t_full
//			   -> t_starve
//			   -> point
//			   -> static - numberOf
//
//	- metoda abstracta - reproduce
//	- metoda abstracta - eat
//  - metoda abstracte - move
	
	private State state;
	private Coordinates position;
	private int t_starve;
	private static int numberOfCells = 0;
	
	public Cell()
	{
		Random random = new Random();
		
		this.state = State.HUNGRY;
		this.t_starve = 8;
		this.numberOfCells++;
		this.position.setXCoordinate(random.nextInt(Grid.getXCoordUpperRightCorner() - Grid.getXCoordLowerLeftCorner() + 1) + Grid.getXCoordLowerLeftCorner());
		this.position.setYCoordinate(random.nextInt(Grid.getYCoordUpperRightCorner() - Grid.getYCoordLowerLeftCorner() + 1) + Grid.getYCoordLowerLeftCorner());
	}
}
