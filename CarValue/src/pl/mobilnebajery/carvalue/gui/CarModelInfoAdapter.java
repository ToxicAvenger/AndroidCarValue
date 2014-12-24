package pl.mobilnebajery.carvalue.gui;

import java.util.List;

import pl.mobilnebajery.carvalue.common.serializable.CarModelInfo;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CarModelInfoAdapter extends ArrayAdapter<CarModelInfo>{

	private List<CarModelInfo> items;
	private Context context;
	
	public CarModelInfoAdapter(Context context, int textViewResourceId,
			List<CarModelInfo> carData) {
		super(context, textViewResourceId, carData);
		
		this.items = carData;
		this.context = context;
	}
	
	public int getCount(){
       return items.size();
    }

    public CarModelInfo getItem(int position){
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
