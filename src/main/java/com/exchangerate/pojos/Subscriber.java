package com.exchangerate.pojos;

import com.sun.org.apache.xpath.internal.operations.Equals;


/**
 * 
 * @author snarielwala
 * Subscriber class models a subscriber and stores his/her name and emailId
 *
 */

public class Subscriber {
	
	public Subscriber(String name, String email)
	{
		this.name=name;
		this.emailId=email;
	}
	
	private String emailId;
	private String name;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Subscriber or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Subscriber)) {
            return false;
        }
        
        // typecast o to Complex so that we can compare data members 
        Subscriber subscriber = (Subscriber) o;
        
        // Compare the data members and return accordingly 
        return this.emailId.equals(subscriber.emailId) && this.name.equals(subscriber.name);
    }
	
	 @Override
	    public int hashCode(){
	        return this.emailId.hashCode()+this.name.hashCode();
	    }
	
	
}
	
	
	

