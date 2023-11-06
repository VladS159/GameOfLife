package theGameOfLife;

public class CellThread extends Thread
{
	private Cell cell;
	
	public CellThread(Cell cell)
	{
		this.cell = cell;
	}
}
