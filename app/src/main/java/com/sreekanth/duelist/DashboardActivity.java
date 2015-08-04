/*
 * Copyright (C) 2011 Wglxy.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sreekanth.duelist;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the base class for activities in the dashboard application.
 * It implements methods that are useful to all top level activities.
 * That includes: (1) stub methods for all the activity lifecycle methods;
 * (2) onClick methods for clicks on home, search, feature 1, feature 2, etc.
 * (3) a method for displaying a message to the screen via the Toast class.
 *
 */

public abstract class DashboardActivity extends Activity 
{
	   Boolean isInternetPresent = false;
	   
	   // Connection detector class
	   ConnectionDetector cd;
	   Context context;
/**
 * onCreate - called when the activity is first created.
 *
 * Called when the activity is first created. 
 * This is where you should do all of your normal static set up: create views, bind data to lists, etc. 
 * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
 * 
 * Always followed by onStart().
 *
 */

protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_default);
}
    
/**
 * onDestroy
 * The final call you receive before your activity is destroyed. 
 * This can happen either because the activity is finishing (someone called finish() on it, 
 * or because the system is temporarily destroying this instance of the activity to save space. 
 * You can distinguish between these two scenarios with the isFinishing() method.
 *
 */

protected void onDestroy ()
{
   super.onDestroy ();
}

/**
 * onPause
 * Called when the system is about to start resuming a previous activity. 
 * This is typically used to commit unsaved changes to persistent data, stop animations 
 * and other things that may be consuming CPU, etc. 
 * Implementations of this method must be very quick because the next activity will not be resumed 
 * until this method returns.
 * Followed by either onResume() if the activity returns back to the front, 
 * or onStop() if it becomes invisible to the user.
 *
 */

protected void onPause ()
{
   super.onPause ();
}

/**
 * onRestart
 * Called after your activity has been stopped, prior to it being started again.
 * Always followed by onStart().
 *
 */

protected void onRestart ()
{
   super.onRestart ();
}

/**
 * onResume
 * Called when the activity will start interacting with the user. 
 * At this point your activity is at the top of the activity stack, with user input going to it.
 * Always followed by onPause().
 *
 */

protected void onResume ()
{
   super.onResume ();
}

/**
 * onStart
 * Called when the activity is becoming visible to the user.
 * Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.
 *
 */

protected void onStart ()
{
   super.onStart ();
}

/**
 * onStop
 * Called when the activity is no longer visible to the user
 * because another activity has been resumed and is covering this one. 
 * This may happen either because a new activity is being started, an existing one 
 * is being brought in front of this one, or this one is being destroyed.
 *
 * Followed by either onRestart() if this activity is coming back to interact with the user, 
 * or onDestroy() if this activity is going away.
 */

protected void onStop ()
{
   super.onStop ();
}

/**
 */
// Click Methods

/**
 * Handle the click on the home button.
 * 
 * @param v View
 * @return void
 */

public void onClickHome (View v)
{
    goHome (this);
}

/**
 * Handle the click on the search button.
 * 
 * @param v View
 * @return void
 */

public void onClickSearch (View v)
{
    startActivity (new Intent(getApplicationContext(), FrmActivity.class));
}

/**
 * Handle the click on the About button.
 * 
 * @param v View
 * @return void
 */

public void onClickAbout (View v)
{
    startActivity (new Intent(getApplicationContext(), AboutActivity.class));
}
public void onClickAdd (View v)
{
    startActivity (new Intent(getApplicationContext(), AboutAdd.class));
}
public void onClickList (View v)
{
    startActivity (new Intent(getApplicationContext(), PmActivity.class));
}
public void onClickRev (View v)
{
    startActivity (new Intent(getApplicationContext(), RevActivity.class));
}

public void onClickSur(View v)
{
	 AlertDialog.Builder alert = new AlertDialog.Builder(DashboardActivity.this); 
	  alert.setTitle("Surrender Quotation");

	 
	  

	  alert.setMessage("Coming Soon");
	  alert.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
	      @Override
	      public void onClick(DialogInterface dialog, int id) {
	      dialog.dismiss();
	      }
	  });
	  alert.show();	
}

public void onClickFeed(View v)
{
	startActivity (new Intent(getApplicationContext(), Feedback.class));
}
public void onClickSet(View v)
{
	//Intent intent8 = new Intent(getApplicationContext(),SettingsTheme.class);
//	startActivity(intent8);
	Intent i = new Intent(this, UserSettingActivity.class);
	//startActivityForResult(i, RESULT_SETTINGS);
	startActivity(i);
	}
public void onClickUtil(View v)
{
	startActivity (new Intent(getApplicationContext(), F7Activity.class));
	}
public void onClickAskLic(View v)
{
	startActivity (new Intent(getApplicationContext(),ASKLICSMS.class));
	}
public void onClickDown(View v)
{
	 Intent intent = new Intent();
	   intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
	   startActivity(intent);
	}
public void onClickBuy(View v)
{
	 
	}
public void onClickDoc(View v)
{
	 startActivity (new Intent(getApplicationContext(),DoctorLocator.class));	 
	}
public void onClickshop(View v)
{
	Intent uIntent = new Intent(getApplicationContext(),Update.class);  	  
  	startActivity(uIntent); 
	}

public void onClickAPortal(View v)
{
	 startActivity (new Intent(getApplicationContext(), F3Activity.class));
	}
public void onClickCPortal(View v)
{
	
	}

public void onClickCBldg(View v)
{
	  startActivity (new Intent(getApplicationContext(),branch.class));
	}

public void onClickNav(View v)
{
	  Intent browserIntent = new Intent(getApplicationContext(),nav.class);  	  
  	startActivity(browserIntent);
	}
public void onClickSite(View v)
{
	 startActivity (new Intent(getApplicationContext(), Devwebsite.class)); 
	}
public void onClickAPerson(View v)
{
//	 startActivity (new Intent(getApplicationContext(), Devwebsite.class)); 
	}



/**
 * Handle the click of a Feature button.
 * 
 * @param v View
 * @return void
 */

public void onClickFeature (View v)
{
    int id = v.getId ();
    switch (id) {
    case R.id.home_feedback :
    	startActivity (new Intent(getApplicationContext(), Feedback.class));
    	   break;
      case R.id.home_btn_feature1 :
           startActivity (new Intent(getApplicationContext(), F1Activity.class));
           break;
      case R.id.home_btn_feature2 :
           startActivity (new Intent(getApplicationContext(), F2Activity.class));
           break;
      case R.id.home_btn_feature3 :
           startActivity (new Intent(getApplicationContext(), F3Activity.class));
           break;
      case R.id.home_btn_feature4 :
           startActivity (new Intent(getApplicationContext(), Otherbranchmenu.class));
           break;
      case R.id.home_btn_feature5 :
    	  
    	  cd = new ConnectionDetector(getApplicationContext());
    	  
    	  isInternetPresent = cd.isConnectingToInternet();
    	  
    	  // check for Internet status
    	  
    		     if (isInternetPresent) {
    		    	Intent uintent = new Intent(DashboardActivity.this,cloud.class);
    		    	uintent.putExtra("source", "db");
    		    	startActivity(uintent);

    				} else {
    	
    		  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    					DashboardActivity.this);
    	 
    				// set title
    				alertDialogBuilder.setTitle("No InterNet Connection");
    	 
    				// set dialog message
    				alertDialogBuilder
    					.setMessage("Enable Mobile data")
    					.setCancelable(false)
    					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    						public void onClick(DialogInterface dialog,int id) {
    							// if this button is clicked, close
    							// current activity
    							ConnectivityManager dataManager;
    					        dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    					        Method dataMtd = null;
    					        try {
    					            dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
    					        } catch (NoSuchMethodException e) {
    					            // TODO Auto-generated catch block
    					            e.printStackTrace();
    					        }
    					        dataMtd.setAccessible(true);
    					        try {
    					            dataMtd.invoke(dataManager, true);
    					        } catch (IllegalArgumentException e) {
    					            // TODO Auto-generated catch block
    					            e.printStackTrace();
    					        } catch (IllegalAccessException e) {
    					            // TODO Auto-generated catch block
    					            e.printStackTrace();
    					        } catch (InvocationTargetException e) {
    					            // TODO Auto-generated catch block
    					            e.printStackTrace();
    					        }   							
    					          
    						}
    					  })
    					.setNegativeButton("OK",new DialogInterface.OnClickListener() {
    						public void onClick(DialogInterface dialog,int id) {
    							// if this button is clicked, just close
    							// the dialog box and do nothing
    							dialog.cancel();
    						}
    					});
    	 
    					// create alert dialog
    					AlertDialog alertDialog = alertDialogBuilder.create();
    	 
    					// show it
    					alertDialog.show();
    	  }
    		
	 
    	  break;

      case R.id.home_btn_feature6 :
        //   startActivity (new Intent(getApplicationContext(), F6Activity.class));
    	   startActivity (new Intent(getApplicationContext(), DBRoulette.class));
           break;
      case R.id.home_extra :
          startActivity (new Intent(getApplicationContext(), F7Activity.class));
          break;     
      case R.id.home_reg :
          cd = new ConnectionDetector(getApplicationContext());
    	  isInternetPresent = cd.isConnectingToInternet();
    	  
    	  // check for Internet status
    	  
    		     if (isInternetPresent) {
    		    	 startActivity (new Intent(getApplicationContext(), F8Activity.class));	
    				} else {
    	
    		  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    					DashboardActivity.this);
    	 
    				// set title
    				alertDialogBuilder.setTitle("No InterNet Connection");
    	 
    				// set dialog message
    				alertDialogBuilder
    					.setMessage("Enable Mobile data")
    					.setCancelable(false)
    					/*.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    						public void onClick(DialogInterface dialog,int id) {
    							// if this button is clicked, close
    							// current activity
    							ConnectivityManager dataManager;
    					        dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    					        Method dataMtd = null;
    					        try {
    					            dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
    					        } catch (NoSuchMethodException e) {
    					            // TODO Auto-generated catch block
    					            e.printStackTrace();
    					        }
    					        dataMtd.setAccessible(true);
    					        try {
    					            dataMtd.invoke(dataManager, true);
    					        } catch (IllegalArgumentException e) {
    					            // TODO Auto-generated catch block
    					            e.printStackTrace();
    					        } catch (IllegalAccessException e) {
    					            // TODO Auto-generated catch block
    					            e.printStackTrace();
    					        } catch (InvocationTargetException e) {
    					            // TODO Auto-generated catch block
    					            e.printStackTrace();
    					        }   							
    					          
    						}
    					  })*/
    					.setNegativeButton("OK",new DialogInterface.OnClickListener() {
    						public void onClick(DialogInterface dialog,int id) {
    							// if this button is clicked, just close
    							// the dialog box and do nothing
    							dialog.cancel();
    						}
    					});
    	 
    					// create alert dialog
    					AlertDialog alertDialog = alertDialogBuilder.create();
    	 
    					// show it
    					alertDialog.show();
    	  }
          break;    
      case R.id.home_club :
          startActivity (new Intent(getApplicationContext(), F10Activity.class));
          break; 
      case R.id.home_doctor:
    	  startActivity (new Intent(getApplicationContext(),DoctorLocator.class));
    	  break;
      case R.id.home_pay:
   	// startActivity (new Intent(getApplicationContext(),Gmail.class));
    	  break;  
      case R.id.home_down:
    	  Intent intent = new Intent();
    	   intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
    	   startActivity(intent);
    	  break;
      case R.id.home_bank:
    	  startActivity (new Intent(getApplicationContext(),ASKLICSMS.class));
    	  break;
      case R.id.home_nav:
    	  
   	//  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.licindia.in/plannav/newplannav.htm"));
    	  Intent browserIntent = new Intent(getApplicationContext(),nav.class);  	  
    	startActivity(browserIntent);
    	  break;
      case R.id.home_office:
    	  startActivity (new Intent(getApplicationContext(),branch.class));
    	  break;	  
      case R.id.home_mypol:
    	  startActivity (new Intent(getApplicationContext(),Otherbranchmenu1.class));
    	  break;
    	  
      case R.id.home_updates:
    	  Intent uIntent = new Intent(getApplicationContext(),Update.class);  	  
      	startActivity(uIntent);
      	  break;
      default: 
    	   break;
    }
}

/**
 */
// More Methods

/**
 * Go back to the home activity.
 * 
 * @param context Context
 * @return void
 */

public void goHome(Context context) 
{
    final Intent intent = new Intent(context, HomeActivity.class);
    intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
    context.startActivity (intent);
}

/**
 * Use the activity label to set the text in the activity's title text view.
 * The argument gives the name of the view.
 *
 * <p> This method is needed because we have a custom title bar rather than the default Android title bar.
 * See the theme definitons in styles.xml.
 * 
 * @param textViewId int
 * @return void
 */

public void setTitleFromActivityLabel (int textViewId)
{
    TextView tv = (TextView) findViewById (textViewId);
    if (tv != null) tv.setText (getTitle ());
} // end setTitleText

/**
 * Show a string on the screen via Toast.
 * 
 * @param msg String
 * @return void
 */

public void toast (String msg)
{
    Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
} // end toast

/**
 * Send a message to the debug log and display it using Toast.
 */
public void trace (String msg) 
{
    Log.d("Demo", msg);
    toast (msg);
}

} // end class
