package com.sreekanth.duelist;

import java.util.ArrayList;
public class Continent {                                                                                                   

private String name;                                                                                                       
private ArrayList<Country1> countryList = new ArrayList<Country1>();                                                         

public Continent(String name, ArrayList<Country1> countryList) {                                                            
super();                                                                                                                   
this.name = name;                                                                                                          
this.countryList = countryList;                                                                                            
}                                                                                                                          
public String getName() {                                                                                                  
return name;                                                                                                               
}                                                                                                                          
public void setName(String name) {                                                                                         
this.name = name;                                                                                                          
}                                                                                                                          
public ArrayList<Country1> getCountryList() {                                                                               
return countryList;                                                                                                        
}                                                                                                                          
public void setCountryList(ArrayList<Country1> countryList) {                                                               
this.countryList = countryList;                                                                                            
};                                                                                                                         

}                                               