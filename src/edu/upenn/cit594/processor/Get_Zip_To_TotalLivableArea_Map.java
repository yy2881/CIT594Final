package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;

public class Get_Zip_To_TotalLivableArea_Map implements Get_Zip_To_TotalValue_Map {

	@Override
	public Map<String, Double> getMap(List<Property> properties) {
		Map<String, Double> zip_to_totalLivableArea = new HashMap<>();
		for(Property p : properties) {
			if(!p.zip_code.equals(" ")&& !p.total_livable_area.equals("") && p.zip_code.length() >=5 && isValidNumerical(p.zip_code)) {
				zip_to_totalLivableArea.put(p.zip_code.substring(0, 5), zip_to_totalLivableArea.getOrDefault(p.zip_code.substring(0, 5), (double) 0) + Double.parseDouble(p.total_livable_area));
			}
			
		}
		return zip_to_totalLivableArea;
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
