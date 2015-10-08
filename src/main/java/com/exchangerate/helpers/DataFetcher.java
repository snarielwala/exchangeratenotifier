package com.exchangerate.helpers;

import java.io.IOException;

import com.exchangerate.pojos.Data;


/**
 * 
 * @author snarielwala
 * Abstract class DataFetcher with the abstract fetchData method
 * which can be implemented accordingly by the respective child classes
 *
 */

abstract class DataFetcher {
	
	public abstract Data fetchData() throws IOException;

}
