package finalTest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

//import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.Lock;

public class Send {
	private static String queName;
	private ConnectionFactory factory;

    public Send(String queName)
    {
  	//lock.lock();
  	  Send.queName = queName;
  	  factory = new ConnectionFactory();
      factory.setHost("localhost");
    }
	
    public void produce(String message) throws Exception {
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();

            channel.queueDeclare(queName, false, false, false, null);
            channel.basicPublish("", queName, null, message.getBytes());

            System.out.println("Message sent: " + message);
        }
    }
}
