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

		Coordinates lowerPoint = new Coordinates(10,10);
		Coordinates upperPoint = new Coordinates(50,50);
		g = new Grid(upperPoint, lowerPoint);

		SexualCell c1 = new SexualCell(g);
//		AsexualCell c2 = new AsexualCell(g);
		FoodUnit f1 = new FoodUnit(g);
		FoodUnit f2 = new FoodUnit(g);

		Cell.cellArray.add(c1);
//		cellArray.add(c2);
		Cell.foodArray.add(f1);
		//Cell.foodArray.add(f2);
		System.out.println("Food f1 X coordinate: " + Cell.foodArray.get(0).getFoodPosition().getXCoordinate() + "; Y coordinate: " + Cell.foodArray.get(0).getFoodPosition().getYCoordinate());

		for (Cell element : Cell.cellArray) {
			element.start();
//			System.out.println(element);
		}
	}
}