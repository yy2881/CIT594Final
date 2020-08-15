package edu.upenn.cit594.ui;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;

public class CommandLineUserInterface {
	protected Processor processor;
    protected Logger logger;
	public CommandLineUserInterface(Processor processor,Logger logger) {
		super();
		this.processor = processor;
		this.logger = logger;
	}

	public void start() {
		logger.writeStartLog();
		Scanner input = new Scanner(System.in);
		while(true) {
			printInstruction();
			try {
				int choice = input.nextInt();
				logger.writeSelectionOfUser(choice);
				if(choice == 0) {
					System.out.println("Now exit the program.");
					return;
				}
				if(choice == 1) {
					showTotalPopulation();
				}
				if(choice == 2) {
					showFinesPerCapita();
				}
				if(choice == 3) {
					Scanner zip = new Scanner(System.in);
					System.out.println("Enter a ZIP Code");
					String zipcode = zip.nextLine();
					logger.writeZipcode(zipcode);
					showAverageMarketValue(zipcode);
				}
				if(choice == 4) {
					Scanner zip = new Scanner(System.in);
					System.out.println("Enter a ZIP Code");
					String zipcode = zip.nextLine();
					logger.writeZipcode(zipcode);
					showAverageLivableArea(zipcode);
				}
				if(choice == 5) {
					Scanner zip = new Scanner(System.in);
					System.out.println("Enter a ZIP Code");
					String zipcode = zip.nextLine();
					logger.writeZipcode(zipcode);
					showTotalMarketValPerCapita(zipcode);
				}
				if(choice == 6) {
					//*******TODO
				}
			} catch (InputMismatchException e){
				System.out.println("The number you entered is invalid.");
				System.exit(0);
			}
			
		}
		
	}
	
	public void printInstruction() {
		System.out.println("Please enter a number:");
		System.out.println("Enter 0 : exit.");
		System.out.println("Enter 1 : show total population for all ZIP Codes.");
		System.out.println("Enter 2 : show total parking fines per capita for each ZIP Code.");
		System.out.println("Enter 3 : show average market value for residences in a specified ZIP Code.");
		System.out.println("Enter 4 : show average total livable area for residences in a specified ZIP Code.");
		System.out.println("Enter 5 : show total residential market value per capita for a specified ZIP Code.");
		System.out.println("Enter 6 : show custom feature.");
	}
	
	//feature 1
	public void showTotalPopulation() {
		int total_pop = processor.countPopulation();
		System.out.println(total_pop);
	}
	//feature 2
	public void showFinesPerCapita() {
		Map<String,String> zip_to_fineAverage = processor.countFinesPerCapita();
		for(Map.Entry<String,String> entry : zip_to_fineAverage.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
	}
	//feature 3
	public void showAverageMarketValue(String zipcode) {
		String ave_market_value = processor.countAverageMarketValue(zipcode);
		System.out.println(ave_market_value);
	}
	
	
	//feature 4
	public void showAverageLivableArea(String zipcode) {
		String ave_livable_area = processor.countAverageLivableArea(zipcode);
		System.out.println(ave_livable_area);
	}
	//feature 5
	public void showTotalMarketValPerCapita(String zipcode) {
		String value = processor.countTotalMarketValPerCapita(zipcode);
		System.out.println(value);
	}
}
