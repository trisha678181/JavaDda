package web.dto;

public class Couple {
	
	private int cno;
	private int wno;
	private int mno;
	private String firstday;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
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
	public String getFirstday() {
		return firstday;
	}
	public void setFirstday(String firstday) {
		this.firstday = firstday;
	}
	@Override
	public String toString() {
		return "Couple [cno=" + cno + ", wno=" + wno + ", mno=" + mno + ", firstday=" + firstday + "]";
	}
	
	

}
