/*
 * This class DataFetcher fetches/scrapes data from all websites 
 * Additionally, it allows you to add new websites from which you want updates 
 * and remove existing ones
 * @Author snarielwala
 */
package com.exchangerate.helpers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.exchangerate.pojos.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DataScraper extends DataFetcher {
	
	
	private static final String XOOM_URL="https://www.xoom.com/india/send-money";
	private static Set<String> listOfSources = new HashSet<String>();
	private static final DataScraper singleDataScraper=new DataScraper();


	public void addSource(String url){
		listOfSources.add(url);
	}
	/*
	 * Private Constructor to ensure only one instance of this class is created
	 */
	private DataScraper(){
	}
	
	/*
	 * Static Factory method which returns the singleton instance
	 */
	public static DataScraper getDataScraper()
	{
		return singleDataScraper;
	}
	
	/* 
	 * This method scrapes data from the website and returns a data object
	 */
	public Data fetchData() throws IOException
	{	
		Document doc = Jsoup.connect(XOOM_URL).get();
		String title = doc.title();
		Elements rateElement = doc.getElementsByTag("em");
		int startIndex = rateElement.text().indexOf("=");
		int endIndex = rateElement.text().indexOf("I");
		String rateString = rateElement.text().substring(startIndex+2, endIndex-1);
		
		Data newRates = new Data();
		newRates.setCurrentRate(Double.parseDouble(rateString));

		return newRates;
	}
	
}
