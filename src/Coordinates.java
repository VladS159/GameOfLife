public class Coordinates
{
	private int x, y;
	
	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getXCoordinate()
	{
		return this.x;
	}
	
	public int getYCoordinate()
	{
		return this.y;
	}
	
	public void setXCoordinate(int x)
	{
		this.x = x;
	}
	
	public void setYCoordinate(int y)
	{
		this.y = y;
	}
	
	public int getEuclidianDistance(Coordinates entity)
	{
		return (int)Math.sqrt(Math.pow(((double)this.x - (double)entity.getXCoordinate()), 2) + Math.pow(((double)this.y - (double)entity.getYCoordinate()), 2));
	}

	public boolean equals(Object obj){
		Coordinates c = (Coordinates) obj;

		if((x==c.getXCoordinate())&&(y==c.getYCoordinate())){
			return true;
		}
		return false;
	}
}
