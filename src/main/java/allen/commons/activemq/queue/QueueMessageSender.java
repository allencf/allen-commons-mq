package allen.commons.activemq.queue;

import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import allen.commons.activemq.base.MQBaseData;


/**
 * @author allen
 * @description  基于ActiveMQ 点对点方式发送消息的消息发送者测试类
 * @date 2016年8月22日 下午10:37:13
 *
 */
public class QueueMessageSender extends MQBaseData{
	
	
	//消息主题
	private final static String DESTINATION = "mq.queue.test.destination";
	
	
	
	
	public static void run() throws Exception{
		QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,ACTIVE_MQ_BROKER_URL);
		
		QueueConnection connection = factory.createQueueConnection();
		
		connection.start();
		
		QueueSession session = connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
		
		Queue destination = session.createQueue(DESTINATION);
		
		QueueSender sender = session.createSender(destination);
		
		sendMessage(sender, session);
		
		session.commit();
		
		
		if(session != null)  session.close();
		
		if(connection != null) connection.close();
	}
	
	
	
	public static void sendMessage(QueueSender sender, Session session) throws Exception{
		
		for (int i = 0; i < SEND_NUM; i++) { 
			
			String text = "发送第:" + (i+1) + "条消息";
			MapMessage message = session.createMapMessage();
			message.setString("text",text);
			message.setLong("time", System.currentTimeMillis());
			
			System.out.println(message.toString());
			
			sender.send(message);
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	/**
	 * @description
	 * @param
	 * @return
	 */
	public static void main(String[] args) throws Exception {
		run();
	}

}
