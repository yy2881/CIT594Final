package edu.upenn.cit594.logging;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import edu.upenn.cit594.processor.Processor;

public class Logger {

	private PrintWriter out;
	public static String[] runtime_args;
	
	private Logger() {
		super();
		try {
			out = new PrintWriter(new File(Logger.runtime_args[4]));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static Logger instance;
	
	public static Logger getInstance() {
		if(instance == null) {
			instance = new Logger();
		}
		return instance;
	}
		
	public void writeNameOfFile(String opened_file) {
		out.write(System.currentTimeMillis()+"  "+opened_file +"\n");
		out.flush();
	}
	
	public void writeSelectionOfUser(int selection) {
		out.write(System.currentTimeMillis()+"  "+selection+"\n" );
		out.flush();
	}
	
	public void writeZipcode(String zipcode) {
		out.write(System.currentTimeMillis()+"  "+zipcode +"\n");
		out.flush();
	}
	
	public void writeStartLog() {
		out.write(System.currentTimeMillis()+"  "+runtime_args[0]+" "+ runtime_args[1]+" "+runtime_args[2]+" "+runtime_args[3]+" "+runtime_args[4]+" "+"\n");
	}
	
}
