package finalTest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.util.concurrent.TimeoutException;
import java.io.IOException;


public class Receive extends Thread{

	private String queName;
	private Channel channel;
	
	public Receive(String queName) throws TimeoutException, IOException{
    	this.queName = queName;
    	ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(queName, false, false, false, null);
    }
	
    public void run(){
        System.out.println("Waiting for messages...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Message received: " + message);
        };

        try {
			channel.basicConsume(queName, false, deliverCallback, consumerTag -> { });
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}