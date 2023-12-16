package finalTest;

public class MainRabbit {
    public static void main(String[] args) throws Exception {
        Receive eatConsumer = new Receive("eatQueue");
        Receive sexualReproductionConsumer = new Receive("sexualReproductionQueue");
        Receive asexualReproductionConsumer = new Receive("asexualReproductionQueue");
        
        eatConsumer.start();
        sexualReproductionConsumer.start();
        asexualReproductionConsumer.start();
    }
}
