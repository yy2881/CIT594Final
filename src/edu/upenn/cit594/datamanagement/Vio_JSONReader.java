package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.logging.Logger;

public class Vio_JSONReader extends ViolationReader {
	


	public Vio_JSONReader(Logger logger, String violation_filename) {
		super(logger, violation_filename);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List read() {
		List<Violation> violations = new ArrayList<>();
		// create a parser
			JSONParser parser = new JSONParser();
			// open the file and get the array of JSON objects
			JSONArray vio_arr;
			try {
				vio_arr = (JSONArray)parser.parse(new FileReader(violation_filename));
				logger.writeNameOfFile(violation_filename);
				// use an iterator to iterate over each element of the array
				Iterator iter = vio_arr.iterator();
				// iterate while there are more objects in array
				while (iter.hasNext()) {
				// get the next JSON object
					JSONObject vio = (JSONObject) iter.next();

				    String ticket_id = (String) vio.get("ticket_number");
				    String plate_id = (String) vio.get("plate_id");
				    String date = (String) vio.get("date");
				    String zipcode = (String) vio.get("zip_code");
				    String violation = (String) vio.get("violation");
				    String fine = (String) vio.get("fine");
				    String state = (String) vio.get("state");
				    Violation cur = new Violation(date,fine,violation,plate_id,state,ticket_id,zipcode);
				    violations.add(cur);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return violations;
	}

	

	

}
