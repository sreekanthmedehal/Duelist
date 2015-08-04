package com.sreekanth.duelist;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.sreekanth.duelist.CountryListFragment.ListFragmentItemClickListener;

public class MainActivitynb extends Activity implements ListFragmentItemClickListener {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        Bundle extras = getIntent().getExtras(); 


        if (extras != null) {
       
      
           
        }
        
    }
    
    
    /** This method will be executed when the user clicks on an item in the listview */
    @Override
    public void onListFragmentItemClick(int position) {
    	
    	/** Getting the orientation ( Landscape or Portrait ) of the screen */
   // 	int orientation = getResources().getConfiguration().orientation;
    	
    	
    	/** Landscape Mode */
   // 	if(orientation == Configuration.ORIENTATION_LANDSCAPE ){
    		/** Getting the fragment manager for fragment related operations */
    		FragmentManager fragmentManager = getFragmentManager();
    		
    		/** Getting the fragmenttransaction object, which can be used to add, remove or replace a fragment */
    		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    		
    		/** Getting the existing detailed fragment object, if it already exists. 
    		 *  The fragment object is retrieved by its tag name 
    		 * */
    		Fragment prevFrag = fragmentManager.findFragmentByTag("in.wptrafficanalyzer.country.details");
    		
    		/** Remove the existing detailed fragment object if it exists */
    		if(prevFrag!=null)
    			fragmentTransaction.remove(prevFrag);    		
    		
    		/** Instantiating the fragment CountryDetailsFragment */
    		CountryDetailsFragment fragment = new CountryDetailsFragment();
    		
    		/** Creating a bundle object to pass the data(the clicked item's position) from the activity to the fragment */ 
    		Bundle b = new Bundle();
    		
    		/** Setting the data to the bundle object */
    		b.putInt("position", position);
    		
    		/** Setting the bundle object to the fragment */
    		fragment.setArguments(b);    		
    		
    		/** Adding the fragment to the fragment transaction */
    		fragmentTransaction.add(R.id.detail_fragment_container, fragment,"in.wptrafficanalyzer.country.details");
    		
    		/** Adding this transaction to backstack */
    		fragmentTransaction.addToBackStack(null);
    		
    		/** Making this transaction in effect */
    		fragmentTransaction.commit();
    		
    	}
    	
    	
    //	else{    		/** Portrait Mode or Square mode */
    		/** Creating an intent object to start the CountryDetailsActivity */
	//    	Intent intent = new Intent("in.wptrafficanalyzer.CountryDetailsActivity");
	    	
	    	/** Setting data ( the clicked item's position ) to this intent */
	  //  	intent.putExtra("position", position);
	    	
	    	/** Starting the activity by passing the implicit intent */
	    //	startActivity(intent);	    	
    	}
  //  }
