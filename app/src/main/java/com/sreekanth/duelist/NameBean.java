package com.sreekanth.duelist;

public class NameBean {
    private String id;
	private String date;
	private String itype;
	private String otype;
	private String parti;
	private String amount;
	private String prodid;
	private String compcode;
	private boolean selected;
	
	
	 public NameBean(String mid,String i,String it,String ot,String pt,String amt,String pid,String cc,Boolean s) {
		    id=mid;
	    	date= i;
	    	itype=it;
	    	otype=ot;
	    	parti=pt;
	    	amount=amt;
	    	prodid=pid;
	    	compcode=cc;	    			
	        selected = s;}
	 
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    public String getItype() {
    	return itype;
    }
    public void setItype(String itype){
    	this.itype  = itype;
    }
    public String getOtype(){
    	return otype;
    }
	public void setOtype(String otype){
		this.otype=otype;
	}
	public String getParti(){
		return parti;
	}
	public void setparti(String parti){
		this.parti=parti;
	}
	public String getAmount(){
		return amount;
	}
	public void setAmount(String amount){
		this.amount=amount;
	}
	public String getProdid(){
		return prodid;
	}
	public void setProdid(String prodid){
		this.prodid=prodid;
	}
	public String getCompcode(){
		return compcode;
	}
	public void setCompcode(String compcode){
		this.compcode=compcode;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}