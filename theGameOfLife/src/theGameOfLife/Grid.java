package theGameOfLife;

public class Grid
{
	private static Coordinates upperRightCorner, lowerLeftCorner;
	
	public Grid(Coordinates upperRightCorner, Coordinates lowerLeftCorner)
	{
		this.upperRightCorner = upperRightCorner;
		this.lowerLeftCorner = lowerLeftCorner;
	}
	
	public static int getXCoordUpperRightCorner()
	{
		return upperRightCorner.getXCoordinate();
	}
	
	public static int getYCoordUpperRightCorner()
	{
		return upperRightCorner.getYCoordinate();
	}
	
	public static int getXCoordLowerLeftCorner()
	{
		return lowerLeftCorner.getXCoordinate();
	}
	
	public static int getYCoordLowerLeftCorner()
	{
		return lowerLeftCorner.getYCoordinate();
	}
}
