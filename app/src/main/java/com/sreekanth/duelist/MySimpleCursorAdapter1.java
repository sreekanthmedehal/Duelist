package com.sreekanth.duelist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

class MySimpleCursorAdapter1 extends SimpleCursorAdapter {
    public MySimpleCursorAdapter1(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
  
    public View newView(Context _context, Cursor _cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(_context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.country_info, parent, false);
        return view;
    }
//    String[] from = new String[] { "_id", "policyno", "polname", "prem", "fup"};  
//	  int[] to = new int[] { R.id.TextView1, R.id.TextView2, R.id.TextView3, R.id.TextView4, R.id.TextView5 };  
    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
    	
        String id = cursor.getString(cursor.getColumnIndex(PremCalc.REG_ID));
      
        String name = cursor.getString(cursor.getColumnIndex(PremCalc.REG_NAME)).trim();
        String plan = cursor.getString(cursor.getColumnIndex(PremCalc.REG_PLAN));
        String age = cursor.getString(cursor.getColumnIndex(PremCalc.REG_AGE));
       
        TextView formid = (TextView) view.findViewById(R.id.code);
        formid.setText(id);
        
      
        
        TextView formname = (TextView) view.findViewById(R.id.name);
        formname.setText(name);
        
        TextView formprem = (TextView) view.findViewById(R.id.region);
        formprem.setText(plan);
        
        TextView formfup = (TextView) view.findViewById(R.id.continent);
        formfup.setText(age);
       
        
        
        ImageButton yourButton = (ImageButton) view.findViewById(R.id.del_button);
       
        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    Object obj = view.getTag();
                   
                   
                    final String st = obj.toString();
                   
         //           Toast.makeText(context, " id = " + st, Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(context)
     	            .setIcon(R.drawable.icon)
     	            .setTitle(st)
     	            .setMessage("Want To Delete...!")
     	            .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int whichButton) {
                	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(context);
            	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
                	 database.delete(PremCalc.TABLE_STOCK,"_id =" + st,null);
                	 database.close();
                	 stockSQLHelper.close();
//                	 String[] from = new String[] { "_id", "policyno", "polname", "prem", "fup"};  
//                  	 int[] to = new int[] { R.id.TextView1, R.id.TextView2, R.id.TextView3, R.id.TextView4, R.id.TextView5 };  
                  	 cursor.requery();
//                  	 ListAdapter	  DBadapter3= new SimpleCursorAdapter(  
//                  	    	    PmActivity.this, R.layout.list1, c, from, to, 0);  
//                  	    	   list.setAdapter(DBadapter3);  
//                  	    	   registerForContextMenu(list);   
                  	 notifyDataSetChanged();
                            } })
    	          
    	            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	                    public void onClick(DialogInterface dialog, int whichButton) {
    	                            /* User clicked Cancel */
    	                    	dialog.cancel();
    	                    }
    	                    })
    	            .show();
                }
            }
        });
        Object obj = cursor.getString(cursor.getColumnIndex(PremCalc.REG_ID));
        yourButton.setTag(obj);
        
        ImageButton ConButton = (ImageButton) view.findViewById(R.id.customFAB1);
        
        ConButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    Object obj = view.getTag();
                   
                   
                    String st = obj.toString();
                   
                //    Toast.makeText(context, " id = " + st, Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
    	            builder1.setMessage("Coming Soon ....");
    	            builder1.setCancelable(true);
    	            builder1.setPositiveButton("Continue ...",
    	                    new DialogInterface.OnClickListener() {
    	                public void onClick(DialogInterface dialog, int id) {
    	                    dialog.cancel();
    	                }
    	            });
    	            AlertDialog alert11 = builder1.create();
    	            alert11.show();	     	
                }
            }
        });
        
        ConButton.setTag(obj);

    }
}