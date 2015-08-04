package com.sreekanth.duelist;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AlphabetListDemo extends Activity {
	//String of alphabets //
	String[] alphabts = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	ListView L1, L2;
	myAdapter myadp;
	myAdapter2 myadp2;
	String prod_arr[] = {};
	String prod_arr0[] = {};
	Cursor c;
	SQLiteDatabase database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphamain);
        
        L1 = (ListView)findViewById(R.id.list1);
        L2 = (ListView)findViewById(R.id.list2);
        
        myadp = new myAdapter(this,alphabts);
        L2.setAdapter(myadp);
        
        // initial populating //
        setProducts(0);	   
        
        L2.setOnItemClickListener(new OnItemClickListener(){
    		@Override
    		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, 
    				long arg3) {
    			setProducts(arg2);	        
    		}  
    	});
        
    }
    
    public void setProducts(int number){
    	
    	// adding some dummy data //
    	int i =0;
    	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	     
	     database = stockSQLHelper.getReadableDatabase();
	      c = database.rawQuery("select policyno,polname  from bmpolmast where substr(polname,1,1) = '"+alphabts[number]+"' order by policyno",null);
	     prod_arr0 = new String[c.getCount()];
	     prod_arr = new String[c.getCount()];
	     if (c.moveToFirst()) {
	    	    do {
	    	        // do what you need with the cursor here
	    	    	prod_arr0[i] = c.getString(c.getColumnIndex("policyno")).trim();
	    	    	prod_arr[i] = c.getString(c.getColumnIndex("policyno")).trim() + "   "+c.getString(c.getColumnIndex("polname")).trim();
	    	    	i++;
	    	    } while (c.moveToNext());
	    	}
	     c.close();
	        database.close();
	   
         
    	
		//setting the adapter in listview //
		 myadp2 = new myAdapter2(AlphabetListDemo.this,prod_arr);
	     L1.setAdapter(myadp2); 
	     L1.setOnItemClickListener(new OnItemClickListener(){
	    		@Override
	    		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, 
	    				long arg3) {
	    		//	setProducts(arg2);
	    			DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(AlphabetListDemo.this);
	    		     
	    		     database = stockSQLHelper.getReadableDatabase();    			
	    		//	 TextView txt = (TextView) arg1.findViewById(R.id.item_title);
	    	    //        String keyword = txt.getText().toString();
	    	          String  keyword = prod_arr0[arg2];
	    	            Toast.makeText(AlphabetListDemo.this, keyword, Toast.LENGTH_LONG).show();
	    	            Log.v("value ", "result is " + keyword);
	    	            c.close();
       c = database.rawQuery("select *  from bmpolmast where policyno = '"+keyword+"'",null); 	
       c.moveToFirst();      
	    String PolicyNo = 	 c.getString(c.getColumnIndex("policyno")).trim();           
	    String Add1 = 
				   c.getString(c.getColumnIndexOrThrow("add1"));
	       String Add2 = 
				   c.getString(c.getColumnIndexOrThrow("add2"));
		   String Add3 = 
				   c.getString(c.getColumnIndexOrThrow("add3"));
		   String Units = c.getString(c.getColumnIndexOrThrow("units"));
		   String AgCode = c.getString(c.getColumnIndexOrThrow("agcode"));
// 		   String DoCode = c.getString(c.getColumnIndexOrThrow("docode"));
		   String Name = c.getString(c.getColumnIndexOrThrow("polname"));
		   String Status = c.getString(c.getColumnIndexOrThrow("status"));
		   String DOC = c.getString(c.getColumnIndexOrThrow("doc"));
		   String Mode = c.getString(c.getColumnIndexOrThrow("mode"));
		   String SA = c.getString(c.getColumnIndexOrThrow("sa"));
		   String Prem = c.getString(c.getColumnIndexOrThrow("prem"));
		   String FUP = c.getString(c.getColumnIndexOrThrow("fup"));
		   String DOB = c.getString(c.getColumnIndexOrThrow("dob"));
		   String Pin = c.getString(c.getColumnIndexOrThrow("pin"));
		
		   String PlanNo = c.getString(c.getColumnIndexOrThrow("plan"));
		   String TermNo = c.getString(c.getColumnIndexOrThrow("term"));
	    Intent intent = new Intent(AlphabetListDemo.this,pmdetail.class);
	    intent.putExtra("PolNo",PolicyNo);
	    intent.putExtra("PolName",Name);
        intent.putExtra("PolFup",FUP);
        intent.putExtra("PolMode",Mode);
        intent.putExtra("PolDoc",DOC);
        intent.putExtra("PolStatus",Status);
        intent.putExtra("PolUnits",Units);
        intent.putExtra("PolPlan",PlanNo);
        intent.putExtra("PolTerm",TermNo);
        intent.putExtra("PolPrem",Prem);
        intent.putExtra("PolAgcode",AgCode);
        intent.putExtra("PolSa",SA);
        intent.putExtra("PolDob",DOB);
        intent.putExtra("PolAdd1",Add1);
        intent.putExtra("PolAdd2",Add2);
        intent.putExtra("PolAdd3",Add3);
        intent.putExtra("PolPin",Pin); 
	    
        startActivity(intent);
        c.close();
        database.close();
	    		}  
	    	});
	     
	     
	     
    }
    
    class myAdapter extends ArrayAdapter<String>
    {
  	   TextView label;	   
  	   ImageView image; 
  	   View row;
  	   public myAdapter(Context context,String[] arr)
  	   {
  		    super(context, android.R.layout.simple_list_item_1, arr); 
  	   }		
  	   
  	   public View getView(final int position, View convertView, ViewGroup parent)
  		{
  	 		   try{
  	 				LayoutInflater inflater=getLayoutInflater();
  	 				row = inflater.inflate(R.layout.lv_rows, parent, false);
  					label = (TextView)row.findViewById(R.id.item_title);
  					label.setText(alphabts[position]);
  					label.setTextColor(Color.BLUE);					
  	 		   }catch(Exception e){
  				   
  			   }					
  		    return row;
  		}
    }
    // adapter for second list.....
    class myAdapter2 extends ArrayAdapter<String>
    {
    
  	   TextView label;	   
  	   ImageView image; 
  	   View row;
  	   public myAdapter2(Context context,String[] arr)
  	   {
  		    super(context, android.R.layout.simple_list_item_1, arr); 
  	   }		
  	   
  	   public View getView(final int position, View convertView, ViewGroup parent)
  		{
  	 		   try{
  	 				LayoutInflater inflater=getLayoutInflater();
  	 				row = inflater.inflate(R.layout.lv_rows, parent, false);
  	 			
  					label = (TextView)row.findViewById(R.id.item_title);
  					label.setText(prod_arr[position]);
  					label.setTextColor(Color.GREEN);					
  	 		   }catch(Exception e){
  				   
  			   }					
  		    return row;
  		}
    }
}