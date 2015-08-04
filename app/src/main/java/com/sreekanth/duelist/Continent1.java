package com.sreekanth.duelist;

import java.util.ArrayList;
public class Continent1 {                                                                                                   

private String name;                                                                                                       
private ArrayList<Country2> countryList = new ArrayList<Country2>();                                                         

public Continent1(String name, ArrayList<Country2> countryList) {                                                            
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
public ArrayList<Country2> getCountryList() {                                                                               
return countryList;                                                                                                        
}                                                                                                                          
public void setCountryList(ArrayList<Country2> countryList) {                                                               
this.countryList = countryList;                                                                                            
};                                                                                                                         

}                                               