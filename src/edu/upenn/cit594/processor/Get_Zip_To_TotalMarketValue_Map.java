package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;

public class Get_Zip_To_TotalMarketValue_Map implements Get_Zip_To_TotalValue_Map {

	@Override
	public Map<String, Double> getMap(List<Property> properties) {
		Map<String, Double> zip_to_totalMarketValue = new HashMap<>();
		for(Property p : properties) {
			if(!p.zip_code.equals(" ")&& isValidNumerical(p.zip_code)&& p.zip_code.length() >= 5 && !p.market_value.equals("") ) {
				zip_to_totalMarketValue.put(p.zip_code.substring(0, 5), zip_to_totalMarketValue.getOrDefault(p.zip_code.substring(0, 5), (double) 0) + Double.parseDouble(p.market_value));
			}
			
		}
		return zip_to_totalMarketValue;
	}

	private boolean isValidNumerical(String s) {
		if(s == null || s.length() == 0) return false;
		for(Character c : s.toCharArray()) {
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

}
