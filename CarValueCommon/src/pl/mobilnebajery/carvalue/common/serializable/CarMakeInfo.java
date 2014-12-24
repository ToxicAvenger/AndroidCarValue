package pl.mobilnebajery.carvalue.common.serializable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlSeeAlso;


//@XmlRootElement(name="carMakeInfo")
//@XmlSeeAlso(CarModelInfo.class)
	public class CarMakeInfo implements Serializable {
		
		//public String name_url;
		//public int id;
		public String displayedName;
		public ArrayList<CarModelInfo> models = new ArrayList<CarModelInfo>();
		
		public CarMakeInfo() {
			//default
		}
		
		public CarMakeInfo(String displayedName, List<String> models) {
			
			this.displayedName = displayedName;
			
			for(String model:models) {
				
				this.models.add(new CarModelInfo(model));
			}
		}
	}
