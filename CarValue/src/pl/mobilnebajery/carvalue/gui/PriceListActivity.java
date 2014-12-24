package pl.mobilnebajery.carvalue.gui;

import java.util.List;

import com.google.inject.Inject;

import pl.mobilnebajery.carvalue.IPriceListPresenter;
import pl.mobilnebajery.carvalue.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@ContentView(R.layout.pricelist)
public class PriceListActivity extends RoboActivity implements IPriceListView {
	
	@Inject
	private IPriceListPresenter presenter;
	
	@InjectView(R.id.textViewPetrolMin)
	private TextView textViewPetrolMin;
	
	@InjectView(R.id.textViewPetrolAvg)
	private TextView textViewPetrolAvg;
	
	@InjectView(R.id.textViewPetrolMax)
	private TextView textViewPetrolMax;
	
	@InjectView(R.id.textViewDieselMin)
	private TextView textViewDieselMin;
	
	@InjectView(R.id.textViewDieselAvg)
	private TextView textViewDieselAvg;
	
	@InjectView(R.id.textViewDieselMax)
	private TextView textViewDieselMax;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        presenter.registerView(this);
        
        Button next = (Button) findViewById(R.id.buttonBack);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        
        TextView textViewMark = (TextView) findViewById(R.id.textViewMark);
        TextView textViewModel = (TextView) findViewById(R.id.textViewModel);
        TextView textViewYear = (TextView) findViewById(R.id.textViewYear);
        
        textViewMark.setText(presenter.getCarMarkName());
        textViewModel.setText(presenter.getCarModelName());
        textViewYear.setText(presenter.getYear());
        
    	textViewPetrolMin.setText("-");
    	textViewPetrolAvg.setText("-");
    	textViewPetrolMax.setText("-");
    	
    	textViewDieselMin.setText("-");
    	textViewDieselAvg.setText("-");
    	textViewDieselMax.setText("-");
    	
        presenter.downloadCarPricesAsync();
    }
    
	public void pricesDownloaded(final List<String> prices, final List<String> avaliableYears) {
		
		PriceListActivity.this.runOnUiThread( new Runnable() {
			
			 public void run() {
				 
				if(prices.size() == 6){
		        	textViewPetrolMin.setText(prices.get(0));
		        	textViewPetrolAvg.setText(prices.get(1));
		        	textViewPetrolMax.setText(prices.get(2));
		        	
		        	textViewDieselMin.setText(prices.get(3));
		        	textViewDieselAvg.setText(prices.get(4));
		        	textViewDieselMax.setText(prices.get(5));
		        }
				else {
					
					String message = "Brak danych dla wybranego ";
					
					if(avaliableYears.size() > 0) {
					
						message += "rocznika. Dostępne roczniki: ";
						message += avaliableYears.get(0);
						
						for(int i = 1; i < avaliableYears.size(); i++) {
							
							message += ", " + avaliableYears.get(i);
						}
						
						message += ".";
					}
					else {
						
						message += "modelu.";
					}
					
					AlertDialog.Builder builder = new AlertDialog.Builder(PriceListActivity.this);
					
					builder.setMessage(message)
					       .setCancelable(false)
					       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					        	   PriceListActivity.this.finish();
					           }
					       });
					
					AlertDialog alert = builder.create();
					
					alert.show();
				}
			 }
		 } );
	}
}
