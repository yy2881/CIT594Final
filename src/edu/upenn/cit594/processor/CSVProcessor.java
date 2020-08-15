package edu.upenn.cit594.processor;

import edu.upenn.cit594.datamanagement.Vio_CSVReader;
import edu.upenn.cit594.datamanagement.ViolationReader;
import edu.upenn.cit594.logging.Logger;

public class CSVProcessor extends Processor{
    
	
    
	
	public CSVProcessor(String property_filename,String population_filename,Logger logger,String violation_filename) {
		super(property_filename, population_filename,logger, violation_filename);
		
	}




	@Override
	public ViolationReader createViolationReader(Logger logger,String violation_filename) {
		// TODO Auto-generated method stub
		return new Vio_CSVReader(logger,violation_filename);
	}




}
