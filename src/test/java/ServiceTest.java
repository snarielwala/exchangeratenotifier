/**
 * @author snarielwala
 * ServiceTest is the class that contains unit-tests for the web-service
 */
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.exchangerate.helpers.DataScraper;
import com.exchangerate.helpers.EmailNotifier;
import com.exchangerate.pojos.Data;
import com.exchangerate.pojos.Subscriber;
import com.exchangerate.utils.ServiceUtils;
import com.exchangerate.webservice.WebService;
import com.sun.xml.internal.ws.api.WSService;

public class ServiceTest {

	
	//testing the DataScraper and its methods
	@Test
	public void testDataFetcher()
	{
		DataScraper testScraper= DataScraper.getDataScraper();
		   try {
			assertNotNull("Not Null Test",testScraper.fetchData());
			assertEquals(60.52, testScraper.fetchData());
			} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/*
	@Test
	public void testEmailNotifier()
	{
		WebService wsc = new WebService();
		wsc.addSubscriber("Samarth", "samarth.narielwala@gmail.com");
		wsc.UpdateAndNotify();
		
	}
	*/
	
	//testing the subscriber class and its equal method
	@Test
	public void testSubscriber()
	{
		Set<Subscriber> subscribers = new HashSet<Subscriber>();
		subscribers.add(new Subscriber("Samarth", "samarth.narielwala@gmail.com"));
		assertEquals(1,subscribers.size());
		subscribers.remove(new Subscriber("Samarth", "samarth.narielwala@gmail.com"));
		assertEquals(0,subscribers.size());
	}
	
	//testing form validation methods
	@Test
	public void testFormValidation()
	{
		assertEquals(new Boolean("true"), ServiceUtils.validateEmail("abc@gmail.com"));
		assertEquals(new Boolean("false"), ServiceUtils.validateEmail("abc&gmail.com"));
		assertEquals(new Boolean("false"), ServiceUtils.validateName("123"));
		assertEquals(new Boolean("false"), ServiceUtils.validateName("trunkclub"));

	}
	

}
