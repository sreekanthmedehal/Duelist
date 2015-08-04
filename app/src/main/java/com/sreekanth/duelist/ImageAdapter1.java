package com.sreekanth.duelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 

 
public class ImageAdapter1 extends BaseAdapter {
	private Context context;
	private final String[] mobileValues;
 
	public ImageAdapter1(Context context, String[] mobileValues) {
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
 
			if (mobile.equals("Add Policy")) {
				imageView.setImageResource(R.drawable.ic_fa_plus);
			} else if (mobile.equals("info")) {
				imageView.setImageResource(R.drawable.ic_fa_info);
			} else if (mobile.equals("Policy Status")) {
				imageView.setImageResource(R.drawable.ic_fa_search);
			} else if (mobile.equals("Policy List"))   {
				imageView.setImageResource(R.drawable.ic_fa_list);
			}else if (mobile.equals("Revival Calculation"))   {
				imageView.setImageResource(R.drawable.ic_action_sum);
			}else if (mobile.equals("Settings"))   {
				imageView.setImageResource(R.drawable.ic_action_action_settings);
			}else if (mobile.equals("Submit Feedback"))   {
				imageView.setImageResource(R.drawable.ic_action_feedback_icon);
			}else if (mobile.equals("Surrender"))   {
				imageView.setImageResource(R.drawable.ic_action_cancel);
			}else if (mobile.equals("Utilities"))   {
				imageView.setImageResource(R.drawable.ic_action_utilities);
			}
			else if (mobile.equals("Ask Lic"))   {
				imageView.setImageResource(R.drawable.ic_fa_question);
			}else if (mobile.equals("Downloads"))   {
				imageView.setImageResource(R.drawable.ic_fa_download);
			}
			else if (mobile.equals("Buy Online"))   {
				imageView.setImageResource(R.drawable.ic_fa_credit_card);
			}
			else if (mobile.equals("Doctor Locator"))   {
				imageView.setImageResource(R.drawable.ic_action_doctor);
			}
			else if (mobile.equals("Updates"))   {
				imageView.setImageResource(R.drawable.ic_action_action_shop);
			}
			else if (mobile.equals("Add Person"))   {
				imageView.setImageResource(R.drawable.ic_action_add_person);
			}
			else if (mobile.equals("Agency Portal"))   {
				imageView.setImageResource(R.drawable.ic_action_image_filter_1);
			}
			else if (mobile.equals("CLIA Portal"))   {
				imageView.setImageResource(R.drawable.ic_action_image_filter_2);
			}
			else if (mobile.equals("Office Locator"))   {
				imageView.setImageResource(R.drawable.ic_fa_building);
			}
			else if (mobile.equals("NAV"))   {
				imageView.setImageResource(R.drawable.ic_action_coin_stacks_512);
			}
			else if (mobile.equals("Developer Site"))   {
				imageView.setImageResource(R.drawable.ic_action_action_language);
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