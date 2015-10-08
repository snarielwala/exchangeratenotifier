package com.exchangerate.webservice;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.exchangerate.helpers.DataScraper;
import com.exchangerate.helpers.EmailNotifier;
import com.exchangerate.pojos.Data;
import com.exchangerate.pojos.Subscriber;

public class WebService {
	
	
	private static final Logger logger = Logger.getLogger(WebService.class);
	private int retryCounter=0;
	private static Set<Subscriber> subscribers = new HashSet<Subscriber>();
	private DataScraper dataScraper = DataScraper.getDataScraper();
	private EmailNotifier emailNotifier = new EmailNotifier();
	private static ScheduledExecutorService dailyUpdater = Executors.newSingleThreadScheduledExecutor();
	private Data cachedRates=new Data();
	private boolean firstRun=true;
	
	public String addSubscriber(String name, String emailId){	
		
		if (subscribers.contains(new Subscriber(name,emailId))){
		return new String("Subscriber already exists!");
		}
		
		else
		subscribers.add(new Subscriber(name,emailId));
		logger.error("User"+name+":"+emailId+"added");
		return new String("Sucessfully added new subscriber!");
		
	}
	
	public String removeSubscriber(String name, String emailId)
	{	
		if(subscribers.contains(new Subscriber(name,emailId))){
			subscribers.remove(new Subscriber(name,emailId));
			logger.error("User"+name+":"+emailId+"removed");
			return new String("Sucessfully removed subscriber!");
		}
		else
			return new String("No such subscriber. Try Again !");
			
	}
	
	/*
	 * This method updates the cache by fetching the data periodically 
	 * and subsequently updates all the subscribers
	 */
	
	public void UpdateAndNotify()
	{	
		dailyUpdater.scheduleAtFixedRate(new Runnable(){
				@Override
				public void run() {
						
					   //get current date and time
					   //log whenever the service runs
					   DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				       Date dateobj = new Date();
				       logger.error("Update&Notify run at "+df.format(dateobj));
					
					
					try {
							Data temp =dataScraper.fetchData();
							if(firstRun){
								temp.setPreviousRate(temp.getCurrentRate());
							}
							else{
								temp.setPreviousRate(cachedRates.getCurrentRate());
	
							}
							cachedRates=temp;		
							String msg="$->Rupee has changed from"
									+" 1$->"+cachedRates.getPreviousRate()+" Rupees" +" to "
									+" 1$->"+cachedRates.getCurrentRate()+" Rupees";
							
							System.out.println(msg);
							for(Subscriber subscriber: subscribers){
								emailNotifier.sendNotifcation(subscriber,msg);
							}
						retryCounter=0;
						firstRun=false;
						
					  } catch (IOException e) {
						  retryCounter++;
						  if(retryCounter<3){
							  logger.error("Failed to fetch Data from Source");
							  this.run();
						  }
						  else
							  logger.error("Retry Failed! Restart Service!");
					}
					
				}
			}, 1, 24,TimeUnit.HOURS);
		} 
	
	/*
	public static void main(String[] args)
	{
		WebService wsc = new WebService();
		wsc.addSubscriber("Samarth", "samarth.narielwala@gmail.com");
		System.out.println(wsc.subscribers.size());
		wsc.UpdateAndNotify();
		
		
	}
	*/
	
}
