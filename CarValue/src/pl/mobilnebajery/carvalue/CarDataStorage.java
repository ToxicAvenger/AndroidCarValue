package pl.mobilnebajery.carvalue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.inject.Inject;

import pl.mobilnebajery.carvalue.common.CarMakesInfoBinarySerializer;
import pl.mobilnebajery.carvalue.common.ICarMakesInfoSerializer;
import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarModelInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarPrice;

public class CarDataStorage implements ICarDataStorage {

    public interface IDatabaseDownloader {
    	void pricesDownloaded(List<String> prices, List<String> avaliableYears);
    	void carMakeDataDownloaded(List<CarMakeInfo> carData);
    	void carModelDataDownloaded(List<CarModelInfo> carData);
    }
    
    private static Object classAccess = new Object();
    private static CarDataStorage dataStorage;
    public static CarDataStorage getInstance() {
    	
    	synchronized(classAccess) {
    		if(dataStorage == null) {
    			dataStorage = new CarDataStorage();
    		}
    		
    		return dataStorage;
    	}
    }
    
	private List<CarMakeInfo> makes;

	@Inject
	private ICarMakesInfoSerializer carMakesInfoSerializer;
	 
    private CarMakeInfo carMarkInfo;
    public String getCarMarkName() {
    	return this.carMarkInfo.displayedName;
    }    
    public void setCarMark(CarMakeInfo carMarkInfo) {
    	this.carMarkInfo = carMarkInfo;
    }

    
    private CarModelInfo carModelInfo;
    public String getCarModelName() {
    	return this.carModelInfo.displayedName;
    }
    public void setCarModel(CarModelInfo carModelInfo) {
    	this.carModelInfo = carModelInfo;
    }

    
    private String year;
    public String getYear() {
    	return this.year;
    }
    public void setYear(int year) {
    	this.year = Integer.toString(year);
    }
    
    public CarDataStorage() {
    	
    	carMakesInfoSerializer = new CarMakesInfoBinarySerializer();
    }   
    
	public void registerInputStream(InputStream dbStream) throws Exception {

		makes = carMakesInfoSerializer.deserialize(dbStream);
	}	

	public void getCarModelsDataAsync(final CarMakeInfo mark, final IDatabaseDownloader downloader) {
		  new Thread(new Runnable() {
			  
			    public void run() {
			    	
			    	int index = makes.indexOf(mark);
			    	
			    	if(index > -1) {
			    		
			    		List<CarModelInfo> models = makes.get(index).models;
			    		
			    		Collections.sort(models, new CustomModelsComparator());
			    		
						downloader.carModelDataDownloaded(models);
			    	}
			    }
		  }).start();
	}

	public void getCarManufacturesDataAsync(final IDatabaseDownloader downloader) {
		  new Thread(new Runnable() {
			  
			    public void run() {
				
			    	List<CarMakeInfo> tmp = new ArrayList<CarMakeInfo>(makes);
		    		
		    		Collections.sort(tmp, new CustomMakesComparator());
		    		
					downloader.carMakeDataDownloaded(tmp);
			    }
		  }).start();
	}
    
    public void downloadCarPricesAsync(final IDatabaseDownloader downloader) {
	  new Thread(new Runnable() {
		  
		    public void run() {
				ArrayList<String> valuesDiesel = new ArrayList<String>();
				ArrayList<String> valuesPetrol = new ArrayList<String>();
				ArrayList<String> avaliableYears = new ArrayList<String>();
				
				for(CarPrice price:carModelInfo.prices) {
					
					String tmp = String.valueOf(price.productionYear);
					
					if(!avaliableYears.contains(tmp)) {			
						
						avaliableYears.add(tmp);
					}
					
					if(tmp.equals(year)) {
						
						if(price.isDiesel) {
							
							valuesDiesel.add(String.valueOf(price.minPrice));
							valuesDiesel.add(String.valueOf(price.avgPrice));
							valuesDiesel.add(String.valueOf(price.maxPrice));
						}
						else {
							
							valuesPetrol.add(String.valueOf(price.minPrice));
							valuesPetrol.add(String.valueOf(price.avgPrice));
							valuesPetrol.add(String.valueOf(price.maxPrice));
						}
					}
				}
				
				if(valuesPetrol.size() == 3) {
					
					if(valuesDiesel.size() != 3) {
						
						valuesDiesel.clear();
						
						valuesDiesel.add("-");
						valuesDiesel.add("-");
						valuesDiesel.add("-");
					}
				} else if (valuesDiesel.size() == 3) {
					
					valuesPetrol.clear();
					
					valuesPetrol.add("-");
					valuesPetrol.add("-");
					valuesPetrol.add("-");
				}
				
				ArrayList<String> values = new ArrayList<String>(valuesPetrol);
				
				values.addAll(valuesDiesel);
		    	
		    	if(downloader != null){
		    		downloader.pricesDownloaded(values, avaliableYears);
		    	}
		    }
	  }).start();
    }

	private class CustomModelsComparator implements Comparator<CarModelInfo> {

	    public int compare(CarModelInfo o1, CarModelInfo o2) {
	        return o1.displayedName.compareTo(o2.displayedName);
	    }
	}
	
	private class CustomMakesComparator implements Comparator<CarMakeInfo> {

	    public int compare(CarMakeInfo o1, CarMakeInfo o2) {
	        return o1.displayedName.compareTo(o2.displayedName);
	    }
	}
}
