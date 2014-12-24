package pl.mobilnebajery.carvalue.ioc;

import pl.mobilnebajery.carvalue.CarDataStorage;
import pl.mobilnebajery.carvalue.CarValuePresenter;
import pl.mobilnebajery.carvalue.ICarDataStorage;
import pl.mobilnebajery.carvalue.ICarValuePresenter;
import pl.mobilnebajery.carvalue.IPriceListPresenter;
import pl.mobilnebajery.carvalue.PriceListPresenter;
import pl.mobilnebajery.carvalue.common.CarMakesInfoBinarySerializer;
import pl.mobilnebajery.carvalue.common.ICarMakesInfoSerializer;

import com.google.inject.AbstractModule;

public class CarValueModule extends AbstractModule {

	@Override
	protected void configure() {
	
		bind(ICarDataStorage.class).to(CarDataStorage.class).asEagerSingleton();
		bind(ICarValuePresenter.class).to(CarValuePresenter.class).asEagerSingleton();
		bind(IPriceListPresenter.class).to(PriceListPresenter.class).asEagerSingleton();
		
		bind(ICarMakesInfoSerializer.class).to(CarMakesInfoBinarySerializer.class).asEagerSingleton();
	}

}
