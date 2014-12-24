package pl.mobilnebajery.carvalue.common.serializable;


import java.io.Serializable;
import java.util.ArrayList;


//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlSeeAlso;

//@XmlRootElement(name="carModelInfo")
//@XmlSeeAlso(CarPrice.class)
	public class CarModelInfo implements Serializable {
		
		//public String name_url;
		//public int id;
		public String displayedName;
		public ArrayList<CarPrice> prices = new ArrayList<CarPrice>();
		
		public CarModelInfo() {
			//default
		}
		
		public CarModelInfo(String displayedName) {
			
			this.displayedName = displayedName;
		}
	}
