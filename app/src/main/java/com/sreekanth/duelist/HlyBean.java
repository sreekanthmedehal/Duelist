package com.sreekanth.duelist;

public class HlyBean {
    private int no_inst;
	private Double rev_factor;
	
	
	
	 public HlyBean(int noi,Double rf) {
		    no_inst=noi;
	    	rev_factor=rf;
	    	}
	 
	public int getinst(){
		return no_inst;
	}
	public void setinst(int no_inst){
		this.no_inst=no_inst;
	}
	public Double getrevfactor() {
		return rev_factor;
	}

	public void setrevfactor(Double rev_factor) {
		this.rev_factor = rev_factor;
	}
    
}