package rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class Consumer implements MessageListener {

	public void onMessage(Message message) {
		System.out.println(new String(message.getBody()));
	}

}
