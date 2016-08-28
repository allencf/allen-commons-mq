package allen.commons.activemq.topic;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import org.apache.activemq.ActiveMQConnectionFactory;
import allen.commons.activemq.base.MQBaseData;

public class TopicMessageReceiver extends MQBaseData{
	
	
	public final static String DESTINATION = "mq.topic.test.destination";


	public static void run() throws JMSException{
		TopicConnectionFactory factory = null;
		TopicConnection connection = null;
		TopicSession session = null;
		
		factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,ACTIVE_MQ_BROKER_URL);
		
		connection = factory.createTopicConnection();
		
		connection.start();
		
		session = connection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
		
		Topic topic = session.createTopic(DESTINATION);
		
		TopicSubscriber subscriber = session.createSubscriber(topic);
		
		subscriber.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				if(message instanceof MapMessage){
					MapMessage mapMessage = (MapMessage) message;
					try {
						System.out.println(mapMessage.getString("text"));
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		session.commit();
		
		
		if(session != null) session.close();
		
		if(connection != null) connection.close();
		
	}
	
	
	
	public static void main(String[] args) throws JMSException {
		run();
	}
	
	
}
