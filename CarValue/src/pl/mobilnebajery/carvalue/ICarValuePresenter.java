package pl.mobilnebajery.carvalue;

import java.io.InputStream;

import pl.mobilnebajery.carvalue.common.infrastructure.IPresenter;
import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarModelInfo;

public interface ICarValuePresenter extends CarDataStorage.IDatabaseDownloader, IPresenter {

	void initializeInputStream(InputStream dbStream) throws Exception;
	void setYear(int year);
	void getCarManufacturesDataAsync();
	void setCarMark(CarMakeInfo carMarkInfo);
	void getCarModelsDataAsync(CarMakeInfo mark);
	void setCarModel(CarModelInfo carModelInfo);
}