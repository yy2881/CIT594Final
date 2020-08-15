package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.logging.Logger;

public class PropertyReader {
	public String property_filename;
	public Logger logger;
	
	
	public PropertyReader(String property_filename, Logger logger) {
		super();
		this.property_filename = property_filename;
		this.logger = logger;
	}


	public List<Property> readProperty() {
		List<Property> property_list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(property_filename))) {
			logger.writeNameOfFile(property_filename);
			int index_market_value = 0;
			int index_total_livable_area = 0;
			int index_zipcode = 0;
			String line = br.readLine();
		    if(line != null) {
		    	String[] values = line.split(",");
		    	for(int i = 0; i < values.length; i++) {
		    		if(values[i].equals("market_value")) {
		    			index_market_value = i;
		    		} else if(values[i].equals("total_livable_area")) {
		    			index_total_livable_area = i;
		    		} else if(values[i].equals("zip_code")) {
		    			index_zipcode = i;
		    		}
		    	}
		    }
		    
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        ArrayList<String> strList = parser(values);
		        Property p = new Property(strList.get(index_zipcode),strList.get(index_market_value),strList.get(index_total_livable_area));
		        property_list.add(p);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property_list;
	}
	
	//helper method to handle cases when commas are within quotations.
	private ArrayList<String> parser(String[] tokens) {
		ArrayList<String> strList = new ArrayList<>();
		for(int i = 0; i < tokens.length; i++) {
			if(!tokens[i].isEmpty() && tokens[i].charAt(0) == '"') {
				StringBuilder sb = new StringBuilder();
				for(int j = i+1; j < tokens.length; j++) {
					if(!tokens[j].isEmpty() && tokens[j].charAt(tokens[j].length() - 1) == '"') {
						for(int k = i; k <= j; k++) {
							sb.append(tokens[k]);
						}
					} 
				}
				if(sb != null) {
					strList.add(sb.toString());
				} else {
					strList.add(tokens[i]);
				}
			} else {
				strList.add(tokens[i]);
			}
		}
		return strList;
	}

}
