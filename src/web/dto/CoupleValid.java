package web.dto;

public class CoupleValid {
	
	private int wno;
	private int mno;
	private String couplevalid;
	private String firstday;
	private int requiredno;
	
	public int getWno() {
		return wno;
	}
	public void setWno(int wno) {
		this.wno = wno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getCouplevalid() {
		return couplevalid;
	}
	public void setCouplevalid(String couplevalid) {
		this.couplevalid = couplevalid;
	}
	
	public String getFirstday() {
		return firstday;
	}
	public void setFirstday(String firstday) {
		this.firstday = firstday;
	}
	
	
	public int getRequiredno() {
		return requiredno;
	}
	public void setRequiredno(int requiredno) {
		this.requiredno = requiredno;
	}
	@Override
	public String toString() {
		return "CoupleValid [wno=" + wno + ", mno=" + mno + ", couplevalid=" + couplevalid + ", firstday=" + firstday
				+ ", requiredno=" + requiredno + "]";
	}
	
	
	
	

}
