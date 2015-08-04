package com.sreekanth.duelist;


import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
public class BmListAdapter extends BaseExpandableListAdapter {                                                             
private Context context;                                                                                                   
private ArrayList<Continent1> Continent1List;                                                                                
private ArrayList<Continent1> originalList;                                                                                 

public BmListAdapter(Context context, ArrayList<Continent1> Continent1List) {                                                
this.context = context;                                                                                                    
this.Continent1List = new ArrayList<Continent1>();                                                                           
this.Continent1List.addAll(Continent1List);                                                                                  
this.originalList = new ArrayList<Continent1>();                                                                            
this.originalList.addAll(Continent1List);                                                                                   
}                                                                                                                          

@Override                                                                                                                  
public Object getChild(int groupPosition, int childPosition) {                                                             
ArrayList<Country2> countryList = Continent1List.get(groupPosition).getCountryList();                                        
return countryList.get(childPosition);                                                                                     
}                                                                                                                          
@Override                                                                                                                  
public long getChildId(int groupPosition, int childPosition) {                                                             
return childPosition;                                                                                                      
}                                                                                                                          
@Override                                                                                                                  
public View getChildView(int groupPosition, int childPosition, boolean isLastChild,                                        
View view, ViewGroup parent) {                                                                                             

Country2 country = (Country2) getChild(groupPosition, childPosition);                                                        
if (view == null) {                                                                                                        
LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);                
view = layoutInflater.inflate(R.layout.list, null);                                                                   
}                                                                                                                          

TextView code = (TextView) view.findViewById(R.id.TextView1);                                                                   
TextView name = (TextView) view.findViewById(R.id.TextView2);                                                                   
TextView population = (TextView) view.findViewById(R.id.TextView16);  
TextView purfrom = (TextView) view.findViewById(R.id.TextView3);
TextView sumass = (TextView)view.findViewById(R.id.TextView4a);
TextView dueto = (TextView)view.findViewById(R.id.TextView4);
TextView mode = (TextView)view.findViewById(R.id.TextView5);
TextView bname = (TextView)view.findViewById(R.id.TextView6);
TextView add1 = (TextView)view.findViewById(R.id.TextView7);
TextView add2 = (TextView)view.findViewById(R.id.TextView8);
TextView add3 = (TextView)view.findViewById(R.id.TextView9);
TextView nfdues = (TextView)view.findViewById(R.id.TextView19);

code.setText(country.getCode().trim());                                                                                    
name.setText(country.getName().trim());                                                                                    
//population.setText(NumberFormat.getNumberInstance(Locale.US).format(country.getPopulation()));                             
population.setText(country.getPopulation().trim());
purfrom.setText(country.getpurfrom().trim());
sumass.setText(country.getsa().trim());
dueto.setText(country.getdueto());
add1.setText(country.getadd1());
add2.setText(country.getadd2());
add3.setText(country.getadd3());
bname.setText(country.getbname());
mode.setText(country.getmode());
nfdues.setText(country.getnfdues());
return view;                                                                                                               
}                                                                                                                          
@Override                                                                                                                  
public int getChildrenCount(int groupPosition) {                                                                           

ArrayList<Country2> countryList = Continent1List.get(groupPosition).getCountryList();                                        
return countryList.size();                                                                                                 
}                                                                                                                          
@Override                                                                                                                  
public Object getGroup(int groupPosition) {                                                                                
return Continent1List.get(groupPosition);                                                                                   
}                                                                                                                          
@Override                                                                                                                  
public int getGroupCount() {                                                                                               
return Continent1List.size();                                                                                               
}                                                                                                                          
@Override                                                                                                                  
public long getGroupId(int groupPosition) {                                                                                
return groupPosition;                                                                                                      
}                                                                                                                          
@Override                                                                                                                  
public View getGroupView(int groupPosition, boolean isLastChild, View view,                                                
ViewGroup parent) {                                                                                                        

Continent1 Continent1 = (Continent1) getGroup(groupPosition);                                                                 
if (view == null) {                                                                                                        
LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);                
view = layoutInflater.inflate(R.layout.group_row, null);                                                                   
}                                                                                                                          

TextView heading = (TextView) view.findViewById(R.id.heading);                                                             
heading.setText(Continent1.getName().trim());                                                                               

return view;                                                                                                               
}                                                                                                                          
@Override                                                                                                                  
public boolean hasStableIds() {                                                                                            
return true;                                                                                                               
}                                                                                                                          
@Override                                                                                                                  
public boolean isChildSelectable(int groupPosition, int childPosition) {                                                   
return true;                                                                                                               
}                                                                                                                          

public void filterData(String query){                                                                                      
	 ArrayList<Country2> newList1 = new ArrayList<Country2>();  
query = query.toLowerCase();                                                                                               
Log.v("MyListAdapter", String.valueOf(Continent1List.size()));                                                              
Continent1List.clear();                                                                                                     
Float totnetunits=(float) 0;
Float totunits=(float)0;
Float totnetamount=(float) 0;
Float totprofit = (float)0;
if(query.isEmpty()){                                                                                                       
Continent1List.addAll(originalList);  
SubTotal();
}                                                                                                                          
else {                                                                                                                     

for(Continent1 Continent1: originalList){                                                                                    

ArrayList<Country2> countryList = Continent1.getCountryList();                                                               
ArrayList<Country2> newList = new ArrayList<Country2>();                                                                     
for(Country2 country: countryList){                                                                                         
if(country.getCode().toLowerCase().contains(query) ||                                                                      
country.getName().toLowerCase().contains(query)){                                                                          
newList.add(country);      
//totnetunits = totnetunits+Float.parseFloat(country.getPopulation());
totunits++;
}                                                                                                                       
}   

if(newList.size() > 0){     
//	newList.add(new Country2(" "," ",String.valueOf(totnetunits)," ","")); 
Continent1 nContinent1 = new Continent1(Continent1.getName(),newList);                                                         
Continent1List.add(nContinent1);  
totnetunits=(float) 0;
}                                                                                                                          
}  

}                                                                                                                          
newList1.add(new Country2(" "," ",String.valueOf(totunits)," "," "," "," "," "," "," "," "," "));     
Continent1 nContinent1 = new Continent1("GR.Total",newList1);                                                         
Continent1List.add(nContinent1);  
Log.v("MyListAdapter", String.valueOf(Continent1List.size()));                                                              
notifyDataSetChanged();                                                                                                    

}       
public void SubTotal(){                                                                                      
	Float totnetunits=(float) 0;
	Float totunits = (float)0;
    Float totnetamount=(float) 0;
    Float totprofit = (float)0;
    Continent1List.clear();    
    ArrayList<Country2> newList1 = new ArrayList<Country2>();  
for(Continent1 Continent1: originalList){                                                                                    

ArrayList<Country2> countryList = Continent1.getCountryList();                                                               
ArrayList<Country2> newList = new ArrayList<Country2>();                                                                     
for(Country2 country: countryList){                                                                                         
//	totnetunits = totnetunits+Float.parseFloat(country.getPopulation());
	totunits++;
//	totnetamount = totnetamount + c.getFloat(c.getColumnIndexOrThrow("NetAmount"));
//	totprofit = totprofit +c.getFloat(c.getColumnIndexOrThrow("Profit"));                                                                     
newList.add(country);                                                                                                      
}                                                                                                                          

//newList.add(new Country2(" "," ",String.valueOf(totnetunits)," "," "));                                                                                                                         
if(newList.size() > 0){                                                                                                    
Continent1 nContinent1 = new Continent1(Continent1.getName(),newList);                                                         
Continent1List.add(nContinent1);                                                                                             
                                                                                                                         
}   
 totnetunits=(float) 0;
}                                                                                                                          
newList1.add(new Country2(" "," ",String.valueOf(totunits)," "," "," "," "," "," "," "," "," "));     
Continent1 nContinent1 = new Continent1("GR.Total",newList1);                                                         
Continent1List.add(nContinent1);  
Log.v("MyListAdapter", String.valueOf(Continent1List.size()));                                                              
notifyDataSetChanged();                                                                                                    

}         

}                                                 