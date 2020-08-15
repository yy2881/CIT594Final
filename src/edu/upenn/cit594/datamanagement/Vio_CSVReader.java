package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.logging.Logger;

public class Vio_CSVReader extends ViolationReader {
	


	


	public Vio_CSVReader(Logger logger, String violation_filename) {
		super(logger, violation_filename);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List read() {
		List<Violation> violations = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(violation_filename))) {
			logger.writeNameOfFile(violation_filename);
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        Violation v;
		        if(values.length == 7) {
		        	v = new Violation(values[0],values[1],values[2],values[3],values[4],values[5],values[6]);
		        } else {
		        	v = new Violation(values[0],values[1],values[2],values[3],values[4],values[5],null);
		        }
		        
		        violations.add(v);
		    }
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return violations;
	}
	
	

	
}
