package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.logging.Logger;

public abstract class ViolationReader {
	public Logger logger;
	public String violation_filename;
	
	
	public ViolationReader(Logger logger, String violation_filename) {
		this.logger = logger;
		this.violation_filename = violation_filename;
	}


	public abstract List<Violation> read();
}
