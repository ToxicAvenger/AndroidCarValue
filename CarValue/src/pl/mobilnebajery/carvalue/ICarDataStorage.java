package pl.mobilnebajery.carvalue;

import java.io.InputStream;

import pl.mobilnebajery.carvalue.CarDataStorage.IDatabaseDownloader;
import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarModelInfo;

public interface ICarDataStorage {

	public abstract void registerInputStream(InputStream dbStream)
			throws Exception;

	public abstract void getCarModelsDataAsync(final CarMakeInfo mark,
			final IDatabaseDownloader downloader);

	public abstract void getCarManufacturesDataAsync(
			final IDatabaseDownloader downloader);

	public abstract void downloadCarPricesAsync(
			final IDatabaseDownloader downloader);

	public abstract void setCarMark(CarMakeInfo carMarkInfo);

	public abstract String getCarMarkName();

	public abstract void setCarModel(CarModelInfo carModelInfo);

	public abstract String getCarModelName();

	public abstract void setYear(int year);

	public abstract String getYear();

}