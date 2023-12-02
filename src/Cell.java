import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Cell extends Thread
{
//	- atribute -> t_full
//			   -> t_starve
//			   -> point
//			   -> static - numberOf
//
//	- metoda abstracta - reproduce
//	- metoda abstracta - eat
//  - metoda abstracte - move
	
	private CurrentState state;
	private Coordinates position = new Coordinates(0,0);
	private Coordinates targetPosition = new Coordinates(0,0);
	private int t_starve;
	private int noOfMeals;

	static CopyOnWriteArrayList<Cell> cellArray = new CopyOnWriteArrayList<>();
	static CopyOnWriteArrayList<FoodUnit> foodArray = new CopyOnWriteArrayList<>();

	public Cell(Grid g)
	{
		Random random = new Random();
		
		this.state = CurrentState.HUNGRY;
		this.t_starve = 20;
		this.noOfMeals = 0;
		this.position.setXCoordinate(random.nextInt(g.getXCoordUpperRightCorner() - g.getXCoordLowerLeftCorner() + 1) + g.getXCoordLowerLeftCorner());
		this.position.setYCoordinate(random.nextInt(g.getYCoordUpperRightCorner() - g.getYCoordLowerLeftCorner() + 1) + g.getYCoordLowerLeftCorner());
	}

	public boolean isNearTarget(){
		if((position.getXCoordinate() == (targetPosition.getXCoordinate())) && (position.getYCoordinate() == (targetPosition.getYCoordinate()))){
//			System.out.print("pov of: " + this + ",they are near: X:(" + position.getXCoordinate() + "," + targetPosition.getXCoordinate() + "); ");
//			System.out.println("Y:(" + position.getYCoordinate() + "," + targetPosition.getYCoordinate() + ");");
			return true;
		}
//		System.out.print("pov of: " + this + ",they are not near: X:(" + position.getXCoordinate() + "," + targetPosition.getXCoordinate() + "); ");
//		System.out.println("Y:(" + position.getYCoordinate() + "," + targetPosition.getYCoordinate() + ");");
		return false;
	}

	public FoodUnit computeFoodTarget(){
		int minDistance=Integer.MAX_VALUE;
		FoodUnit nearestFood = new FoodUnit(Main.getGrid());
		Coordinates posNearestFood=new Coordinates(0,0);
		for(FoodUnit foodUnit : Cell.foodArray){
			int currentDistance=this.position.getEuclidianDistance(foodUnit.getFoodPosition());
			if(minDistance>currentDistance){
				minDistance=currentDistance;
				nearestFood = foodUnit;
			}
		}
//		System.out.println("Food X coordinate: " + posNearestFood.getXCoordinate() + "; Y coordinate: " + posNearestFood.getYCoordinate());
		targetPosition = nearestFood.getFoodPosition();
		return nearestFood;
	}

	public SexualCell computeCellTarget(){
		int minDistance=Integer.MAX_VALUE;
		Cell nearestCell=new SexualCell(Main.getGrid());
		for(Cell cell : Cell.cellArray){
			int currentDistance=this.position.getEuclidianDistance(cell.position);
			if((minDistance>currentDistance) && (cell.state == CurrentState.HORNEE) && (cell instanceof SexualCell) && (this != cell)){
				minDistance=currentDistance;
				nearestCell = cell; /* SA VENITI VLAD SI BEA SI ATAT AICI, PLS */
			}
		}
		targetPosition = nearestCell.getCellPosition();
		return (SexualCell) nearestCell;
	}

	public void eat(){
		FoodUnit targetFood = computeFoodTarget();
		if(!Cell.foodArray.isEmpty()){
			if(isNearTarget()){
				synchronized (targetFood){
					System.out.print("You ate the apple from ");
					System.out.println("X coordinate: " + targetFood.getFoodPosition().getXCoordinate() + "; Y coordinate: " + targetFood.getFoodPosition().getYCoordinate());
					System.out.println(this + " X coordinate : "+ this.position.getXCoordinate() + " Y coordinate : " + this.position.getYCoordinate());
					System.out.println();
	
					t_starve+=FoodUnit.getTFull();
					boolean first=true;
					for(FoodUnit foodUnit : Cell.foodArray){
						if(targetPosition.equals(foodUnit.getFoodPosition()) && (first)){
							Cell.foodArray.remove(foodUnit);
							first=false;
						}
					}
					noOfMeals++;
				}
			}
			else{
				move();
			}
		}
		else{
			t_starve--;
		}
	}

	public abstract void reproduce();

	public void move(){
		System.out.println(this + ": X coordinate: " + position.getXCoordinate() + "; Y coordinate: " + position.getYCoordinate()+"; t_starve: "+t_starve);

		if(position.getXCoordinate()>targetPosition.getXCoordinate()){
			position.setXCoordinate(position.getXCoordinate()-1);
		}
		else if(position.getXCoordinate()<targetPosition.getXCoordinate()){
			position.setXCoordinate(position.getXCoordinate()+1);
		}

		if(position.getYCoordinate()>targetPosition.getYCoordinate()){
			position.setYCoordinate(position.getYCoordinate()-1);
		}
		else if(position.getYCoordinate()<targetPosition.getYCoordinate()){
			position.setYCoordinate(position.getYCoordinate()+1);
		}

		t_starve--;
	}

	public void run()
	{
		while (t_starve != 0) {
			if(noOfMeals >= 2 && t_starve > 8){
				state = CurrentState.HORNEE;
//				System.out.println(this + " is hornee with " + this.noOfMeals + " meals.");
			}
			else{
				state = CurrentState.HUNGRY;
			}
//			System.out.println("Thread is running...");
			if (state == CurrentState.HORNEE) {
				reproduce();
			} else {
				eat();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		Random random = new Random();
		int randomNumber = random.nextInt(5) + 1;

		for(int i=0;i<randomNumber;i++){
			Cell.foodArray.add(new FoodUnit(Main.getGrid()));
		}

		for(Cell cell : Cell.cellArray){
			if((t_starve == 0)){
				Cell.cellArray.remove(cell);
			}
		}

		System.out.println("Cell " + this + " died :(");
	}

	public Coordinates getTargetPosition(){
		return targetPosition;
	}

	public void setTargetPosition(Coordinates newTargetPostion){
		this.targetPosition = newTargetPostion;
	}

	public int getTStarve(){
		return t_starve;
	}

	public void setTStarve(int tStarve){
		this.t_starve = tStarve;
	}

	public Coordinates getCellPosition(){
		return position;
	}

	public void setNoOfMeals(int noOfMeals){
		this.noOfMeals = noOfMeals;
	}

	public boolean partnerExists(){
		boolean flag = false;
		for(Cell cell : Cell.cellArray){
			if((cell instanceof SexualCell) && (cell.state == CurrentState.HORNEE) && (cell != this)){
				flag = true;
			}
		}
		return flag;
	}

//	public String toString(){
//		return "X coordinate: " + position.getXCoordinate() + "; Y coordinate: " + position.getYCoordinate();
//	}

}