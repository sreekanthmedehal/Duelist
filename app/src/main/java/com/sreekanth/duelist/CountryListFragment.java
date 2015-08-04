package com.sreekanth.duelist;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class CountryListFragment extends ListFragment{
	
	/** List of countries to be displayed in the ListFragment */
		
	ListFragmentItemClickListener ifaceItemClickListener;	
	
	/** An interface for defining the callback method */
	public interface ListFragmentItemClickListener {
		/** This method will be invoked when an item in the ListFragment is clicked */
		void onListFragmentItemClick(int position);
	}	
	
	/** A callback function, executed when this fragment is attached to an activity */	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try{
			/** This statement ensures that the hosting activity implements ListFragmentItemClickListener */
			ifaceItemClickListener = (ListFragmentItemClickListener) activity;			
		}catch(Exception e){
			Toast.makeText(activity.getBaseContext(), "Exception",Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ArrayList<HashMap<String,Object>> listdata=new ArrayList<HashMap<String,Object>>();
		    DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(inflater.getContext());
	        SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
	        
	        String xmonth = "01";
	        Cursor cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String jancount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));    
	        
	        xmonth = "02";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String febcount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        xmonth = "03";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String marcount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        
	        
	        
	        
	        xmonth = "04";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String aprcount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        xmonth = "05";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String maycount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        xmonth = "06";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String juncount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        xmonth = "07";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String julcount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        xmonth = "08";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String augcount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        xmonth = "09";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String sepcount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        xmonth = "10";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String octcount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        xmonth = "11";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String novcount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        xmonth = "12";
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null); 
	        cursorStock.moveToFirst();
	        String deccount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        
	        cursorStock = database.rawQuery("SELECT count(*) as nbcount FROM nbcr", null); 
	        cursorStock.moveToFirst();
	        String totcount =   cursorStock.getString(cursorStock.getColumnIndexOrThrow("nbcount"));
	        
	        database.close();
	        stockSQLHelper.close();
	        
	        HashMap<String, Object> hm = new HashMap<String, Object>();
	        hm.put("title", "January");
	        hm.put("context", jancount);
	        listdata.add(hm);
	         hm = new HashMap<String, Object>();
	        hm.put("title", "February");
	        hm.put("context", febcount);
	        listdata.add(hm);
	       hm = new HashMap<String, Object>();
	        hm.put("title", "March");
	        hm.put("context", marcount);
	        listdata.add(hm);
	         hm = new HashMap<String, Object>();
	        hm.put("title", "April");
	        hm.put("context", aprcount);
	        listdata.add(hm);
	         hm = new HashMap<String, Object>();
	        hm.put("title", "May");
	        hm.put("context", maycount);
	        listdata.add(hm);
	        hm = new HashMap<String, Object>();
	        hm.put("title", "June");
	        hm.put("context", juncount);
	        listdata.add(hm);
	        hm = new HashMap<String, Object>();
	        hm.put("title", "July");
	        hm.put("context", julcount);
	        listdata.add(hm);
	        hm = new HashMap<String, Object>();
	        hm.put("title", "August");
	        hm.put("context", augcount);
	        listdata.add(hm);
	        hm = new HashMap<String, Object>();
	        hm.put("title", "September");
	        hm.put("context", sepcount);
	        listdata.add(hm);
	         hm = new HashMap<String, Object>();
	        hm.put("title", "October");
	        hm.put("context", octcount);
	        listdata.add(hm);
	        hm = new HashMap<String, Object>();
	        hm.put("title", "November");
	        hm.put("context", novcount);
	        listdata.add(hm);
	        hm = new HashMap<String, Object>();
	        hm.put("title", "December");
	        hm.put("context", deccount);
	        listdata.add(hm);
	        hm = new HashMap<String, Object>();
	        hm.put("title", "Total");
	        hm.put("context", totcount);
	        listdata.add(hm);
	       
	   

	    String[] from = {"title", "context"};
	    int[] to={R.id.textView1,R.id.textView2};

	    SimpleAdapter adapter = new SimpleAdapter(inflater.getContext(), listdata, R.layout.lv_nb_item_month, from, to);
	  
		
		/** Data source for the ListFragment */
	//	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, Country.name);
	//	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), R.layout.lv_nb_item_month,R.id.textView1, Country.name);	
		/** Setting the data source to the ListFragment */
		setListAdapter(adapter);	
		
		
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {	
	
		/** Invokes the implementation of the method onListFragmentItemClick in the hosting activity */
		ifaceItemClickListener.onListFragmentItemClick(position);
		
	}
	
}
