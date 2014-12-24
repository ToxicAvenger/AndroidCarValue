package pl.mobilnebajery.carvalue.common;

import java.io.InputStream;
import java.util.List;

import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;

public interface ICarMakesInfoSerializer {

	void serialize(List<CarMakeInfo> carInfos, String fileName)
			throws Exception;

	List<CarMakeInfo> deserialize(InputStream inputStream)
			throws Exception;

}