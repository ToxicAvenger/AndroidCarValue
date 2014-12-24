package pl.mobilnebajery.carvalue.common.serializable;


import java.io.Serializable;

//import javax.xml.bind.annotation.XmlRootElement;


//@XmlRootElement(name="carPrice")
public class CarPrice implements Serializable {

	public boolean isDiesel;
	public int productionYear = -1;
	public int minPrice = -1;
	public int avgPrice = -1;
	public int maxPrice = -1;
	
	public CarPrice() {
		
		this.isDiesel = false;
		this.productionYear = -1;
		this.minPrice = -1;
		this.avgPrice = -1;
		this.maxPrice = -1;
	}
	
	public CarPrice(boolean isDiesel, int productionYear, int minPrice, int avgPrice, int maxPrice) {
		
		this.isDiesel = isDiesel;
		this.productionYear = productionYear;
		this.minPrice = minPrice;
		this.avgPrice = avgPrice;
		this.maxPrice = maxPrice;
	}
	
	public CarPrice (boolean isDiesel, String productionYear, String minPrice, String avgPrice, String maxPrice) throws NumberFormatException {
		
		try{
			
			this.isDiesel = isDiesel;
			this.productionYear = Integer.parseInt(productionYear);
			this.minPrice = Integer.parseInt(minPrice);
			this.avgPrice = Integer.parseInt(avgPrice);
			this.maxPrice = Integer.parseInt(maxPrice);	
		}
		catch(Exception ex) {
			
			throw new NumberFormatException();
		}
	}
}
