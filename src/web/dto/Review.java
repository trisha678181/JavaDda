package web.dto;

public class Review { 
	
	private int rno;
	private int pno;
	private int uno;
	private String rvcontent;
	private String rvdate;
	private int rvstar;
	@Override
	public String toString() {
		return "Review [rno=" + rno + ", pno=" + pno + ", uno=" + uno + ", rvcontent=" + rvcontent + ", rvdate="
				+ rvdate + ", rvstar=" + rvstar + "]";
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getRvcontent() {
		return rvcontent;
	}
	public void setRvcontent(String rvcontent) {
		this.rvcontent = rvcontent;
	}
	public String getRvdate() {
		return rvdate;
	}
	public void setRvdate(String rvdate) {
		this.rvdate = rvdate;
	}
	public int getRvstar() {
		return rvstar;
	}
	public void setRvstar(int rvstar) {
		this.rvstar = rvstar;
	}
	
	

}
