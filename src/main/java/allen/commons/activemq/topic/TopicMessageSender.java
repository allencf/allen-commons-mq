package allen.commons.activemq.topic;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import org.apache.activemq.ActiveMQConnectionFactory;
import allen.commons.activemq.base.MQBaseData;


/**
 * @author allen
 * @description 基于发布/订阅模式发布消息的MQ测试类 
 * @date 2016年8月28日 下午10:16:10
 *
 */
public class TopicMessageSender extends MQBaseData{
	
	
	/**
	 * 消息主题
	 */
	private final static String DESINATION = "mq.topic.test.distination";
	
	
	public static void run() throws Exception{
		//连接工厂
		TopicConnectionFactory factory = null;
		//连接
		TopicConnection connection = null;
		
		factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, ACTIVE_MQ_BROKER_URL);
		try {
			connection = factory.createTopicConnection();
			connection.start();
			TopicSession session = connection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(DESINATION);
			
			TopicPublisher publish = session.createPublisher(topic);
			
			sendMessage(session , publish);
			
			session.commit();
			
			
			Thread.sleep(1000 * 100); 
			
			if(session != null) session.close();
			if(connection != null) connection.close();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void sendMessage(Session session ,TopicPublisher publish) throws Exception{
		for (int i = 0; i <SEND_NUM ; i++) {
			MapMessage message = session.createMapMessage();
			message.setString("text", String.valueOf(i));
			publish.send(message);
			
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		run();
	}

}
