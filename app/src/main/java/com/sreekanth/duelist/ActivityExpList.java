package com.sreekanth.duelist;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;




public class ActivityExpList extends ExpandableListActivity implements                                                                      
SearchView.OnQueryTextListener, SearchView.OnCloseListener{                                                                
private SearchView search;                                                                                                 
private MyListAdapter listAdapter;                                                                                         
private ExpandableListView myList;                                                                                         
private ArrayList<Continent> continentList = new ArrayList<Continent>();                                                   
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
@Override                                                                                                                  
public void onCreate(Bundle savedInstanceState) {                                                                          
super.onCreate(savedInstanceState);                                                                                        
setContentView(R.layout.activity_main1c);                                                                                    

Bundle extras = getIntent().getExtras(); 


if (extras != null) {
    fyyear = extras.getString("ftyear");
    String [] items = fyyear.split("-");
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
    	          Toast.makeText(getBaseContext(), "From : " + format.format(fdate) +"To : "+format.format(tdate)  , Toast.LENGTH_LONG).show();

   
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
final ArrayList<Continent> continentList1  = loadSomeData();                                                                                                            
                                                          
//get reference to the ExpandableListView                                                                                  
 myList = getExpandableListView();                                                           
 //create the adapter by passing your ArrayList data                                                                        
 listAdapter = new MyListAdapter(this, continentList1);  
listAdapter.SubTotal();                                                                                      
   
 //attach the adapter to the list                                                                                           
 myList.setAdapter(listAdapter); 
 
//expand all Groups                                                                                                        
//expandAll();                         
myList.setOnChildClickListener(new OnChildClickListener() {
    
    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
        int groupPosition, int childPosition, long id) {
    	 InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    	    imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
    	TextView tv = (TextView)v.findViewById(R.id.name);
    	if(tv.getText().toString().trim().length()>0){
    	int value = Integer.parseInt(tv.getText().toString());
    	
    	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(ActivityExpList.this);
    	SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
    	
    	  
    	 String sql1 = "SELECT _id,policyno,sa,doc,polname,add1,add2,add3,pin FROM bmpolmast where policyno = " + value ;
		 Cursor c = database.rawQuery(sql1, null);
			c.moveToFirst();
		
    if (!c.isAfterLast()){
	
	do{
		   productid = c.getString(c.getColumnIndexOrThrow("policyno"));
		   folionumb = c.getString(c.getColumnIndexOrThrow("polname"));
		   netunits = c.getString(c.getColumnIndexOrThrow("sa"));
		   NetAmount = c.getString(c.getColumnIndexOrThrow("doc"));
		   Mktval = c.getString(c.getColumnIndexOrThrow("add1"));
		   Profit = c.getString(c.getColumnIndexOrThrow("add2"));
		   Profit1 = c.getString(c.getColumnIndexOrThrow("add3"));
			} while (c.moveToNext());
	
  }
    c.close();
    database.close();
    stockSQLHelper.close();
    AlertDialog.Builder builder1 = new AlertDialog.Builder(ActivityExpList.this);
    WebView wv = new WebView(getApplicationContext());
    String html = "<html><body><table bgcolor=\"green\" border=1>"  
    		+ "<tr><td>" + "Policy Number" +"</td>" + "<td>" + productid.trim() +"</td></tr>"
    		+ "<tr><td>" + "Name" +"</td>" + "<td>" + folionumb.trim() +"</td></tr>"
    		+ "<tr><td>" + "Sum Assu" +"</td>" + "<td>" + netunits.trim() +"</td></tr>"
    		+ "<tr><td>" + "DOC" +"</td>" + "<td>" + NetAmount.trim() +"</td></tr>"
    		+ "<tr><td>" + "Address1" +"</td>" + "<td>" + Mktval.trim() +"</td></tr>"
    		+ "<tr><td>" + "Address2" +"</td>" + "<td>" + Profit.trim() +"</td></tr>"
    		+ "<tr><td>" + "Address3" +"</td>" + "<td>" + Profit1.trim() +"</td></tr>"
    		         
    		
    		
    		
    		+ "</body></html>";
    wv.loadData(html, "text/html", "UTF-8");
    builder1.setView(wv);
    
    
 //   final ScrollView s_view = new ScrollView(getApplicationContext());
 //   final TextView t_view = new TextView(getApplicationContext());
 //   t_view.setText("----------------------------------------------------" + "\n"  
//            + " Pol      |  "+productid.trim()+ "\n"
//            + " ---------------------------------------------------" + "\n"
//            + " Name  |  "+folionumb.trim() + "\n"
//            + " ---------------------------------------------------" + "\n"
//            + " sa       |  "+netunits.trim() + "\n"
//            + " ---------------------------------------------------" + "\n"
//            + " doc     |  "+NetAmount.trim() + "\n"
//            + " ---------------------------------------------------" + "\n"
//            + " add1   |  "+Mktval.trim() + "\n"
//            + " ---------------------------------------------------" + "\n"
//            + " add2   |  "+Profit.trim() + "\n"
//            + " ---------------------------------------------------" + "\n"
//            + " add3   |  "+Profit1.trim() + "\n"
//            + " ---------------------------------------------------" + "\n");
//    t_view.setTextSize(14);   
//    t_view.setTextColor(Color.RED);
   
    
    
 //   s_view.addView(t_view);
    
   // builder1.setView(s_view);
    builder1.setTitle("Status!");
    
    
    
    
 /*   builder1.setTitle("Status...");
    builder1.setMessage(                      "+-------+---------------------+" + "\n"  
                                            + "|POl No | "+productid+ "\n"
    		                                + "|-------|---------------------|" + "\n"
                                            + "|Name   | "+folionumb + "\n"
                                            + "|-------|---------------------|" + "\n"
                                            + "|sa     | "+netunits + "\n"
                                            + "|-------|---------------------|" + "\n"
                                            + "|doc    | "+NetAmount + "\n"
                                            + "|-------|---------------------|" + "\n"
                                            + "|add1   | "+Mktval + "\n"
                                            + "|-------|---------------------|" + "\n"
                                            + "|add2   | "+Profit + "\n"
                                            + "|-------|---------------------|" + "\n"
                                            + "|add3   |"+Profit1 + "\n"
                                            + "+-------+---------------------+" + "\n"); */
    builder1.setCancelable(true);
   
    builder1.setNeutralButton("Continue....",
            new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
            
        }
    });

    AlertDialog alert11 = builder1.create();
    alert11.show();
    	
   /*     Toast.makeText(ActivityExpList.this, "POl No:"+productid+ "\n"
                                            + "Name:"+folionumb + "\n"
                                            + "sa:"+netunits + "\n"
                                            + "doc:"+NetAmount + "\n"
                                            + "add1:"+Mktval + "\n"
                                            + "add2:"+Profit + "\n"
                                            + "add3:"+Profit1,
                                            Toast.LENGTH_LONG).show(); */
        
    } return true;} });                                                                                     
}                                                                                                                     


private ArrayList<Continent> loadSomeData() {                                                                                              
	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
	ArrayList<Country1> countryList = new ArrayList<Country1>();
	   SimpleDateFormat  format = new SimpleDateFormat("yyyy/MM/dd");  
	   
	   
	   
	String sql = "SELECT  plan,count(plan) as cplan FROM bmpolmast  where doc >= '"+format.format(fdate)+ "' and doc <= '"+ format.format(tdate) +"' group by plan ORDER BY plan ";
	System.out.println(sql);
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
			 String GrParent = ds.getString(ds.getColumnIndexOrThrow("plan"));
			 String GrParent1 = ds.getString(ds.getColumnIndexOrThrow("cplan"));
			 Log.d("Response: ", "> " + GrParent);
			
//			 String sql1 = "SELECT _id,policyno,polname,prem,fup,doc FROM bmpolmast where plan = '" + GrParent + "' AND (status  = 'Lapse' OR status = 'paid-up') ORDER by SUBSTR(fup,4,4),SUBSTR(fup,1,2)";
			 String sql1 = "SELECT _id,policyno,polname,prem,fup,doc,status FROM bmpolmast where plan = '" + GrParent + "' ORDER by SUBSTR(fup,4,4),SUBSTR(fup,1,2)";
			 Cursor c = database.rawQuery(sql1, null);
				c.moveToFirst();
				countryList = new ArrayList<Country1>();
			
			
	    if (!c.isAfterLast()){
		
		do{
			 
			  String compcode = c.getString(c.getColumnIndexOrThrow("policyno"));
			  String id = c.getString(c.getColumnIndexOrThrow("polname")).toString();
			  String purchasedfor = c.getString(c.getColumnIndexOrThrow("prem"));
			  String purfrom = c.getString(c.getColumnIndexOrThrow("fup")).toString();
			  String sa = c.getString(c.getColumnIndexOrThrow("status")).toString();
				 try {
					sdate = format1.parse(c.getString(c.getColumnIndexOrThrow("doc")).toString());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  if ((sdate.compareTo(fdate)>=0)&&(sdate.compareTo(tdate)<=0)){
      		          
					  Country1 country = new Country1(id,compcode,purchasedfor,purfrom,sa);
						 
					  countryList.add(country);
	                }
			 
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
	    String groupdisp = GrParent +"("+GrParent1+")";
	     Continent continent = new Continent(groupdisp,countryList);
	     continentList.add(continent);
	  } while (ds.moveToNext()); 
	    }

ds.close();
database.close();

return continentList;
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

public class Revival {

 /*   ArrayList<YlyBean> yitems = new ArrayList<YlyBean>();
	   ArrayList<HlyBean> hitems = new ArrayList<HlyBean>();
	   ArrayList<QlyBean> qitems = new ArrayList<QlyBean>();
	   ArrayList<MlyBean> mitems = new ArrayList<MlyBean>();
//--------------------------------------------------------------------------------------------------------------	
	   yitems = new ArrayList<YlyBean>();
	   yitems.add(new YlyBean(1,1.0));
	   yitems.add(new YlyBean(2,2.0973));
	   yitems.add(new YlyBean(3,3.3012));
	   yitems.add(new YlyBean(4,4.6223));
	   yitems.add(new YlyBean(5,6.0718));
	 
	   hitems = new ArrayList<HlyBean>();
	   hitems.add(new HlyBean(1,1.0));
	   hitems.add(new HlyBean(2,2.0475));
	   hitems.add(new HlyBean(3,3.1448));
	   hitems.add(new HlyBean(4,4.2941));
	   hitems.add(new HlyBean(5,5.4981));
	   hitems.add(new HlyBean(6,6.7593));
	   hitems.add(new HlyBean(7,8.0803));
	   hitems.add(new HlyBean(8,9.4641));
	   hitems.add(new HlyBean(9,10.9137));
	   hitems.add(new HlyBean(10,12.4321));
	   
	   
	   qitems = new ArrayList<QlyBean>();
	   qitems.add(new QlyBean(1,1.0));
	   qitems.add(new QlyBean(2,2.0235));
	   qitems.add(new QlyBean(3,3.0710));
	   qitems.add(new QlyBean(4,4.1431));
	   qitems.add(new QlyBean(5,5.2403));
	   qitems.add(new QlyBean(6,6.3633));
	   qitems.add(new QlyBean(7,7.5127));
	   qitems.add(new QlyBean(8,8.6891));
	   qitems.add(new QlyBean(9,9.8930));
	   qitems.add(new QlyBean(10,11.1253));
	   qitems.add(new QlyBean(11,12.3864));
	   qitems.add(new QlyBean(12,13.6772));
	   qitems.add(new QlyBean(13,14.9983));
	   qitems.add(new QlyBean(14,16.3503));
	   qitems.add(new QlyBean(15,17.7342));
	   qitems.add(new QlyBean(16,19.1505));
	   qitems.add(new QlyBean(17,20.6000));
	   qitems.add(new QlyBean(18,22.0836));
	   qitems.add(new QlyBean(19,23.6020));
	   qitems.add(new QlyBean(20,25.1560));


	       mitems = new ArrayList<MlyBean>();
	       mitems.add(new MlyBean(1,1.0079));
        mitems.add(new MlyBean(2,1.0158));
        mitems.add(new MlyBean(3,1.0238));
        mitems.add(new MlyBean(4,1.0317));
        mitems.add(new MlyBean(5,1.0396));
        mitems.add(new MlyBean(6,1.0475));
        mitems.add(new MlyBean(7,1.0554));
        mitems.add(new MlyBean(8,1.0633));
        mitems.add(new MlyBean(9,1.0713));
        mitems.add(new MlyBean(10,1.0792));
        mitems.add(new MlyBean(11,1.0871));
        mitems.add(new MlyBean(12,1.0950));
        mitems.add(new MlyBean(13,1.1029));
        mitems.add(new MlyBean(14,1.1108));
        mitems.add(new MlyBean(15,1.1188));
        mitems.add(new MlyBean(16,1.1267));
        mitems.add(new MlyBean(17,1.1346));
        mitems.add(new MlyBean(18,1.1425));
        mitems.add(new MlyBean(19,1.1504));
        mitems.add(new MlyBean(20,1.1583));
        mitems.add(new MlyBean(21,1.1663));
        mitems.add(new MlyBean(22,1.1742));
        mitems.add(new MlyBean(23,1.1821));
        mitems.add(new MlyBean(24,1.1900));
        mitems.add(new MlyBean(25,1.1979));
        mitems.add(new MlyBean(26,1.2058));
        mitems.add(new MlyBean(27,1.2138));
        mitems.add(new MlyBean(28,1.2217));
        mitems.add(new MlyBean(29,1.2296));
        mitems.add(new MlyBean(30,1.2375));
        mitems.add(new MlyBean(31,1.2454));
        mitems.add(new MlyBean(32,1.2533));
        mitems.add(new MlyBean(33,1.2613));
        mitems.add(new MlyBean(34,1.2692));
        mitems.add(new MlyBean(35,1.2771));
        mitems.add(new MlyBean(36,1.2850));
        mitems.add(new MlyBean(37,1.2929));
        mitems.add(new MlyBean(38,1.3008));
        mitems.add(new MlyBean(39,1.3088));
        mitems.add(new MlyBean(40,1.3167));
        mitems.add(new MlyBean(41,1.3242));
        mitems.add(new MlyBean(42,1.3325));
        mitems.add(new MlyBean(43,1.3404));
        mitems.add(new MlyBean(44,1.3483));
        mitems.add(new MlyBean(45,1.3563));
        mitems.add(new MlyBean(46,1.3642));
        mitems.add(new MlyBean(47,1.3721));
        mitems.add(new MlyBean(48,1.3800));
        mitems.add(new MlyBean(49,1.3879));
        mitems.add(new MlyBean(50,1.3958));
        mitems.add(new MlyBean(51,1.4038));
        mitems.add(new MlyBean(52,1.4117));
        mitems.add(new MlyBean(53,1.4196));
        mitems.add(new MlyBean(54,1.4275));
        mitems.add(new MlyBean(55,1.4354));
        mitems.add(new MlyBean(56,1.4433));
        mitems.add(new MlyBean(57,1.4513));
        mitems.add(new MlyBean(58,1.4592));
        mitems.add(new MlyBean(59,1.4671));
        mitems.add(new MlyBean(60,1.4750)); 
//----------------------------------INPUT--------------------------------------------------------------------------
 	//	   String DOC = cursor.getString(cursor.getColumnIndexOrThrow("doc"));
 	//	   String Mode = cursor.getString(cursor.getColumnIndexOrThrow("mode"));
   //         String Type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
  //          String FUP = cursor.getString(cursor.getColumnIndexOrThrow("fup"));  
  //          String  Prem = cursor.getString(cursor.getColumnIndexOrThrow("prem"));    
//-------------------------------------------------------------------------------------------------------------------
             int no_of_inst; 
             int tot_prem;
             double late_fee;
             double tot_rev_amt;
           	String datetime;
           	String datetimedd;
           	Date date;
           	String FUP1;
           	String datetimetoday;
           	DateTime FUP1cdate;
           	int mode_days;
           	double rev_factor = 1.0;
//-----------------------------------------------------------------------------------------------------------------             	 
 	//	   String dtStart = DOC;  
 		   SimpleDateFormat  format = new SimpleDateFormat("yyyy/MM/dd");  
 		   try {  
 	//	        date = format.parse(dtStart);  
 		       System.out.println(date);  
 		   } catch (ParseException e0) {  
 		       // TODO Auto-generated catch block  
 		       e0.printStackTrace();  
 		   } catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		  

 		   
 		   SimpleDateFormat  dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
 		   try {  
 		     Date date1 = date;  
 		     datetime = dateformat.format(date1);
 		     datetimedd = datetime.substring(0,2);
 		     datetimetoday = dateformat.format(new Date());
 		    System.out.println("Current Date Time : " + datetime); 
 		   } catch (ParseException e1) {  
 		       // TODO Auto-generated catch block  
 		       e1.printStackTrace();  
 		   }
 		   FUP1 = datetimedd+"/"+FUP+" 00:00:00";
 		
                      SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                      try {
          				FUP1cdate = new DateTime(format1.parse(FUP1));
          			} catch (java.text.ParseException e2) {
          				// TODO Auto-generated catch block
          				e2.printStackTrace();
          			}  
                      DateTime test = new DateTime();
        String no_of_days =  String.valueOf(Days.daysBetween(FUP1cdate,test).getDays());
        if (Integer.parseInt(no_of_days)<=30){
     // to do	
     	   showAlertView(no_of_days);
        }
        if (Integer.parseInt(no_of_days)>=(365*5)){
     //to do	   
     	   	   showAlertView(no_of_days);
        }
        
        
        int no_of_years = Years.yearsBetween(FUP1cdate, test).getYears();

        if (Mode.equals("Y")){
     	    mode_days = 365;
        }else if (Mode.equals("H")){
     	    mode_days = 180;
        }else if (Mode.equals("Q")){
     	    mode_days = 90;
        }else if (Mode.equals("M")){
     	    mode_days = 30;
        }

//          System.out.println(Integer.parseInt(no_of_days));
        no_of_inst = (Integer.parseInt(no_of_days)/(mode_days))+1;   
        
        if (Mode.equals("Y")){
    	   for (int j = 0; j < yitems.size(); j++){
    		   if (yitems.get(j).getinst()==(no_of_inst)){
     	 rev_factor = yitems.get(j).getrevfactor();
     	 //	   System.out.println(no_of_inst+"------"+yitems.get(j).getinst()+"-------"+yitems.get(j).getrevfactor());
        }	   
    	   }
        }
       
        else if (Mode.equals("H")){
     	  for (int j = 0; j < hitems.size(); j++){
       		   if (hitems.get(j).getinst() == no_of_inst){
        	 rev_factor = hitems.get(j).getrevfactor();
        }
     	  }
       }
        else if (Mode.equals("Q")){
      	  for (int j = 0; j < qitems.size(); j++){
        		   if (qitems.get(j).getinst() == no_of_inst){
         	 rev_factor = qitems.get(j).getrevfactor();
         }
      	  }
        }
        else if (Mode.equals("M") && Type.equals("O")){
       	  for (int j = 0; j < mitems.size(); j++){
         		   if (mitems.get(j).getinst() == no_of_inst){
          	 rev_factor = mitems.get(j).getrevfactor();
          }
       	  }
         }
        else if (Mode.equals("M") && Type.equals("S")){
// to do
   //  	   Toast.makeText(Revival.this, " is SSS" + "Contact PS Dept", Toast.LENGTH_SHORT).show();
     	
          }
        
        
        
        
        
        tot_prem =   no_of_inst*Integer.parseInt(Prem);
        

         late_fee = (double) Integer.parseInt(Prem) * rev_factor; 
        
        int r_days = Integer.parseInt(no_of_days) - (no_of_years *365);
        System.out.println(r_days);
        int remainder = r_days % 30; 
        if (remainder > 14){
     	   r_days = r_days -remainder + 30;
     	   }else {
     		   r_days = r_days - remainder;
     	   }
        
        System.out.println(r_days);
        
        double late_fee_r_days = (late_fee)*9.5*r_days/(100*365);
        late_fee += late_fee_r_days-tot_prem;
         tot_rev_amt = tot_prem + late_fee;
                 Textv2.setText(PolicyNo);
                 Textv4.setText(PlanNo); 
                 Textv6.setText(datetimetoday); 
                 Textv8.setText(FUP1);
                 Textv10.setText(String.valueOf(no_of_inst));
                 Textv12.setText(Prem);
                 Textv14.setText(String.valueOf(tot_prem));
                 Textv16.setText("9.5");
                 Textv18.setText(String.valueOf(late_fee));
                 Textv20.setText(String.valueOf(tot_rev_amt));
             
                 cursor.close();
               database.close();
                stockSQLHelper.close();
          
             
             //DO something

          
           	  	 
 private void showAlertView(String str) {
		AlertDialog alert = new AlertDialog.Builder(ActivityExpList.this).create();
	//	if (TextUtils.isEmpty(str)) {
		if (Integer.parseInt(str)<=30){
			alert.setTitle("Revival");
			alert.setMessage("With in Grace Period!!!");
		} else if (Integer.parseInt(str)>=(365*5)){
			// Remove , end of the name
//			String strContactList = str.substring(0, str.length() - 1);

			alert.setTitle("Revival");
			alert.setMessage("Cannot be revivied Lapsed more than 5 years");
		}
		

 }
 }*/
} 
}





































                                                                                                                      
                                             
