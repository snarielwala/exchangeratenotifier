package com.exchangerate.helpers;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.exchangerate.pojos.Subscriber;



public class EmailNotifier extends Notifier {
	
	@Override
	public void sendNotifcation(Subscriber subscriber, String msg) { 
	
	String host = "localhost";
    String from = "notifier@NotificationSystem.com";
    String to = subscriber.getEmailId();
    Properties properties = System.getProperties();
    System.out.println(properties.toString());
    properties.setProperty("mail.smtp.host", host);
    Session session = Session.getInstance(properties);
    MimeMessage message = new MimeMessage(session);
    
    try {
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Update from Notification Service");
		message.setText(msg);
		msg="<title> Exchange Rate Notification </title><body><b> Hi ! "+ subscriber.getName() +"</b><br/>"+
				"<b>Today's Currency Exchange Rate Update from XOOM<b><br/>"+msg +"<br/><a href=\"https://www.xoom.com/india/send-money\">Click here to go to zoom</a>";
		message.setContent(msg, "text/html");
		Transport.send(message);
		message.setFrom(new InternetAddress(from));
		
	} catch (MessagingException e) {
		
		e.printStackTrace();
	}

  
		
	}

	
	
}
