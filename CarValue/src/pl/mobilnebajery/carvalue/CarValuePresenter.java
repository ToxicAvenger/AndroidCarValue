package pl.mobilnebajery.carvalue;

import java.io.InputStream;
import java.util.List;

import com.google.inject.Inject;

import pl.mobilnebajery.carvalue.common.infrastructure.IView;
import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarModelInfo;
import pl.mobilnebajery.carvalue.gui.ICarValueView;

public class CarValuePresenter implements ICarValuePresenter {
	
	@Inject
	private ICarDataStorage carValueModel;	
	
	private ICarValueView carValueView;
	
	public void registerView(IView view) {
		
		carValueView = (ICarValueView)view;
	}

	public void initializeInputStream(InputStream dbStream) throws Exception {
		carValueModel.registerInputStream(dbStream);
	}

	public void setYear(int year) {
		carValueModel.setYear(year);
	}
	
	public void setCarMark(CarMakeInfo carMarkInfo) {
		carValueModel.setCarMark(carMarkInfo);
	}
	
	public void setCarModel(CarModelInfo carModelInfo) {
		carValueModel.setCarModel(carModelInfo);
	}

	public void getCarManufacturesDataAsync() {
		carValueModel.getCarManufacturesDataAsync(this);
	}

	public void getCarModelsDataAsync(CarMakeInfo carMarkInfo) {
		carValueModel.getCarModelsDataAsync(carMarkInfo, this);
	}

	public void pricesDownloaded(List<String> prices,
			List<String> avaliableYears) {
		// TODO Auto-generated method stub		
	}

	public void carMakeDataDownloaded(List<CarMakeInfo> carData) {
		carValueView.carMakeDataDownloaded(carData);
	}

	public void carModelDataDownloaded(List<CarModelInfo> carData) {
		carValueView.carModelDataDownloaded(carData);		
	}
}
