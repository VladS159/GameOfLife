public class SexualCell extends Cell
{
    public SexualCell(Grid g){
        super(g);
    }
    @Override
    public void reproduce(){
        System.out.println("Sexual reproduction!");
        move();
    }
}