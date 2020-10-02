package web.dto;

public class HaveCoupon {
	
	private int hcpno; 
	private int cpno; //쿠폰번호
	private int uno; //회원번호
	private String cpstate;
	
	@Override
	public String toString() {
		return "HaveCoupon [hcpno=" + hcpno + ", cpno=" + cpno + ", uno=" + uno + ", cpstate=" + cpstate + "]";
	}

	public int getHcpno() {
		return hcpno;
	}

	public void setHcpno(int hcpno) {
		this.hcpno = hcpno;
	}

	public int getCpno() {
		return cpno;
	}

	public void setCpno(int cpno) {
		this.cpno = cpno;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getCpstate() {
		return cpstate;
	}

	public void setCpstate(String cpstate) {
		this.cpstate = cpstate;
	}
	
	
	
	

}
