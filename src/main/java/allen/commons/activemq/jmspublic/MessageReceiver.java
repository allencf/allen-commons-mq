package allen.commons.activemq.jmspublic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import allen.commons.activemq.base.MQBaseData;


/**
 * @author allen
 * @description 消息接收者对象
 * @date 2016年8月19日 下午5:55:24
 */
public class MessageReceiver extends MQBaseData{
	
	//消息主题
	private final static String DESITINATION = "mq.test.destination";
	
	
	//处理消息的方法
	public static void run() throws JMSException{
		//连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,ACTIVE_MQ_BROKER_URL);
		//创建连接
		Connection connection = factory.createConnection();
		//启动连接
		connection.start();
		//创建会话
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//创建目标主题
		Destination destination = session.createQueue(DESITINATION);
		//创建消费者对象
		MessageConsumer consumer = session.createConsumer(destination);
		
		while(true){
			//接收数据的等待时间 5秒
			TextMessage message =  (TextMessage) consumer.receive(1000*5);
			
			if(message != null){
				System.out.println(message.getText());
			}else{
				break;
			}
		}
		
		session.commit();
		
		//关闭资源
		if(session != null) session.close();
		//关闭资源
		if(connection != null) connection.close();
		
	}
	
	

	public static void main(String[] args) throws JMSException {
		run();

	}

}
