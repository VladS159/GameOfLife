public class AsexualCell extends Cell
{
    public AsexualCell(Grid g){
        super(g);
    }
    @Override
    public void reproduce(){
        System.out.println("Asexual reproduction!");
        move();
    }
}