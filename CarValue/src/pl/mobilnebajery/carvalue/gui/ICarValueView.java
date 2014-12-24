package pl.mobilnebajery.carvalue.gui;

import java.util.List;

import pl.mobilnebajery.carvalue.common.infrastructure.IView;
import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarModelInfo;

public interface ICarValueView extends IView {
	void carMakeDataDownloaded(List<CarMakeInfo> carData);
	void carModelDataDownloaded(List<CarModelInfo> carData);
}