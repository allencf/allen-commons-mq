package allen.commons.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;

public class MqPulisher {
	
	
	//http://shmilyaw-hotmail-com.iteye.com/blog/1897635
	//http://www.cnblogs.com/hoojo/p/active_mq_jms_apache_activeMQ.html
	
	//目标destination
	private Destination[] destination;
	
	//消息生产对象
	private MessageProducer producer;
	
	private Session getSession(){
		String brokerURL = null;
		
		//创建jsm连接工厂
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
		
		try {
			//创建activeMQ连接
			ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection();
			
			//启动连接
			connection.start();
			
			//创建session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//消息发布者
			producer = session.createProducer(null);
			
			return session;
		
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * @description 返回一系列的topic
	 * @param 
	 * @return
	 */
	protected Destination[] setTopics(String[] stocks) throws JMSException{
		destination = new Destination[stocks.length];
		for (int i = 0; i < stocks.length; i++) {
			destination[i] = getSession().createTopic("STOCKS." + stocks[i]);
		}
		return destination;
	}
	
	
	/**
	 * @description 创建消息发送对象 
	 * @param 
	 * @return
	 * @throws JMSException 
	 */
	public Message createStockMessage(String stock,Session session) throws JMSException{
		MapMessage message = session.createMapMessage();
		message.setString("stock", stock);
		return message;
	}
	
	
	/**
	 * @description 发送消息 
	 * @param 
	 * @return
	 * @throws JMSException 
	 */
	protected void sendMessage(String[] stocks) throws JMSException{
		for (int i = 0; i < stocks.length; i++) {
			Message message = createStockMessage(stocks[i], getSession());
			
			System.out.println("sending:" +((ActiveMQMapMessage)message).getContentMap() + "on destination :" + destination[i]);
			
			//发送消息
			producer.send(destination[i], message);
		}
	}
	
	
	
	public static void main(String[] args) {
	
	}
	
}
