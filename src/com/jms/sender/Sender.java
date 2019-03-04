package com.jms.sender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
public class Sender {
     
    public static void main(String[] args) throws JMSException, IOException {       
        ConnectionFactory cf=new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
    	Connection con=cf.createConnection();
    	con.start();
        Session session=con.createSession(false,Session.AUTO_ACKNOWLEDGE); 
        Destination des=session.createQueue("test1");
    	MessageProducer producer=session.createProducer(des);
    	TextMessage msg=session.createTextMessage();
		
    	File f=new File("D:\\WCS_Document\\WCS Note.txt");
		BufferedReader br=new BufferedReader(new FileReader(f));
		String s1 = br.readLine();
		StringBuilder sb=new StringBuilder();
		
		while(s1!=null){
			sb.append(s1).append("\n");
			s1=br.readLine();
		}
		String out=(String)sb.toString();
		msg.setText(out);
		
		producer.send(msg);
        System.out.println("Sent message:"+"\n"+ msg.getText() + "'");
        con.close();
    }

}
