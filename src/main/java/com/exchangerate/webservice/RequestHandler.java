/**
 * This class WebService contains methods 
 * corresponding to the service endpoints for subscribing/unsubscribing to the 
 * exhchange-rate notification service 
 * @author snarielwala
 * 
 */

package com.exchangerate.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;import javax.ws.rs.FormParam;
import javax.ws.rs.GET;import javax.ws.rs.POST;
import javax.ws.rs.Path;import javax.ws.rs.PathParam;import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.BasicConfigurator;

import com.exchangerate.utils.ServiceUtils;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/notification")
@Produces(MediaType.APPLICATION_JSON)
public class RequestHandler {
	WebService controller;
	
	public RequestHandler(){
	controller = new WebService();
	controller.UpdateAndNotify();
	BasicConfigurator.configure();
	}

	/**
	 * 
	 * @param subscribe parameter indicating that the subscriber wants to subscribe to the service
	 * @param name name of the subscriber
	 * @param email email of the subscriber
	 * @return message indicating success after parameter validation
	 */
	@POST
	@Path("/subscribe")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response subscribe(@FormParam("subscribe") String subscribe, @FormParam("name") String name,@FormParam("email") String email) {
	    
		
		if(subscribe.equals("subscribe")&&ServiceUtils.validateEmail(email)&&ServiceUtils.validateName(name)){
		controller.addSubscriber(name, email);
		return Response
				.status(Response.Status.OK)
				.entity("User "+name+" Successfully Subscribed!").build();
		}
		else 
		return Response	
				.status(Response.Status.OK)
				.entity("Invalid Form Parameters! Enter again!").build();
	}
	
	/**
	 * 
	 * @param unsubscribe parameter indicating that the subscriber wants to subscribe to the service
	 * @param name name of the subscriber
	 * @param email email of the subscriber
	 * @return message indicating success after parameter validation
	 */
	@POST
	@Path("/unsubscribe")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response unsubscribe(@FormParam("unsubscribe") String unsubscribe, @FormParam("name") String name,@FormParam("email") String email) {
		
		if(unsubscribe.equals("unsubscribe")&&ServiceUtils.validateEmail(email)&&ServiceUtils.validateName(name)){
			controller.addSubscriber(name, email);
			return Response
					.status(Response.Status.OK)
					.entity("User "+name+" Successfully Subscribed!").build();
			}	
			else 
			return Response
					.status(Response.Status.OK)
					.entity("Successfully Un-Subscribed !").build();
	}
	
	
	
}
