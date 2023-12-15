package finalTest;

public class MainRabbit {
    public static void main(String[] args) throws Exception {
//    	Send producer = new Send("eatQueue");
//        producer.produce("please work..");

        Receive eatConsumer = new Receive("eatQueue");
        Receive sexualReproductionConsumer = new Receive("sexualReproductionQueue");
        Receive asexualReproductionConsumer = new Receive("asexualReproductionQueue");
        
        eatConsumer.start();
        sexualReproductionConsumer.start();
        asexualReproductionConsumer.start();
        
        try {
        	eatConsumer.join();
        	sexualReproductionConsumer.join();
        	asexualReproductionConsumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
//        Send producer2 = new Send("eatQueue0");
//        producer2.produce("please work2..");
    }
}
