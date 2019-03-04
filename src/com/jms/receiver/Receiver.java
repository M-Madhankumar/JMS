package com.jms.receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
public class Receiver {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory cf= new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection con = cf.createConnection();
        con.start();
        Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination des = session.createQueue("test1");
        MessageConsumer consumer = session.createConsumer(des);
         Message message = consumer.receive();
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Received message '" + textMessage.getText() + "'");
        }
        con.close();
    }
}

