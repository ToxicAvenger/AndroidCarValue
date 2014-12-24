package pl.mobilnebajery.carvalue.common;


import java.util.ArrayList;
import java.util.List;

import pl.mobilnebajery.carvalue.common.serializable.CarPrice;


public class CarPriceUtils {

	public static List<CarPrice> getCarPrices(String year, List<String> values, List<String> errors) {
		
		if(errors == null) {
			
			errors = new ArrayList<String>();
		}
		
		List<CarPrice> prices = new ArrayList<CarPrice>();
		
		if(values.size() == 6) {
			
			try {
				
				CarPrice petrol = new CarPrice(false, year, values.get(0), values.get(1), values.get(2));
				CarPrice diesel = new CarPrice(true, year, values.get(3), values.get(4), values.get(5));
				
				prices.add(petrol);
				prices.add(diesel);
			}
			catch(Exception ex) {
				
				errors.add(year);
			}
		}
		else {
			
			errors.add(year);
		}
		
		return prices;
	}
}
