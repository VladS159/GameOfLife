package gameOfLife;

public class Grid
{
	private static Coordinates upperRightCorner, lowerLeftCorner;
	
	public Grid(Coordinates upperRightCorner, Coordinates lowerLeftCorner)
	{
		this.upperRightCorner = upperRightCorner;
		this.lowerLeftCorner = lowerLeftCorner;
	}
	
	public int getXCoordUpperRightCorner()
	{
		return upperRightCorner.getXCoordinate();
	}
	
	public int getYCoordUpperRightCorner()
	{
		return upperRightCorner.getYCoordinate();
	}
	
	public int getXCoordLowerLeftCorner()
	{
		return lowerLeftCorner.getXCoordinate();
	}
	
	public int getYCoordLowerLeftCorner()
	{
		return lowerLeftCorner.getYCoordinate();
	}
}