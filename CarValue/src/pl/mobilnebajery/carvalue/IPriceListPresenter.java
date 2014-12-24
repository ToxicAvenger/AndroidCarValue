package pl.mobilnebajery.carvalue;

import pl.mobilnebajery.carvalue.common.infrastructure.IPresenter;

public interface IPriceListPresenter extends CarDataStorage.IDatabaseDownloader, IPresenter {

	void downloadCarPricesAsync();
	String getCarMarkName();
	String getCarModelName();
	String getYear();
}