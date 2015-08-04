package com.sreekanth.duelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 

 
public class ImageAdapter2 extends BaseAdapter {
	private Context context;
	private final String[] mobileValues;
 
	public ImageAdapter2(Context context, String[] mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.mobile, null);
 
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(mobileValues[position]);
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
 
			String mobile = mobileValues[position];
 
			if (mobile.equals("Share")) {
				imageView.setImageResource(R.drawable.ic_action_social_share);
			} else if (mobile.equals("Edit")) {
				imageView.setImageResource(R.drawable.ic_fa_edit);
			} 
			else if (mobile.equals("Delete")) {
				imageView.setImageResource(R.drawable.ic_action_discard);
			} 
			else if (mobile.equals("Detailed View")) {
				imageView.setImageResource(R.drawable.ic_action_view_as_list);
			} 
			else if (mobile.equals("Revival Calc"))   {
				imageView.setImageResource(R.drawable.ic_action_sum);
			
			}else if (mobile.equals("Surrender Calc"))   {
				imageView.setImageResource(R.drawable.ic_action_cancel);
		    }
			} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return mobileValues.length;
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
}