package edu.upenn.cit594.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.datamanagement.ViolationReader;
import edu.upenn.cit594.logging.Logger;

public abstract class Processor<E> {
	protected ViolationReader vio_reader;
	List<Property> properties;
	List<Population> pops;
	
	
	
	public Processor(String property_filename, String population_filename,Logger logger,String violation_filename) {
		this.vio_reader = createViolationReader(logger,violation_filename);
		this.pops = new PopulationReader(population_filename,logger).read();
		this.properties = new PropertyReader(property_filename,logger).readProperty();
		
	}

	
	protected abstract ViolationReader createViolationReader(Logger logger,String violation_filename);
	
	//feature 1
	public int countPopulation() {
		int total = 0;
		for(Population p : pops) {
			if(!p.population.equals(" ")) {
				total += Integer.parseInt(p.population);
			}
		}
		return total;
	}
	
	//feature 2
	public Map<String,String> countFinesPerCapita() {
		List<Violation> violations = vio_reader.read();
		Map<String,Integer> zip_to_sumfine = new HashMap<>();
		System.out.println(violations.size());
		for(Violation v : violations) {
			if(v.zip_code != null && !v.zip_code.equals(" ") && !v.fine.equals(" ") && isValidNumerical(v.fine) && v.state.equals("PA")) {
				zip_to_sumfine.put(v.zip_code, zip_to_sumfine.getOrDefault(v.zip_code, 0) + Integer.parseInt(v.fine));
			}
			
		}
		Map<String,String> zip_to_fineAverage = new HashMap<>();
		for(Population p : pops) {
			if(zip_to_sumfine.get(p.zip_code) != null && isValidNumerical(p.population)) {
				if(p.population == "0") continue;
				double fine_average = zip_to_sumfine.get(p.zip_code)/Double.parseDouble(p.population);
				//System.out.println("sum fine is: "+zip_to_sumfine.get(p.zip_code)+"population is : "+Integer.parseInt(p.population));
				if(fine_average == 0) continue;
				DecimalFormat formater = new DecimalFormat();
			    formater.setMaximumFractionDigits(4);
			    formater.setGroupingSize(0);
			    formater.setRoundingMode(RoundingMode.FLOOR);
			    String trunacated_fine_average =formater.format(fine_average);
			    trunacated_fine_average = String.format("%.04f",Double.parseDouble(trunacated_fine_average));
			    System.out.println("average is :"+trunacated_fine_average);  
				zip_to_fineAverage.put(p.zip_code, trunacated_fine_average);
			}
		}
		return zip_to_fineAverage;
	}
	
	//helper method
	public boolean isValidNumerical(String s) {
		if(s == null || s.length() == 0) return false;
		for(Character c : s.toCharArray()) {
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
	private String trunacateToInteger(Double num) {
		DecimalFormat formater = new DecimalFormat();
	    formater.setMaximumFractionDigits(0);
	    formater.setGroupingSize(0);
	    formater.setRoundingMode(RoundingMode.FLOOR);
	    String trunacated_double_average =formater.format(num);
	    return trunacated_double_average;
	}
	//same "strategy" for feature 3 and 4
	public String countAverageByZipcode(String zipcode,Get_Zip_To_TotalValue_Map map) {
		Map<String,Integer> zip_to_num_of_homes = new HashMap<>();
		for(Property p : properties) {
			if(!p.zip_code.equals(" ") && isValidNumerical(p.zip_code) && p.zip_code.length() >= 5) {
				zip_to_num_of_homes.put(p.zip_code.substring(0,5),zip_to_num_of_homes.getOrDefault(p.zip_code.substring(0,5), 0) + 1);
			}
		}
		Map<String, Double> zip_to_totalValue = map.getMap(properties);
		//System.out.println(zip_to_totalValue.size());
		if(zip_to_num_of_homes.get(zipcode) == null || zip_to_totalValue.get(zipcode) == null) return "0";
		//System.out.println("total Value is:"+zip_to_totalValue.get(zipcode));
		//System.out.println("num of homes is:"+zip_to_num_of_homes.get(zipcode));
		double result = zip_to_totalValue.get(zipcode)/zip_to_num_of_homes.get(zipcode);
		return trunacateToInteger(result);
	}
	//feature 3
	
	public String countAverageMarketValue(String zipcode) {
		return countAverageByZipcode(zipcode,new Get_Zip_To_TotalMarketValue_Map());
	}
	//feature 4
	public String countAverageLivableArea(String zipcode) {
		return countAverageByZipcode(zipcode,new Get_Zip_To_TotalLivableArea_Map());
	}
	
	
	
	//feature5
	public String countTotalMarketValPerCapita(String zipcode) {
		Get_Zip_To_TotalMarketValue_Map get_map = new Get_Zip_To_TotalMarketValue_Map();
		Map<String,Double> zip_to_totalMarketVal = get_map.getMap(properties);
		if(zip_to_totalMarketVal.size() == 0) return "0";
		if(zip_to_totalMarketVal.get(zipcode) == null) return "0";
		Double total_market_value = zip_to_totalMarketVal.get(zipcode);
		int totalPopulation = 0;
		for(Population p : pops) {
			if(p.zip_code.equals(zipcode)) {
				totalPopulation += Integer.parseInt(p.population);
			}
		}
		if(total_market_value == 0 || totalPopulation == 0) return "0";
		return trunacateToInteger(total_market_value/totalPopulation);
	}
	
	
	

}
