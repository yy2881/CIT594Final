package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;

public interface Get_Zip_To_TotalValue_Map {
	public Map<String,Double> getMap(List<Property> properties);
}
