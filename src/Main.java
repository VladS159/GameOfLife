import java.util.ArrayList;

public class Main
{
	private static Grid g;

	public static Grid getGrid(){
		//System.out.println("Upper:("+g.getXCoordUpperRightCorner()+","+g.getYCoordUpperRightCorner()+"); \nLower:("+g.getXCoordLowerLeftCorner()+","+g.getYCoordLowerLeftCorner()+");");
		return g;
	}

	public static void main(String[] args)
	{
		//ArrayList<Cell> cellArray = new ArrayList<>();
		//ArrayList<FoodUnit> foodArray = new ArrayList<>();

		Coordinates lowerPoint = new Coordinates(0,0);
		Coordinates upperPoint = new Coordinates(20,20);
		g = new Grid(upperPoint, lowerPoint);

		for(int i=0; i<10 ; i++){
			Cell.foodArray.add(new FoodUnit(Main.getGrid()));
		}

		for(int i=0; i<3 ; i++){
			Cell.cellArray.add(new SexualCell(Main.getGrid()));
		}
		
		//Cell.cellArray.add(new AsexualCell(Main.getGrid()));

		for (Cell element : Cell.cellArray) {
			element.start();
//			System.out.println(element);
		}
	}
}