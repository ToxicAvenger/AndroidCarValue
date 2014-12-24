package pl.mobilnebajery.carvalue.common.serializable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlSeeAlso;


//@XmlRootElement(name="carMakesInfoList")
//@XmlSeeAlso(CarMakeInfo.class)
public class CarMakesInfoList implements Serializable {
	
	//@XmlElement(name="carMakeInfo")
    public List<CarMakeInfo> list;

    public CarMakesInfoList(){}

    public CarMakesInfoList(List<CarMakeInfo> list){
        this.list=list;
    }
}