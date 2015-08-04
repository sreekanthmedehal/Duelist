package com.sreekanth.duelist;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class CountryDetailsFragment extends Fragment {
	
String nbmonth;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		/** Inflating the layout country_details_fragment_layout to the view object v */
		View v = inflater.inflate(R.layout.country_details_fragment_layout, null);
		
		/** Getting the textview object of the layout to set the details */ 
		TextView tv = (TextView) v.findViewById(R.id.country_details);		
		
		/** Getting the bundle object passed from MainActivity ( in Landscape mode )  or from 
		 *  CountryDetailsActivity ( in Portrait Mode )  
		 * */
		Bundle b = getArguments();
		
		/** Getting the clicked item's position and setting corresponding details in the textview of the detailed fragment */
		tv.setText("Details of " + Country.name[b.getInt("position")]);		
		if (Country.name[b.getInt("position")].equals("April")){
			 nbmonth = "04";
		}
		else if (Country.name[b.getInt("position")].equals("May")){
			 nbmonth = "05";
		}
		else if (Country.name[b.getInt("position")].equals("June")){
			 nbmonth = "06";
		}
		else if (Country.name[b.getInt("position")].equals("July")){
			 nbmonth = "07";
		}
		else if (Country.name[b.getInt("position")].equals("August")){
			 nbmonth = "08";
		}
		else if (Country.name[b.getInt("position")].equals("January")){
			 nbmonth = "01";
		}
		else if (Country.name[b.getInt("position")].equals("February")){
			 nbmonth = "02";
		}
		else if (Country.name[b.getInt("position")].equals("September")){
			 nbmonth = "09";
		}
		else if (Country.name[b.getInt("position")].equals("October")){
			 nbmonth = "10";
		}
		else if (Country.name[b.getInt("position")].equals("November")){
			 nbmonth = "11";
		}
		else if (Country.name[b.getInt("position")].equals("December")){
			 nbmonth = "12";
		}
		else if (Country.name[b.getInt("position")].equals("March")){
			 nbmonth = "03";
		}
		else if (Country.name[b.getInt("position")].equals("Total")){
			 nbmonth = "99";
		}
		
		
		
		
		
		
		
		
		 ListView listViewPhoneBook;
		
		    
		 
		  listViewPhoneBook=(ListView)v.findViewById(R.id.listnb);
		  String[] arrayColumns = new String[]{"nbpolno","nbname"};
		  int[] arrayViewID = new int[]{R.id.textViewnbpol,R.id.textViewnbpolname};
		  Cursor cursor;
		     cursor = getnbdata(nbmonth);
		     SimpleCursorAdapter adapter = new SimpleCursorAdapter( this.getActivity() , R.layout.nb_item, cursor, arrayColumns, arrayViewID,0);
		     listViewPhoneBook.setAdapter(adapter);
		
		return v;
	
	}

	 private Cursor getnbdata(String xmonth) {
	        DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this.getActivity());
	        SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
	        Cursor cursorStock =null;
	        if (xmonth.equals("99")){
	        	cursorStock = database.rawQuery("SELECT * FROM nbcr", null);	
	        }else {
	         cursorStock = database.rawQuery("SELECT * FROM nbcr where strftime('%m', `nbcommdt`) = '" + xmonth + "'", null);
	        }
	    cursorStock.moveToFirst();
	      return cursorStock;
	        
	    }
	 public void close() {
		 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper( this.getActivity() );
	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
    database.close();
} 
	











}
	 
