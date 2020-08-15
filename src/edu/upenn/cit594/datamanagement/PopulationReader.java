package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.logging.Logger;

public class PopulationReader {
    public String txt_filename;
    public Logger logger;
    
    
    public PopulationReader(String txt_filename,Logger logger) {
		super();
		this.txt_filename = txt_filename;
		this.logger = logger;
	}


    





	public List read() {
		List<Population> pops = new ArrayList<>();
		File file = new File(txt_filename); 
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			logger.writeNameOfFile(txt_filename);
			String st; 
			while ((st = br.readLine()) != null) { 
				String[] pair = st.split(" ");
				Population p = new Population(pair[0],pair[1]);
				pops.add(p);
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return pops;
	}

	

}
