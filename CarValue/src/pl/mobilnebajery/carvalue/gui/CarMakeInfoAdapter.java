package pl.mobilnebajery.carvalue.gui;

import java.util.List;

import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CarMakeInfoAdapter extends ArrayAdapter<CarMakeInfo>{

	private List<CarMakeInfo> items;
	private Context context;
	
	public CarMakeInfoAdapter(Context context, int textViewResourceId,
			List<CarMakeInfo> carData) {
		super(context, textViewResourceId, carData);
		
		this.items = carData;
		this.context = context;
	}
	
	public int getCount(){
       return items.size();
    }

    public CarMakeInfo getItem(int position){
       return items.get(position);
    }

    public long getItemId(int position){
       return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(getItem(position).displayedName);

        return label;
    }
    
    @Override
    public View getDropDownView(int position, View convertView,
            ViewGroup parent) {
    	
    	int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, context.getResources().getDisplayMetrics());
    	
        TextView label = new TextView(context);
        label.setPadding(padding, padding, padding, padding);
        label.setTextColor(Color.BLACK);
        label.setText(getItem(position).displayedName);

        return label;
    }

}
