package pl.mobilnebajery.carvalue.gui;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import com.google.inject.Inject;

import pl.mobilnebajery.carvalue.ICarValuePresenter;
import pl.mobilnebajery.carvalue.R;
import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarModelInfo;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

@ContentView(R.layout.main)
public class CarValueActivity extends RoboActivity implements ICarValueView {
	
	@Inject
	private ICarValuePresenter presenter;
	
	private CarModelInfoAdapter modelAdapter;
	private CarMakeInfoAdapter markAdapter;
	
	@InjectView(R.id.datePickerMain)
	private DatePicker datePicker;
	
	@InjectView(R.id.spinnerMark)
	private Spinner spinnerMark;
	
	@InjectView(R.id.spinnerModel)
	private Spinner spinnerModel;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        presenter.registerView(this);
        
        InputStream dbStream = this.getResources().openRawResource(R.raw.cardb);
        
        try {
        	presenter.initializeInputStream(dbStream);
		} catch (Exception e1) {

			Toast t = Toast.makeText(this, "Błąd bazy", Toast.LENGTH_LONG);
        	t.show();
        	
        	this.finish();
		}
       
        configureDatePicker(datePicker);
        
        presenter.getCarManufacturesDataAsync();        
             
        spinnerMark.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				CarMakeInfo _carMarkInfo = markAdapter.getItem(arg2);
				
				presenter.setCarMark(_carMarkInfo);                
				presenter.getCarModelsDataAsync(_carMarkInfo);               
			}
			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub				
			}
        });
        
        spinnerModel.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				CarModelInfo _carModelInfo = modelAdapter.getItem(arg2);
				presenter.setCarModel(_carModelInfo); 
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub				
			}
        });
        
        final ScrollView scrollViewerMain = (ScrollView) findViewById(R.id.scrollViewerMain);
        scrollViewerMain.post(new Runnable() { 
            public void run() { 
            	scrollViewerMain.fullScroll(ScrollView.FOCUS_UP); 
            } 
        });         
    }
    
    public void onCalculateClick(View view) {
    	presenter.setYear(datePicker.getYear());
        Intent myIntent = new Intent(view.getContext(), PriceListActivity.class);
        startActivityForResult(myIntent, 0);
    }
    
    public void onCloseClick(View view) {
    	CarValueActivity.this.finish();
    }
    
	public void carMakeDataDownloaded(final List<CarMakeInfo> carData) {
		
		CarValueActivity.this.runOnUiThread( new Runnable() {
			
			 public void run() {
				 	markAdapter = new CarMakeInfoAdapter(CarValueActivity.this, R.layout.carvalue_layout, carData);
			        spinnerMark.setAdapter(markAdapter);   
		        }
			 }
		 );		
	}
	
	public void carModelDataDownloaded(
			final List<CarModelInfo> carData) {

		CarValueActivity.this.runOnUiThread( new Runnable() {
			
			 public void run() {
	                modelAdapter = new CarModelInfoAdapter(CarValueActivity.this, R.layout.carvalue_layout, carData);
	                spinnerModel.setAdapter(modelAdapter);  
		        }
			 }
		 );
	}
    
    private void configureDatePicker(DatePicker picker) {
    	
        try {
            Field f[] = picker.getClass().getDeclaredFields();
            for (Field field : f) {
                if (field.getName().equals("mDayPicker") || field.getName().equals("mMonthPicker") || field.getName().equals("mDaySpinner") || field.getName().equals("mMonthSpinner")) {
                    field.setAccessible(true);
                    Object dayPicker = new Object();
                    dayPicker = field.get(picker);
                    ((View) dayPicker).setVisibility(View.GONE);
                }
            }
        } catch (SecurityException e) {
            Log.d("ERROR", e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            Log.d("ERROR", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.d("ERROR", e.getMessage());
        }  
    }
}