package pl.mobilnebajery.carvalue.gui;

import java.util.List;

import pl.mobilnebajery.carvalue.common.infrastructure.IView;

public interface IPriceListView extends IView {

	void pricesDownloaded(List<String> prices, List<String> avaliableYears);
}