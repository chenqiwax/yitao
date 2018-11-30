package cn.et.yitao.activeMq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年11月06日 下午 3:36
 */
public class ActiveMQTest {
    @Test
    public void testQueueProducer() throws JMSException {
        //创建ConnectionFactory对象,需要指定服务端ip及端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.4.110:61616");
        //使用ConnectionFactoru对象,创建一个Connection对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建一个Session对象,
        // 第一个参数:是否开启事务(true:开启事务)
        //第二个参数:当第一个参数为false时,才有意义,1:自动应答,2:手动应答.一般是自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用Session创建一个Destination对象(topic,queue),c
        //此处创建一个Queue对象.参数:队列名称
        Queue queue = session.createQueue("test-queue");
        //使用Session创建一个Producer对象.
        MessageProducer producer = session.createProducer(queue);
        //创建一个Message对象(创建一个TextMessage对象)
        TextMessage activeMqTest = session.createTextMessage("hello activeMq test12");
        //使用Producer对象发送消息
        producer.send(activeMqTest);
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testQueueConsumer() throws JMSException, IOException {
        //创建一个ConnectionFactory对象
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.4.110:61616");
        //从ConnectionFactory 对象中获得一个Connection对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用Session对象创建一个Destination对象.和发送端保持一致queue,并且对列名称一致
        Queue queue = session.createQueue("test-queue");
        //Session对象创建一个Consumer对象
        MessageConsumer consumer = session.createConsumer(queue);
        //接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String text = null;
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //等待键盘输入
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicProduce() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.4.110:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("hello activeMq,topic test");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer() throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.4.110:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String text = null;
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("topic的消费端`````````");
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
