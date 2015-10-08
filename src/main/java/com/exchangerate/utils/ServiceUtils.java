/*
 * @author snarielwala
 * This class ServiceUtils contains utility methods used by other classes
 */
package com.exchangerate.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ServiceUtils {
	
	
	/**
	 * 
	 * @param email is the subscribers emailId 
	 * @return true if the emailId is valid, false otherwise
	 */
	public static boolean validateEmail(String email)
	{	
		 String[] tokens = email.split("@");
		 boolean result = true;
		 
		 if (email == null) return false;
		 
		 try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      if (!(tokens.length == 2 && tokens[0]!=null && tokens[1]!=null))
		    	  result=false;
				        
		      }catch (AddressException ex){
		      result = false;
		      }
		 return result;
		
	}
	
	/**
	 * This method validates the name entered by the user
	 * @param name is a string which represents the subscribers name
	 * @return true if the name is not null, false otherwise
	 */
	
	public static boolean validateName(String name)
	{
		if (name==null||name.length()<6)
			return false;
		else 
			return true;	
	}
	
	
	
}
