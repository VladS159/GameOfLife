public class SexualCell extends Cell
{
    public SexualCell(Grid g){
        super(g);
    }

    @Override
    public void reproduce(){
        SexualCell targetCell = computeCellTarget();
        
        if(this.partnerExists()){
        	Object pairReproductionLock = (this.hashCode() < targetCell.hashCode()) ? this : targetCell;
        	setTargetPosition(targetCell.getCellPosition());
            if(this.isNearTarget()){
            	synchronized (pairReproductionLock){
	                System.out.println("Sexual reproduction!");
	                System.out.println(this + " X coordinate : "+ this.getCellPosition().getXCoordinate() + " Y coordinate : " + this.getCellPosition().getYCoordinate());
	                System.out.println(targetCell + " X coordinate : "+ targetCell.getCellPosition().getXCoordinate() + " Y coordinate : " + targetCell.getCellPosition().getYCoordinate());
	                System.out.println();
	                
	                Cell.cellArray.add(new SexualCell(Main.getGrid()));
	                Cell.cellArray.get(Cell.cellArray.size() - 1).start();
	                targetCell.setNoOfMeals(0);
	                this.setNoOfMeals(0);
            	}
            }
            else{
                move();
            }
        }
        else{
            setTStarve(getTStarve() - 1);
        }
    }
}