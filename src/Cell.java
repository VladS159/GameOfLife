import java.util.ArrayList;
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
	private static int numberOfCells = 0;

	static CopyOnWriteArrayList<Cell> cellArray = new CopyOnWriteArrayList<>();
	static CopyOnWriteArrayList<FoodUnit> foodArray = new CopyOnWriteArrayList<>();

	public Cell(Grid g)
	{
		Random random = new Random();
		
		this.state = CurrentState.HUNGRY;
		this.t_starve = 20;
		this.numberOfCells++;
		this.position.setXCoordinate(random.nextInt(g.getXCoordUpperRightCorner() - g.getXCoordLowerLeftCorner() + 1) + g.getXCoordLowerLeftCorner());
		this.position.setYCoordinate(random.nextInt(g.getYCoordUpperRightCorner() - g.getYCoordLowerLeftCorner() + 1) + g.getYCoordLowerLeftCorner());
	}

	public boolean isNearTarget(){
		if((position.getXCoordinate() == (targetPosition.getXCoordinate())) && (position.getYCoordinate() == (targetPosition.getYCoordinate()))){
			return true;
		}
		return false;
	}

	public Coordinates computeTarget(){
		if(state == CurrentState.HORNEE) {
			int minDistance=Integer.MAX_VALUE;
			Coordinates posNearestCell=new Coordinates(0,0);
			for(Cell cell : Cell.cellArray){
				int currentDistance=this.position.getEuclidianDistance(cell.position);
				if(minDistance>currentDistance){
					minDistance=currentDistance;
					posNearestCell.setXCoordinate(cell.position.getXCoordinate());
					posNearestCell.setYCoordinate(cell.position.getYCoordinate());
				}
			}
			return posNearestCell;
		}
		else{
			int minDistance=Integer.MAX_VALUE;
			Coordinates posNearestFood=new Coordinates(0,0);
			for(FoodUnit foodUnit : Cell.foodArray){
				int currentDistance=this.position.getEuclidianDistance(foodUnit.getFoodPosition());
				if(minDistance>currentDistance){
					minDistance=currentDistance;
					posNearestFood.setXCoordinate(foodUnit.getFoodPosition().getXCoordinate());
					posNearestFood.setYCoordinate(foodUnit.getFoodPosition().getYCoordinate());
				}
			}
			System.out.println("Food X coordinate: " + posNearestFood.getXCoordinate() + "; Y coordinate: " + posNearestFood.getYCoordinate());
			return posNearestFood;
		}
	}

	public void eat(){
		targetPosition=computeTarget();
		if(!Cell.foodArray.isEmpty()){
			if(isNearTarget()){
				System.out.println("You ate an apple!");
				t_starve+=FoodUnit.getTFull();
				boolean first=true;
				for(FoodUnit foodUnit : Cell.foodArray){
					if(targetPosition.equals(foodUnit.getFoodPosition()) && (first)){
						Cell.foodArray.remove(foodUnit);
						first=false;
					}
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
		System.out.println("X coordinate: " + position.getXCoordinate() + "; Y coordinate: " + position.getYCoordinate()+"; t_starve: "+t_starve);

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
		System.out.println("Cell died :(");
	}

	public String toString(){
		return "X coordinate: " + position.getXCoordinate() + "; Y coordinate: " + position.getYCoordinate();
	}
}