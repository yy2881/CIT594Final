package edu.upenn.cit594;

import java.io.File;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.CSVProcessor;
import edu.upenn.cit594.processor.JSONProcessor;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.ui.CommandLineUserInterface;

public class Main {

	public static void main(String[] args) {
		System.out.println(args.length);
		//handle error 
		if(args.length != 5) {
			System.out.println("Error: The number of arguments is incorrect");
			return;
		}
		if(!args[0].equals("json") && !args[0].equals("csv") ) {
			System.out.println("Error: The format of input is incorrect");
			return;
		}
			
		File park_violations = new File(args[1]);
		if(!park_violations.canRead()) {
			System.out.println("Error: The file"+args[1] + " cannot be read because of file permission.");
			return;
		}
		
		
		
		
		System.out.println(args.length);
		System.out.println(args[1]);
		
		
		
		
		
		//Create objects and arrange dependencies
		
		
		//create logger

		Logger.runtime_args = args;
		Logger logger = Logger.getInstance();
				
				
		//create processor
		Processor processor;
		
		if(args[0].equals("json")) {
			processor = new JSONProcessor(args[2],args[3],logger,args[1]);
		} else {
			processor = new CSVProcessor(args[2],args[3],logger,args[1]);
		}
		

		

		
		//create ui
		CommandLineUserInterface ui = new CommandLineUserInterface(processor,logger);
		ui.start();

	}

}
