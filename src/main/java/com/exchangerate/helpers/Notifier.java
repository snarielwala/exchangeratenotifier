package com.exchangerate.helpers;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.exchangerate.pojos.Subscriber;


/**
 * 
 * @author snarielwala
 * Abstract class Notifier with the abstract sendNotification method
 * which can be implemented by the child classes
 *
 */
abstract class Notifier {
	
	public abstract void sendNotifcation(Subscriber subscribers, String msg);
	

}
