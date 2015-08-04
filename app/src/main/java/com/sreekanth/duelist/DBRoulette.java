/*
 * Copyright (c) 2010-11 Dropbox, Inc.
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sreekanth.duelist;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.android.AuthActivity;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.TokenPair;



public class DBRoulette extends Activity {
    private static final String TAG = "DBRoulette";

    ///////////////////////////////////////////////////////////////////////////
    //                      Your app-specific settings.                      //
    ///////////////////////////////////////////////////////////////////////////

    // Replace this with your app key and secret assigned by Dropbox.
    // Note that this is a really insecure way to do this, and you shouldn't
    // ship code which contains your key & secret in such an obvious way.
    // Obfuscation is good.
    //             cp reddy            //
    //final static private String APP_KEY = "s0b1txyou99mztc";
    //final static private String APP_SECRET = "6mldwx4w5oppeeu";
    //             sunil kumar            //
    final static private String APP_KEY = "2ek55f8w1p2aqzi";
    final static private String APP_SECRET = "s8xbwmhik93clgi";

    // If you'd like to change the access type to the full Dropbox instead of
    // an app folder, change this value.
    final static private AccessType ACCESS_TYPE = AccessType.DROPBOX;

    ///////////////////////////////////////////////////////////////////////////
    //                      End app-specific settings.                       //
    ///////////////////////////////////////////////////////////////////////////

    // You don't need to change these, leave them alone.
    final static private String ACCOUNT_PREFS_NAME = "prefs";
    final static private String ACCESS_KEY_NAME = "ACCESS_KEY";
    final static private String ACCESS_SECRET_NAME = "ACCESS_SECRET";


    DropboxAPI<AndroidAuthSession> mApi;

    private boolean mLoggedIn;

    // Android widgets
    private Button mSubmit;
//    private LinearLayout mDisplay;
    private Button mPhoto;
    private Button mRoulette;
    private Button mCount;
    
    
    private Button mPhotoa;
    private Button mRoulettea;
    private Button mCounta;
    
    private Button mPhotob;
    private Button mRouletteb;
    private Button mCountb;
    
    private Button mPhotoc;
    private Button mRoulettec;
    private Button mCountc;
    
    private TextView mImage;
    Object[] obj;

    private final String PHOTO_DIR = "/Photos/";

    final static private int NEW_PICTURE = 1;
    private String mCameraFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCameraFileName = savedInstanceState.getString("mCameraFileName");
        }

        // We create a new AuthSession so that we can use the Dropbox API.
        AndroidAuthSession session = buildSession();
        mApi = new DropboxAPI<AndroidAuthSession>(session);

        // Basic Android widgets
        setContentView(R.layout.main);

        checkAppKeySetup();

        mSubmit = (Button)findViewById(R.id.auth_button);

        mSubmit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // This logs you out if you're logged in, or vice versa
                if (mLoggedIn) {
                    logOut();
                } else {
                    // Start the remote authentication
                    mApi.getSession().startAuthentication(DBRoulette.this);
                }
            }
        });

//        mDisplay = (LinearLayout)findViewById(R.id.logged_in_display);

        // This is where a photo is displayed
        mImage = (TextView)findViewById(R.id.fileNameTextView);

        // This is the button to take a photo
        mPhoto = (Button)findViewById(R.id.photo_button);
        mPhoto.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(DBRoulette.this);
     	        SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
     	        database.execSQL("DELETE FROM bmpolmast");
     	    	File dir = Environment.getExternalStorageDirectory();
     	    	File yourFile = new File(dir, "download/" + "0080165pm.csv");
     	      
     	        database.beginTransaction();
     		try {
     			
     			FileReader file = new FileReader(yourFile);
     		
     	        BufferedReader buffer = new BufferedReader(file);
     		String line = "";

     		 ContentValues values;
     		 

     		
     		while ((line = buffer.readLine()) != null) {

     		    String[] str = line.split("#");
     		   if (str.length == 21) {
     		       
	                values = new ContentValues();
	                values.put(bmpolmast.PM_TYPE,  str[1]);
	                values.put(bmpolmast.PM_BRANCH, str[2]);
	                values.put(bmpolmast.PM_POLNO, str[3]);
	                values.put(bmpolmast.PM_UNITS, str[4]);
	                values.put(bmpolmast.PM_PLAN, str[5]);
	                values.put(bmpolmast.PM_AGCODE, str[6]);
	                values.put(bmpolmast.PM_DOCODE, str[7]);
	                values.put(bmpolmast.PM_SA, str[8].replace(",",""));
	                values.put(bmpolmast.PM_PREM, str[9].replace(",",""));
	                values.put(bmpolmast.PM_TERM, str[10]);
	                values.put(bmpolmast.PM_FUP, str[11]);
	                values.put(bmpolmast.PM_NAME, str[12]);
	                values.put(bmpolmast.PM_STATUS, str[13]);
	                values.put(bmpolmast.PM_DOC, str[14]);
	                values.put(bmpolmast.PM_MOD, str[15]);
	                values.put(bmpolmast.PM_ADD1, str[16]);
	                values.put(bmpolmast.PM_ADD2, str[17]);
	                values.put(bmpolmast.PM_ADD3, str[18]);
	                values.put(bmpolmast.PM_PIN, str[19]);
	                values.put(bmpolmast.PM_DOB, str[20]);
	                
	                database.insert(bmpolmast.TABLE_STOCK, null, values);
	            }

                 
               }
             
     		   
     		
     		buffer.close();
     		
     		} catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     			
     		}
     		
     		   String sql = "select count(*) as mskcount from bmpolmast";
     	       Cursor navcount = database.rawQuery(sql, null);
     	       navcount.moveToFirst();
     		
     	
     		
               String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
               Toast.makeText(getApplicationContext(), "Job Over,PM Records Inserted = "  + ncount,
             		   Toast.LENGTH_LONG).show();
               database.setTransactionSuccessful();// marks a commit
               database.endTransaction();
         
            }
        });
        // This is the button to count records
        mCount = (Button)findViewById(R.id.count_button);

        mCount.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
             	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(DBRoulette.this);
      	        SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
      	      String sql = "select count(*) as mskcount from bmpolmast";
    	       Cursor navcount = database.rawQuery(sql, null);
    	       navcount.moveToFirst();
    		
    	
    		
              String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
              Toast.makeText(getApplicationContext(), "PM Records ..... = "  + ncount,
            		   Toast.LENGTH_LONG).show();
              database.close();
            }
        });

        // This is the button to take a photo
        mRoulette = (Button)findViewById(R.id.roulette_button);

        mRoulette.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DownloadRandomPicture download = new DownloadRandomPicture(DBRoulette.this, mApi,  PHOTO_DIR,"0080165pm.csv");
                download.execute();
                
            }
        });
        /***********************************************************************/
        mRoulettea = (Button)findViewById(R.id.roulette_buttona);
        mPhotoa = (Button)findViewById(R.id.photo_buttona);
        mCounta = (Button)findViewById(R.id.count_buttona);
        
        mRoulettea.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            DownloadRandomPicture download = new DownloadRandomPicture(DBRoulette.this, mApi, PHOTO_DIR,"0080165ag.csv");
            download.execute();
                
            }
        });
        
        mPhotoa.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(DBRoulette.this);
     	        SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
     	        database.execSQL("DELETE FROM agentdata");
     	    	File dir = Environment.getExternalStorageDirectory();
     	    	File yourFile = new File(dir, "download/" + "0080165ag.csv");
     	      
     	        database.beginTransaction();
     		try {
     			
     			FileReader file = new FileReader(yourFile);
     		
     	        BufferedReader buffer = new BufferedReader(file);
     		String line = "";

     		 ContentValues values;
     		 

     		
     		while ((line = buffer.readLine()) != null) {

     		    String[] str = line.split("#");
     		 
	            
     		 if (str.length == 27) {
     		       
	                values = new ContentValues();
	                values.put(AgentData.AG_CODE, str[1]);
	                values.put(AgentData.AG_NAME, str[2]);
	                values.put(AgentData.AG_ELGCODE, str[3]);
	                values.put(AgentData.AG_DOCODE, str[4]);
	                values.put(AgentData.AG_SEX, str[5]);
	                values.put(AgentData.AG_CLUB, str[6]);
	                values.put(AgentData.AG_PAN, str[7]);
	                values.put(AgentData.AG_DOA, str[8]);
	                values.put(AgentData.AG_DOB, str[9]);
	                values.put(AgentData.AG_LICENNO, str[10]);
	                values.put(AgentData.AG_LICEXPDATE, str[11]);
	                values.put(AgentData.AG_ACCOUNTNO, str[12]);
	                values.put(AgentData.AG_BANKNAME, str[13]);
	                values.put(AgentData.AG_DOE, str[14]);
	                values.put(AgentData.AG_DOR, str[15]);
	                values.put(AgentData.AG_ADR1, str[16]);
	                values.put(AgentData.AG_ADR2, str[17]);
	                values.put(AgentData.AG_ADR3, str[18]);
	                values.put(AgentData.AG_PIN, str[19]);
	                values.put(AgentData.AG_TEL, str[20]);
	                values.put(AgentData.AG_ENTRYMD, str[21]);
	                values.put(AgentData.AG_EXITMD, str[22]);
	                values.put(AgentData.AG_NOMINEE, str[23]);
	                values.put(AgentData.AG_NOMINEEREL, str[24]);
	                values.put(AgentData.AG_CLIACD, str[25]);
	                values.put(AgentData.AG_EMAIL, str[26]);
	                database.insert(AgentData.TABLE_STOCK, null, values);
                 
               }
             
     		}
     		
     		buffer.close();
     		
     		} catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     			
     		}
     		
     		   String sql = "select count(*) as mskcount from agentdata";
     	       Cursor navcount = database.rawQuery(sql, null);
     	       navcount.moveToFirst();
     		
     	
     		
               String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
               Toast.makeText(getApplicationContext(), "Job Over,Agency Records Inserted = "  + ncount,
             		   Toast.LENGTH_LONG).show();
               database.setTransactionSuccessful();// marks a commit
               database.endTransaction();
         
            }
        });
        
        
        mCounta.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
             	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(DBRoulette.this);
      	        SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
      	      String sql = "select count(*) as mskcount from agentdata";
    	       Cursor navcount = database.rawQuery(sql, null);
    	       navcount.moveToFirst();
    		
    	
    		
              String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
              Toast.makeText(getApplicationContext(), "Agent Records ..... = "  + ncount,
            		   Toast.LENGTH_LONG).show();
              database.close();
            }
        }); 
        
        
  /**************************************************************/      
        mRouletteb = (Button)findViewById(R.id.roulette_buttonb);
        mPhotob = (Button)findViewById(R.id.photo_buttonb);
        mCountb = (Button)findViewById(R.id.count_buttonb);
        
        mRouletteb.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            DownloadRandomPicture download = new DownloadRandomPicture(DBRoulette.this, mApi, PHOTO_DIR,"0080165bm.csv");
            download.execute();
                
            }
        });
        
        mPhotob.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(DBRoulette.this);
     	        SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
     	        database.execSQL("DELETE FROM bmbillmast");
     	    	File dir = Environment.getExternalStorageDirectory();
     	    	File yourFile = new File(dir, "download/" + "0080165bm.csv");
     	      
     	        database.beginTransaction();
     		try {
     			
     			FileReader file = new FileReader(yourFile);
     		
     	        BufferedReader buffer = new BufferedReader(file);
     		String line = "";

     		 ContentValues values;
     		 

     		
     		while ((line = buffer.readLine()) != null) {

     		    String[] RowData = line.split("#");
     		 
	            
     		   if (RowData.length == 28) {
     		       
                   values = new ContentValues();
               System.out.println(RowData[0]);
                   values.put(bmbillmast.BM_POLNO, RowData[0]);
                  values.put(bmbillmast.BM_DOC, RowData[1]);
                   values.put(bmbillmast.BM_PLAN, RowData[2]);
                   values.put(bmbillmast.BM_TERM, RowData[3]);
                   values.put(bmbillmast.BM_MOD, RowData[4]);
                   values.put(bmbillmast.BM_SA, RowData[5]);
                   values.put(bmbillmast.BM_PREM, RowData[6]);
                   values.put(bmbillmast.BM_AGCODE, RowData[7]);
                   values.put(bmbillmast.BM_DEVOFF, RowData[8]);
                   values.put(bmbillmast.BM_DUEFROM, RowData[9]);
                   values.put(bmbillmast.BM_DUETO, RowData[10]);
                   values.put(bmbillmast.BM_NAME, RowData[11]);
                   values.put(bmbillmast.BM_ADD1, RowData[12]);
                   values.put(bmbillmast.BM_ADD2, RowData[13]);
                   values.put(bmbillmast.BM_ADD3, RowData[14]);
                   values.put(bmbillmast.BM_PIN, RowData[15]);
                   values.put(bmbillmast.BM_APR, RowData[16]);
                   values.put(bmbillmast.BM_MAY, RowData[17]);
                   values.put(bmbillmast.BM_JUN, RowData[18]);
                   values.put(bmbillmast.BM_JUL, RowData[19]);
                   values.put(bmbillmast.BM_AUG, RowData[20]);
                   values.put(bmbillmast.BM_SEP, RowData[21]);
                   values.put(bmbillmast.BM_OCT, RowData[22]);
                   values.put(bmbillmast.BM_NOV, RowData[23]);
                   values.put(bmbillmast.BM_DECE, RowData[24]);
                   values.put(bmbillmast.BM_JAN, RowData[25]);
                   values.put(bmbillmast.BM_FEB, RowData[26]);
                   values.put(bmbillmast.BM_MAR, RowData[27]);
              
                   database.insert(bmbillmast.TABLE_STOCK, null, values);
               }
             
     		}
     		
     		buffer.close();
     		
     		} catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     			
     		}
     		
     		   String sql = "select count(*) as mskcount from bmbillmast";
     	       Cursor navcount = database.rawQuery(sql, null);
     	       navcount.moveToFirst();
     		
     	
     		
               String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
               Toast.makeText(getApplicationContext(), "Job Over,Dues Records Inserted = "  + ncount,
             		   Toast.LENGTH_LONG).show();
               database.setTransactionSuccessful();// marks a commit
               database.endTransaction();
         
            }
        });
        
        
        mCountb.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
             	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(DBRoulette.this);
      	        SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
      	      String sql = "select count(*) as mskcount from bmbillmast";
    	       Cursor navcount = database.rawQuery(sql, null);
    	       navcount.moveToFirst();
    		
    	
    		
              String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
              Toast.makeText(getApplicationContext(), "Dues Records ..... = "  + ncount,
            		   Toast.LENGTH_LONG).show();
              database.close();
            }
        }); 
        /**************************************************************/     
        
        mRoulettec = (Button)findViewById(R.id.roulette_buttonc);
        mPhotoc = (Button)findViewById(R.id.photo_buttonc);
        mCountc = (Button)findViewById(R.id.count_buttonc);
        
        mRoulettec.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            DownloadRandomPicture download = new DownloadRandomPicture(DBRoulette.this, mApi, PHOTO_DIR,"0080165nb.csv");
            download.execute();
                
            }
        });
        
        
        mPhotoc.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(DBRoulette.this);
     	        SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
     	        database.execSQL("DELETE FROM nbcr");
     	    	File dir = Environment.getExternalStorageDirectory();
     	    	File yourFile = new File(dir, "download/" + "0080165nb.csv");
     	      
     	        database.beginTransaction();
     		try {
     			
     			FileReader file = new FileReader(yourFile);
     		
     	        BufferedReader buffer = new BufferedReader(file);
     		String line = "";

     		 ContentValues values;
     		 

     		
     		while ((line = buffer.readLine()) != null) {

     		    String[] RowData = line.split("#");
     		 
	            
     		   if (RowData.length == 10) {
     		       
                   values = new ContentValues();
               System.out.println(RowData[0]);
                   values.put(NBData.NB_POLNO, RowData[0]);
                  values.put(NBData.NB_COMMDT, RowData[1]);
                   values.put(NBData.NB_SA, RowData[2]);
                   values.put(NBData.NB_PREM, RowData[3]);
                   values.put(NBData.NB_PLAN, RowData[4]);
                   values.put(NBData.NB_TERM, RowData[5]);
                   values.put(NBData.NB_AGCODE, RowData[6]);
                   values.put(NBData.NB_DOCODE, RowData[7]);
                   values.put(NBData.NB_MODE, RowData[8]);
                   values.put(NBData.NB_NAME, RowData[9]);
                 
                 
                   database.insert(NBData.TABLE_STOCK, null, values);
               }
             
     		}
     		
     		buffer.close();
     		
     		} catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     			
     		}
     		
     		   String sql = "select count(*) as mskcount from nbcr";
     	       Cursor navcount = database.rawQuery(sql, null);
     	       navcount.moveToFirst();
     		
     	
     		
               String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
               Toast.makeText(getApplicationContext(), "Job Over,NB Records Inserted = "  + ncount,
             		   Toast.LENGTH_LONG).show();
               database.setTransactionSuccessful();// marks a commit
               database.endTransaction();
         
            }
        }); 
        
        mCountc.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
             	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(DBRoulette.this);
      	        SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
      	      String sql = "select count(*) as mskcount from nbcr";
    	       Cursor navcount = database.rawQuery(sql, null);
    	       navcount.moveToFirst();
    		
    	
    		
              String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
              Toast.makeText(getApplicationContext(), "NB Records ..... = "  + ncount,
            		   Toast.LENGTH_LONG).show();
              database.close();
            }
        }); 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /**************************************************************/     
        // Display the proper UI state if logged in or not
        setLoggedIn(mApi.getSession().isLinked());
        
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("mCameraFileName", mCameraFileName);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AndroidAuthSession session = mApi.getSession();

        // The next part must be inserted in the onResume() method of the
        // activity from which session.startAuthentication() was called, so
        // that Dropbox authentication completes properly.
        if (session.authenticationSuccessful()) {
            try {
                // Mandatory call to complete the auth
                session.finishAuthentication();

                // Store it locally in our app for later use
                TokenPair tokens = session.getAccessTokenPair();
                storeKeys(tokens.key, tokens.secret);
                setLoggedIn(true);
            } catch (IllegalStateException e) {
                showToast("Couldn't authenticate with Dropbox:" + e.getLocalizedMessage());
                Log.i(TAG, "Error authenticating", e);
            }
        }
    }

    // This is what gets called on finishing a media piece to import
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_PICTURE) {
            // return from file upload
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = null;
                if (data != null) {
                    uri = data.getData();
                }
                if (uri == null && mCameraFileName != null) {
                    uri = Uri.fromFile(new File(mCameraFileName));
                }
                File file = new File(mCameraFileName);

                if (uri != null) {
                    UploadPicture upload = new UploadPicture(this, mApi, PHOTO_DIR, file);
                    upload.execute();
                }
            } else {
                Log.w(TAG, "Unknown Activity Result from mediaImport: "
                        + resultCode);
            }
        }
    }

    private void logOut() {
        // Remove credentials from the session
        mApi.getSession().unlink();

        // Clear our stored keys
        clearKeys();
        // Change UI state to display logged out version
        setLoggedIn(false);
    }

    /**
     * Convenience function to change UI state based on being logged in
     */
    private void setLoggedIn(boolean loggedIn) {
    	mLoggedIn = loggedIn;
    	if (loggedIn) {
    		mSubmit.setText("Unlink from Dropbox");
            mImage.setVisibility(View.VISIBLE);
            mRoulette.setVisibility(View.VISIBLE);
            mPhoto.setVisibility(View.VISIBLE);
            mCount.setVisibility(View.VISIBLE);
            mRoulettea.setVisibility(View.VISIBLE);
            mPhotoa.setVisibility(View.VISIBLE);
            mCounta.setVisibility(View.VISIBLE);
            mRouletteb.setVisibility(View.VISIBLE);
            mPhotob.setVisibility(View.VISIBLE);
            mCountb.setVisibility(View.VISIBLE);
            mRoulettec.setVisibility(View.VISIBLE);
            mPhotoc.setVisibility(View.VISIBLE);
            mCountc.setVisibility(View.VISIBLE);
    	} else {
    		mSubmit.setText("Link with Dropbox");
            mImage.setVisibility(View.GONE);
            mRoulette.setVisibility(View.GONE);
            mPhoto.setVisibility(View.GONE);
            mCount.setVisibility(View.GONE);
            mRoulettea.setVisibility(View.GONE);
            mPhotoa.setVisibility(View.GONE);
            mCounta.setVisibility(View.GONE);
            mRouletteb.setVisibility(View.GONE);
            mPhotob.setVisibility(View.GONE);
            mCountb.setVisibility(View.GONE);
            mRoulettec.setVisibility(View.GONE);
            mPhotoc.setVisibility(View.GONE);
            mCountc.setVisibility(View.GONE);
    	}
    }

    private void checkAppKeySetup() {
        // Check to make sure that we have a valid app key
        if (APP_KEY.startsWith("CHANGE") ||
                APP_SECRET.startsWith("CHANGE")) {
            showToast("You must apply for an app key and secret from developers.dropbox.com, and add them to the DBRoulette ap before trying it.");
            finish();
            return;
        }

        // Check if the app has set up its manifest properly.
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        String scheme = "db-" + APP_KEY;
        String uri = scheme + "://" + AuthActivity.AUTH_VERSION + "/test";
        testIntent.setData(Uri.parse(uri));
        PackageManager pm = getPackageManager();
        if (0 == pm.queryIntentActivities(testIntent, 0).size()) {
            showToast("URL scheme in your app's " +
                    "manifest is not set up correctly. You should have a " +
                    "com.dropbox.client2.android.AuthActivity with the " +
                    "scheme: " + scheme);
            finish();
        }
    }

    private void showToast(String msg) {
        Toast error = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        error.show();
    }

    /**
     * Shows keeping the access keys returned from Trusted Authenticator in a local
     * store, rather than storing user name & password, and re-authenticating each
     * time (which is not to be done, ever).
     *
     * @return Array of [access_key, access_secret], or null if none stored
     */
    private String[] getKeys() {
        SharedPreferences prefs = getSharedPreferences(ACCOUNT_PREFS_NAME, 0);
        String key = prefs.getString(ACCESS_KEY_NAME, null);
        String secret = prefs.getString(ACCESS_SECRET_NAME, null);
        if (key != null && secret != null) {
        	String[] ret = new String[2];
        	ret[0] = key;
        	ret[1] = secret;
        	return ret;
        } else {
        	return null;
        }
    }

    /**
     * Shows keeping the access keys returned from Trusted Authenticator in a local
     * store, rather than storing user name & password, and re-authenticating each
     * time (which is not to be done, ever).
     */
    private void storeKeys(String key, String secret) {
        // Save the access key for later
        SharedPreferences prefs = getSharedPreferences(ACCOUNT_PREFS_NAME, 0);
        Editor edit = prefs.edit();
        edit.putString(ACCESS_KEY_NAME, key);
        edit.putString(ACCESS_SECRET_NAME, secret);
        edit.commit();
    }

    private void clearKeys() {
        SharedPreferences prefs = getSharedPreferences(ACCOUNT_PREFS_NAME, 0);
        Editor edit = prefs.edit();
        edit.clear();
        edit.commit();
    }

    private AndroidAuthSession buildSession() {
        AppKeyPair appKeyPair = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session;

        String[] stored = getKeys();
        if (stored != null) {
            AccessTokenPair accessToken = new AccessTokenPair(stored[0], stored[1]);
            session = new AndroidAuthSession(appKeyPair, ACCESS_TYPE, accessToken);
        } else {
            session = new AndroidAuthSession(appKeyPair, ACCESS_TYPE);
        }

        return session;
    }
}
