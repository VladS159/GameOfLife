package gameOfLife;

import finalTest.Send;

public class AsexualCell extends Cell
{
    public AsexualCell(Grid g){
        super(g);
    }
    @Override
    public void reproduce() throws Exception{
    	Send reproduceMessage = new Send("asexualReproductionQueue");
    	
    	String onAsexualReprodMessage = "";
    	onAsexualReprodMessage += "You made a baby cell.. kinda (asexually 6-6)\n";
    	onAsexualReprodMessage += "Parent: " + this;
    	
		reproduceMessage.produce(onAsexualReprodMessage);
		
        System.out.println("pov of " + this + ": Asexual reproduction!");
        Cell.cellArray.add(new AsexualCell(Main.getGrid()));

        int lastIndex = Cell.cellArray.size() - 1;
        Cell.cellArray.get(lastIndex).start();

        setNoOfMeals(0);
        setTStarve(20);
    }
}