package gameOfLife;

import finalTest.Send;

public class SexualCell extends Cell
{
    public SexualCell(Grid g){
        super(g);
    }

    @Override
    public void reproduce() throws Exception{
        SexualCell targetCell = computeCellTarget();
        
        if(this.partnerExists()){
        	Object pairReproductionLock = (this.hashCode() < targetCell.hashCode()) ? this : targetCell;
        	setTargetPosition(targetCell.getCellPosition());
        	
            if(this.isNearTarget()){
            	
            	synchronized (pairReproductionLock){
            		if(targetCell.getCellState() == CurrentState.HORNEE){
            			Send reproduceMessage = new Send("sexualReproductionQueue");
            			
            			String onSexualReprodMessage = "";
            			onSexualReprodMessage += "You made a healthy baby cell (sexually ^_^*)\n";
            			onSexualReprodMessage += "Parents: " + this + ", " + targetCell;
            			
            			reproduceMessage.produce(onSexualReprodMessage);
            			
		                System.out.println("Sexual reproduction!");
//		                System.out.println(this + " X coordinate : "+ this.getCellPosition().getXCoordinate() + " Y coordinate : " + this.getCellPosition().getYCoordinate());
//		                System.out.println(targetCell + " X coordinate : "+ targetCell.getCellPosition().getXCoordinate() + " Y coordinate : " + targetCell.getCellPosition().getYCoordinate());
//		                System.out.println();
		                
		                Cell.cellArray.add(new SexualCell(Main.getGrid()));
		                Cell.cellArray.get(Cell.cellArray.size() - 1).start();
		                targetCell.setNoOfMeals(0);
		                targetCell.setCellState(CurrentState.HUNGRY);
		                this.setNoOfMeals(0);
		                this.setCellState(CurrentState.HUNGRY);
            		}
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