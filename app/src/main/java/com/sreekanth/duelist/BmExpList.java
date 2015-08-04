package com.sreekanth.duelist;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;




public class BmExpList extends ExpandableListActivity implements                                                                      
SearchView.OnQueryTextListener, SearchView.OnCloseListener{                                                                
private SearchView search;                                                                                                 
private BmListAdapter listAdapter;                                                                                         
private ExpandableListView myList;                                                                                         
private ArrayList<Continent1> Continent1List = new ArrayList<Continent1>();                                                   
String folionumb;
String productid;
String netunits; 
String NetAmount;
String Mktval;
String Profit;
String Profit1;
String fyyear;
Date fdate;  
Date tdate;
Date  sdate;
String smonth;
String smsbody;
String smsfup; String smsagname; String smsprem;
String smsmobile;
@Override                                                                                                                  
public void onCreate(Bundle savedInstanceState) {                                                                          
super.onCreate(savedInstanceState);                                                                                        
setContentView(R.layout.activity_main1c);                                                                                    

Bundle extras = getIntent().getExtras(); 


if (extras != null) {
    fyyear = extras.getString("ftyear");
    
   /* String [] items = fyyear.split("-");
    String fs = items[0]+"/04/01";
    String ts = items[1]+"/03/31";
    SimpleDateFormat  format = new SimpleDateFormat("yyyy/MM/dd");  
    try {  
         fdate = format.parse(fs);  
         tdate = format.parse(ts);
    } catch (ParseException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }
    	          Toast.makeText(getBaseContext(), "From : " + format.format(fdate) +"To : "+format.format(tdate)  , Toast.LENGTH_LONG).show();*/
if (fyyear.equals("Jan")){smonth = "01";}
if (fyyear.equals("Feb")){smonth = "02";}
if (fyyear.equals("Mar")){smonth = "03";}
if (fyyear.equals("Apr")){smonth = "04";}
if (fyyear.equals("May")){smonth = "05";}
if (fyyear.equals("Jun")){smonth = "06";}
if (fyyear.equals("Jul")){smonth = "07";}
if (fyyear.equals("Aug")){smonth = "08";}
if (fyyear.equals("Sep")){smonth = "09";}
if (fyyear.equals("Oct")){smonth = "10";}
if (fyyear.equals("Nov")){smonth = "11";}
if (fyyear.equals("Dec")){smonth = "12";}

    Toast.makeText(getBaseContext(), "Month: " +fyyear  , Toast.LENGTH_LONG).show();
}


SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);                                    
search = (SearchView) findViewById(R.id.search);                                                                           
search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));                                             
search.setIconifiedByDefault(false);                                                                                       
search.setOnQueryTextListener(this);                                                                                       
search.setOnCloseListener(this);                                                                                           

//display the list                                                                                                         
displayList();     
                                                                                      
}                                                                                                                          
@Override                                                                                                                  
public boolean onCreateOptionsMenu(Menu menu) {                                                                            
//getMenuInflater().inflate(R.menu.main, menu);                                                                     
return true;                                                                                                               
}                                                                                                                          

//method to expand all groups                                                                                              
private void expandAll() {                                                                                                 
int count = listAdapter.getGroupCount();                                                                                   
for (int i = 0; i < count; i++){                                                                                           
myList.expandGroup(i);                                                                                                     
}                                                                                                                          
}                                                                                                                          

//method to expand all groups                                                                                              
private void displayList() {                                                                                               
 
//display the list                                                                                                         
final ArrayList<Continent1> Continent1List1  = loadSomeData();                                                                                                            
                                                          
//get reference to the ExpandableListView                                                                                  
 myList = getExpandableListView();                                                           
 //create the adapter by passing your ArrayList data                                                                        
 listAdapter = new BmListAdapter(this, Continent1List1);  
listAdapter.SubTotal();                                                                                      
   
 //attach the adapter to the list                                                                                           
 myList.setAdapter(listAdapter); 
 
//expand all Groups                                                                                                        
//expandAll();                         
myList.setOnChildClickListener(new OnChildClickListener() {
    
    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
        int groupPosition, int childPosition, long id) {
    	TextView tv = (TextView)v.findViewById(R.id.TextView2);
    	if(tv.getText().toString().trim().length()>0){
    	int value = Integer.parseInt(tv.getText().toString());
    	
    	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(BmExpList.this);
    	SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
    	
    	  
    	 String sql1 = "SELECT _id,bmpolno,bmname,bmadd1,bmadd2,bmadd3,bmpin,bmduefrom,bmprem,bmagcode,bmmobile FROM bmbillmast where bmpolno = " + value ;
		 Cursor c = database.rawQuery(sql1, null);
			c.moveToFirst();
		
    if (!c.isAfterLast()){
	
	do{
		   productid = c.getString(c.getColumnIndexOrThrow("bmpolno"));
		   folionumb = c.getString(c.getColumnIndexOrThrow("bmname"));
		  
		   Mktval = c.getString(c.getColumnIndexOrThrow("bmadd1"));
		   Profit = c.getString(c.getColumnIndexOrThrow("bmadd2"));
		   Profit1 = c.getString(c.getColumnIndexOrThrow("bmadd3"));
		   smsfup = c.getString(c.getColumnIndexOrThrow("bmduefrom"));
		   
		   smsprem = c.getString(c.getColumnIndexOrThrow("bmprem"));
		   smsagname = c.getString(c.getColumnIndexOrThrow("bmagcode"));
		   smsmobile = c.getString(c.getColumnIndexOrThrow("bmmobile"));
			} while (c.moveToNext());
	
  }
    c.close();
    database.close();
    stockSQLHelper.close();
    	
    	
/*        Toast.makeText(BmExpList.this, "POl No:"+productid+ "\n"
                                            + "Name:"+folionumb + "\n"
                                            + "add1:"+Mktval + "\n"
                                            + "add2:"+Profit + "\n"
                                            + "add3:"+Profit1,
                                            Toast.LENGTH_LONG).show(); */
        //String smsagtel = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
        smsbody = "Policy No : " + productid + "\n" + "PolName : " + folionumb + "\n"+ "Due : " + smsfup + "\n" + "Instl Prem : " + smsprem
         		  + "\n" + "Please Pay Premiums at LIC Office" + "\n" + smsagname + "\n";
        new AlertDialog.Builder(BmExpList.this)
        .setIcon(R.drawable.icon)
        .setTitle("Policy Master")
        .setMessage("Want to Send SMS...!")
      
        .setPositiveButton("Continue....", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                        /* User clicked Cancel */
                	
                	
                	
                	 Intent smsintent = new Intent(BmExpList.this,SMS.class);
	            	 smsintent.putExtra("smsmobno", smsmobile);
	            	 smsintent.putExtra("smsbody",smsbody);
	            	 startActivity(smsintent);        
                  	/*try {
  						 
  					     Intent sendIntent = new Intent(Intent.ACTION_VIEW);
  					     sendIntent.putExtra("address",smsmobile);
  					     sendIntent.putExtra("sms_body", smsbody); 
  					     sendIntent.setType("vnd.android-dir/mms-sms");
  					     startActivity(sendIntent);

  					} catch (Exception e) {
  						Toast.makeText(getApplicationContext(),
  							"SMS failed, please try again later!",
  							Toast.LENGTH_LONG).show();
  						e.printStackTrace();
  					}*/
                	dialog.cancel();
                }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                        /* User clicked Cancel */
                	dialog.cancel();
                }
        })
        
        
        
        
        
        
        .show();
    } return true;} });                                                                                     
}                                                                                                                     


private ArrayList<Continent1> loadSomeData() {  
	
	
	
	
	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
	ArrayList<Country2> countryList = new ArrayList<Country2>();
	SimpleDateFormat  format = new SimpleDateFormat("yyyy/MM/dd");  
	   
	String slabs[] = {"100000-1000000","50000-99999","10000-49999","5000-9999","3000-4999","1-2999"};
	for( int i = 0; i < slabs.length - 1; i++)
	{
		  String element = slabs[i];
		  String[] separated = element.split("-");
		  String GrParentl = separated[0];
		  String GrParenth = separated[1];
  String sql = "SELECT count(*) as scount FROM bmbillmast where bmprem >= " + GrParentl + " AND bmprem <=" + GrParenth + " AND " +  fyyear + " = 'Y'";;
		  
//	String sql = "SELECT  bmmod,count(bmmod) as cplan FROM bmbillmast  where bmdoc >= '"+format.format(fdate)+ "' and bmdoc <= '"+ format.format(tdate) +"' group by bmmod ORDER BY bmmod ";
	//System.out.println(sql);
//	String sql = "SELECT  bmmod,count(bmmod) as cplan FROM bmbillmast   WHERE " + fyyear + " = 'Y'  group by bmmod ORDER BY bmmod";

 	Cursor ds = database.rawQuery(sql, null);
	 
 	 ds.moveToFirst();
	 
	 
	 
	//Cursor c = database.query("purchasessales",
	//		 new String[] 
	//		 {"productid", "companycode","folionumb","netunits"},
	//		 null, null, null, null
	//		 , "purchasedfrom");

	 SimpleDateFormat  format1 = new SimpleDateFormat("yyyy/MM/dd");

 		 if (!ds.isAfterLast()){
 			 do{
 			 String GrParent = ds.getString(ds.getColumnIndexOrThrow("scount"));
//			 String GrParent1 = ds.getString(ds.getColumnIndexOrThrow("cplan"));
 			 Log.d("Response: ", "> " + GrParent);
			
//			 String sql1 = "SELECT _id,policyno,polname,prem,fup,doc FROM bmpolmast where plan = '" + GrParent + "' AND (status  = 'Lapse' OR status = 'paid-up') ORDER by SUBSTR(fup,4,4),SUBSTR(fup,1,2)";
			 String sql1 = "SELECT _id,bmpolno,bmname,bmprem,bmduefrom,bmdoc,bmsa,bmdueto,bmadd1,bmadd2,bmadd3,bmmod,nfdues FROM bmbillmast where bmprem >= " + GrParentl + " AND bmprem <=" + GrParenth + " AND " +  fyyear + " = 'Y'";;
			 Cursor c = database.rawQuery(sql1, null);
	//		 String sql1 = "SELECT _id,bmpolno,bmname,bmprem,bmdueto,bmdoc,bmsa FROM bmbillmast where bmmod = '" + GrParent + "' ORDER by SUBSTR(bmdueto,4,4),SUBSTR(bmdueto,1,2)";

				c.moveToFirst();
				countryList = new ArrayList<Country2>();
			
			
	    if (!c.isAfterLast()){
		
		do{
			
			
			  String id = c.getString(c.getColumnIndexOrThrow("_id")).toString();
			  String compcode = c.getString(c.getColumnIndexOrThrow("bmpolno"));
			  String purchasedfor = c.getString(c.getColumnIndexOrThrow("bmdoc")).toString();
			  String purfrom = c.getString(c.getColumnIndexOrThrow("bmprem")).toString();
			  String sa = c.getString(c.getColumnIndexOrThrow("bmduefrom")).toString();
			 // String bmdueto = c.getString(c.getColumnIndexOrThrow("bmdueto")).toString();
			 
			  String bmdueto = " ";
			  String bmadd1 = c.getString(c.getColumnIndexOrThrow("bmadd1")).toString();
			  String bmadd2 = c.getString(c.getColumnIndexOrThrow("bmadd2")).toString();
			  String bmadd3 = c.getString(c.getColumnIndexOrThrow("bmadd3")).toString();
			  String bmmod = c.getString(c.getColumnIndexOrThrow("bmmod")).toString();
			  String bname = c.getString(c.getColumnIndexOrThrow("bmname")).toString();
			  String nfdues = c.getString(c.getColumnIndexOrThrow("nfdues")).toString();
				 try {
			//		sdate = format1.parse(c.getString(c.getColumnIndexOrThrow("bmdoc")).toString());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      		          
					  Country2 country = new Country2(id,compcode,purchasedfor,purfrom,sa,bmdueto,bmadd1,bmadd2,bmadd3,bmmod,bname,nfdues);
						 
					  countryList.add(country);
	          //      }
			 
				} while (c.moveToNext());
		
	  }
//	    c.moveToFirst();
//	    if(!c.isAfterLast()){
			
//			do{
//				totnetunits = totnetunits+c.getFloat(c.getColumnIndexOrThrow("netunits"));
//				totnetamount = totnetamount + c.getFloat(c.getColumnIndexOrThrow("NetAmount"));
//				totprofit = totprofit +c.getFloat(c.getColumnIndexOrThrow("Profit"));
//			}while (c.moveToNext());
//		}
//	    Country country = new Country(String.valueOf(totnetunits),String.valueOf(totnetamount),String.valueOf(totprofit)," ");
//	   countryList.add(country);
	    c.close();
	    String groupdisp = GrParentl +"-"+GrParenth + "(" + GrParent + ")";
	     Continent1 Continent1 = new Continent1(groupdisp,countryList);
	     Continent1List.add(Continent1);
	   } while (ds.moveToNext()); 
	    }

 		ds.close();
	}
	
	database.close();
return Continent1List;
}

                                                                                                                     
@Override                                                                                                                  
public boolean onClose() {                                                                                                 
listAdapter.filterData("");                                                                                                
expandAll();                                                                                                               
return false;                                                                                                              
}                                                                                                                          
@Override                                                                                                                  
public boolean onQueryTextChange(String query) {                                                                           
listAdapter.filterData(query);                                                                                             
expandAll();                                                                                                               
return false;                                                                                                              
}                                                                                                                          
@Override                                                                                                                  
public boolean onQueryTextSubmit(String query) {                                                                           
listAdapter.filterData(query);                                                                                             
expandAll();                                                                                                               
return false;                                                                                                              
}   


} 






































                                                                                                                      
                                             
