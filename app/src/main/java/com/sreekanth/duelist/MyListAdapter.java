package com.sreekanth.duelist;


import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
public class MyListAdapter extends BaseExpandableListAdapter {                                                             
private Context context;                                                                                                   
private ArrayList<Continent> continentList;                                                                                
private ArrayList<Continent> originalList;                                                                                 

public MyListAdapter(Context context, ArrayList<Continent> continentList) {                                                
this.context = context;                                                                                                    
this.continentList = new ArrayList<Continent>();                                                                           
this.continentList.addAll(continentList);                                                                                  
this.originalList = new ArrayList<Continent>();                                                                            
this.originalList.addAll(continentList);                                                                                   
}                                                                                                                          

@Override                                                                                                                  
public Object getChild(int groupPosition, int childPosition) {                                                             
ArrayList<Country1> countryList = continentList.get(groupPosition).getCountryList();                                        
return countryList.get(childPosition);                                                                                     
}                                                                                                                          
@Override                                                                                                                  
public long getChildId(int groupPosition, int childPosition) {                                                             
return childPosition;                                                                                                      
}                                                                                                                          
@Override                                                                                                                  
public View getChildView(int groupPosition, int childPosition, boolean isLastChild,                                        
View view, ViewGroup parent) {                                                                                             

Country1 country = (Country1) getChild(groupPosition, childPosition);                                                        
if (view == null) {                                                                                                        
LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);                
view = layoutInflater.inflate(R.layout.child_row, null);                                                                   
}                                                                                                                          

TextView code = (TextView) view.findViewById(R.id.code);                                                                   
TextView name = (TextView) view.findViewById(R.id.name);                                                                   
TextView population = (TextView) view.findViewById(R.id.population);  
TextView purfrom = (TextView) view.findViewById(R.id.purfrom);
TextView sumass = (TextView)view.findViewById(R.id.sa);
TextView revamt = (TextView)view.findViewById(R.id.revamount);
revamt.setText("Rev Amount");
code.setText(country.getCode().trim());                                                                                    
name.setText(country.getName().trim());                                                                                    
//population.setText(NumberFormat.getNumberInstance(Locale.US).format(country.getPopulation()));                             
population.setText(country.getPopulation().trim());
purfrom.setText(country.getpurfrom().trim());
sumass.setText(country.getsa().trim());
return view;                                                                                                               
}                                                                                                                          
@Override                                                                                                                  
public int getChildrenCount(int groupPosition) {                                                                           

ArrayList<Country1> countryList = continentList.get(groupPosition).getCountryList();                                        
return countryList.size();                                                                                                 
}                                                                                                                          
@Override                                                                                                                  
public Object getGroup(int groupPosition) {                                                                                
return continentList.get(groupPosition);                                                                                   
}                                                                                                                          
@Override                                                                                                                  
public int getGroupCount() {                                                                                               
return continentList.size();                                                                                               
}                                                                                                                          
@Override                                                                                                                  
public long getGroupId(int groupPosition) {                                                                                
return groupPosition;                                                                                                      
}                                                                                                                          
@Override                                                                                                                  
public View getGroupView(int groupPosition, boolean isLastChild, View view,                                                
ViewGroup parent) {                                                                                                        

Continent continent = (Continent) getGroup(groupPosition);                                                                 
if (view == null) {                                                                                                        
LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);                
view = layoutInflater.inflate(R.layout.group_row, null);                                                                   
}                                                                                                                          

TextView heading = (TextView) view.findViewById(R.id.heading);                                                             
heading.setText(continent.getName().trim());                                                                               

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
	 ArrayList<Country1> newList1 = new ArrayList<Country1>();  
query = query.toLowerCase();                                                                                               
Log.v("MyListAdapter", String.valueOf(continentList.size()));                                                              
continentList.clear();                                                                                                     
Float totnetunits=(float) 0;
Float totunits=(float)0;
Float totnetamount=(float) 0;
Float totprofit = (float)0;
if(query.isEmpty()){                                                                                                       
continentList.addAll(originalList);  
SubTotal();
}                                                                                                                          
else {                                                                                                                     

for(Continent continent: originalList){                                                                                    

ArrayList<Country1> countryList = continent.getCountryList();                                                               
ArrayList<Country1> newList = new ArrayList<Country1>();                                                                     
for(Country1 country: countryList){                                                                                         
if(country.getCode().toLowerCase().contains(query) ||                                                                      
country.getName().toLowerCase().contains(query)){                                                                          
newList.add(country);      
//totnetunits = totnetunits+Float.parseFloat(country.getPopulation());
totunits++;
}                                                                                                                       
}   

if(newList.size() > 0){     
//	newList.add(new Country1(" "," ",String.valueOf(totnetunits)," ","")); 
Continent nContinent = new Continent(continent.getName(),newList);                                                         
continentList.add(nContinent);  
totnetunits=(float) 0;
}                                                                                                                          
}  

}                                                                                                                          
newList1.add(new Country1(" "," ",String.valueOf(totunits)," "," "));     
Continent nContinent = new Continent("GR.Total",newList1);                                                         
continentList.add(nContinent);  
Log.v("MyListAdapter", String.valueOf(continentList.size()));                                                              
notifyDataSetChanged();                                                                                                    

}       
public void SubTotal(){                                                                                      
	Float totnetunits=(float) 0;
	Float totunits = (float)0;
    Float totnetamount=(float) 0;
    Float totprofit = (float)0;
    continentList.clear();    
    ArrayList<Country1> newList1 = new ArrayList<Country1>();  
for(Continent continent: originalList){                                                                                    

ArrayList<Country1> countryList = continent.getCountryList();                                                               
ArrayList<Country1> newList = new ArrayList<Country1>();                                                                     
for(Country1 country: countryList){                                                                                         
//	totnetunits = totnetunits+Float.parseFloat(country.getPopulation());
	totunits++;
//	totnetamount = totnetamount + c.getFloat(c.getColumnIndexOrThrow("NetAmount"));
//	totprofit = totprofit +c.getFloat(c.getColumnIndexOrThrow("Profit"));                                                                     
newList.add(country);                                                                                                      
}                                                                                                                          

//newList.add(new Country1(" "," ",String.valueOf(totnetunits)," "," "));                                                                                                                         
if(newList.size() > 0){                                                                                                    
Continent nContinent = new Continent(continent.getName(),newList);                                                         
continentList.add(nContinent);                                                                                             
                                                                                                                         
}   
 totnetunits=(float) 0;
}                                                                                                                          
newList1.add(new Country1(" "," ",String.valueOf(totunits)," "," "));     
Continent nContinent = new Continent("GR.Total",newList1);                                                         
continentList.add(nContinent);  
Log.v("MyListAdapter", String.valueOf(continentList.size()));                                                              
notifyDataSetChanged();                                                                                                    

}         

}                                                 