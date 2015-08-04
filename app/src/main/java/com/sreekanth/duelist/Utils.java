package com.sreekanth.duelist;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class Utils {

	private Context _context;
	public static String SIZE="";
	  public static boolean settingChanged=false;
	  public static String THEME="";
	// constructor
	public Utils(Context context) {
		this._context = context;
	}

	/*
	 * Reading file paths from SDCard
	 */
	public ArrayList<String> getFilePaths() {
		ArrayList<String> filePaths = new ArrayList<String>();

		File directory = new File(
				android.os.Environment.getExternalStorageDirectory()
						+ File.separator + AppConstant.PHOTO_ALBUM);

		// check for directory
		if (directory.isDirectory()) {
			// getting list of file paths
			File[] listFiles = directory.listFiles();

			// Check for count
			if (listFiles.length > 0) {

				// loop through all files
				for (int i = 0; i < listFiles.length; i++) {

					// get file path
					String filePath = listFiles[i].getAbsolutePath();

					// check for supported file extension
					if (IsSupportedFile(filePath)) {
						// Add image path to array list
						filePaths.add(filePath);
					}
				}
			} else {
				// image directory is empty
				Toast.makeText(
						_context,
						AppConstant.PHOTO_ALBUM
								+ " is empty. Please load some images in it !",
						Toast.LENGTH_LONG).show();
			}

		} else {
			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("Error!");
			alert.setMessage(AppConstant.PHOTO_ALBUM
					+ " directory path is not valid! Please set the image directory name AppConstant.java class");
			alert.setPositiveButton("OK", null);
			alert.show();
		}

		return filePaths;
	}

	/*
	 * Check supported file extensions
	 * 
	 * @returns boolean
	 */
	private boolean IsSupportedFile(String filePath) {
		String ext = filePath.substring((filePath.lastIndexOf(".") + 1),
				filePath.length());

		if (AppConstant.FILE_EXTN
				.contains(ext.toLowerCase(Locale.getDefault())))
			return true;
		else
			return false;

	}

	/*
	 * getting screen width
	 */

	@SuppressLint("NewApi")
	public int getScreenWidth() {
		int columnWidth;
		WindowManager wm = (WindowManager) _context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (java.lang.NoSuchMethodError ignore) { // Older device
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		columnWidth = point.x;
		return columnWidth;
	}
	 public static void setThemeToActivity(Activity act )
	  {
	  
	   try {
	  

//	   if (Utils.SIZE.equalsIgnoreCase("LARGE"))
//	   {
//	       act.setTheme(R.style.Theme_LargeText);
//	       Log.d(" ", "Theme Large txt size is to be is applied.");
//	   }
//	    if (Utils.SIZE.equalsIgnoreCase("SMALL"))
//	   {
//	       act.setTheme(R.style.Theme_SmallText);
//	       Log.d(" ", "Theme Small text Size is to be is applied.");
//	   }
//	   
//	    if(Utils.SIZE.equalsIgnoreCase("DEFAULT"))
//	    {
//	     act.setTheme(R.style.Theme_DefaultText);
//	     Log.d("", "theme default text size is applied.");
//	    }
	   
	    if(Utils.THEME.equalsIgnoreCase("Dark"))
	    {
	     act.setTheme(android.R.style.Theme_DeviceDefault);
	//     Log.d("", "Default theme is to be applied.");
	    }


	    if(Utils.THEME.equalsIgnoreCase("Light"))
	    {
	     act.setTheme(R.style.Theme_Example);
	 //    Log.d("", "gray theme is to be applied.");
	    }

//	    if(Utils.THEME.equalsIgnoreCase("Radial"))
//	    {
//	     act.setTheme(R.style.Theme_Radial);
//	     Log.d("", "radial theme is to be applied.");
//	    }
	    
	  
	   }
	   catch (Exception e) {
	  e.printStackTrace();
	 }
	  
	  }
}
