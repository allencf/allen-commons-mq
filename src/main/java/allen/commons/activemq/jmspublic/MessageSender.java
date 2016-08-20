package allen.commons.activemq.jmspublic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import allen.commons.activemq.base.MQBaseData;

/**
 * @author allen
 * @description  这里主要是通过JSM公共 消息模式发送MQ消息 
 * @date 2016年8月19日 下午3:29:46
 */
public class MessageSender extends MQBaseData{
	
	
	//http://shmilyaw-hotmail-com.iteye.com/blog/1897635 
	//http://www.cnblogs.com/hoojo/p/active_mq_jms_apache_activeMQ.html
	
	
	private final static String DESTINATION = "mq.test.destination";
	
	
	public static void run() throws JMSException{
		//创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,ACTIVE_MQ_BROKER_URL);
		//创建连接
		Connection connection = factory.createConnection();
		//启动连接
		connection.start();
		//创建session会话
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		//创建一个消息列队
		Destination destination = session.createQueue(DESTINATION);
		//创建消息提供者
		MessageProducer producer = session.createProducer(destination);
		//设置持久化
		//producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		messageSend(session, producer);
		
		//提交会话
		session.commit();
		
		if(session != null) session.close();
		
		if(connection != null) session.close();
	}
	
	
	/**
	 * @description 发送消息 
	 * @param 
	 * @return
	 */
	public static void messageSend(Session session,MessageProducer producer) throws JMSException{
		
		for (int i = 0; i < SEND_NUM; i++) {
			String messageText = "发送消息第:"+(i+1)+"条";
			Message message = session.createTextMessage(messageText);
			System.out.println(message.toString());
			producer.send(message);
		}
		
	}
	
	
	
	public static void main(String[] args) throws JMSException {
		run();
	}
	
}
