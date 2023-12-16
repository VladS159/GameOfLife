package gameOfLife;

public class Main
{
	private static Grid g;

	public static Grid getGrid(){
		return g;
	}

	public static void main(String[] args)
	{
		int maxThreads = Runtime.getRuntime().availableProcessors(); // Get the number of available processors
        System.out.println("Max number of threads: " + maxThreads);
		
		Coordinates lowerPoint = new Coordinates(0,0);
		Coordinates upperPoint = new Coordinates(20,20);
		g = new Grid(upperPoint, lowerPoint);

		for(int i=0; i<15; i++){
			Cell.foodArray.add(new FoodUnit(Main.getGrid()));
		}

		for(int i=0; i<3; i++){
			Cell.cellArray.add(new SexualCell(Main.getGrid()));
		}
		
		for(int i=0; i<1; i++){
			Cell.cellArray.add(new AsexualCell(Main.getGrid()));
		}

		for (Cell cell : Cell.cellArray) {
			cell.start();
		}
	}
}