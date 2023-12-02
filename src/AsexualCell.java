public class AsexualCell extends Cell
{
    public AsexualCell(Grid g){
        super(g);
    }
    @Override
    public void reproduce(){
        System.out.println("pov of " + this + ": Asexual reproduction!");
        Cell.cellArray.add(new AsexualCell(Main.getGrid()));

        int lastIndex = Cell.cellArray.size() - 1;
        Cell.cellArray.get(lastIndex).start();

        setNoOfMeals(0);
        setTStarve(20);
    }
}