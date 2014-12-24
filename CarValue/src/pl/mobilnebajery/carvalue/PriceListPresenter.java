package pl.mobilnebajery.carvalue;

import java.util.List;

import com.google.inject.Inject;

import pl.mobilnebajery.carvalue.common.infrastructure.IView;
import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarModelInfo;
import pl.mobilnebajery.carvalue.gui.IPriceListView;

public class PriceListPresenter implements IPriceListPresenter {

	@Inject
	private ICarDataStorage priceListModel;
	
	private IPriceListView priceListView;
	
	public void registerView(IView view) {
		priceListView = (IPriceListView)view;
	}

	public void pricesDownloaded(List<String> prices,
			List<String> avaliableYears) {
		priceListView.pricesDownloaded(prices, avaliableYears);
	}

	public void carMakeDataDownloaded(List<CarMakeInfo> carData) {
		// TODO Auto-generated method stub		
	}

	public void carModelDataDownloaded(List<CarModelInfo> carData) {
		// TODO Auto-generated method stub		
	}

	public void downloadCarPricesAsync() {
		priceListModel.downloadCarPricesAsync(this);	
	}

	public String getCarMarkName() {
		return priceListModel.getCarMarkName();
	}

	public String getCarModelName() {
		return priceListModel.getCarModelName();
	}

	public String getYear() {
		return priceListModel.getYear();
	}
}
