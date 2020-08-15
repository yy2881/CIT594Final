package edu.upenn.cit594.processor;

import edu.upenn.cit594.datamanagement.Vio_JSONReader;
import edu.upenn.cit594.datamanagement.ViolationReader;
import edu.upenn.cit594.logging.Logger;

public class JSONProcessor extends Processor{

	
	
	public JSONProcessor(String property_filename,String population_filename,Logger logger,String json_filename) {
		super(property_filename,population_filename,logger, json_filename);
	}

	@Override
	public ViolationReader createViolationReader(Logger logger,String json_filename) {
		// TODO Auto-generated method stub
		return new Vio_JSONReader(logger,json_filename);
	}

	

}
