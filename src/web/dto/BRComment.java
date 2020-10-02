package web.dto;

import java.util.Date;

public class BRComment {
	private int brcmno;
	private int brno;
	private int uno;
	private String brcontent;
	private String brdate;
	
	@Override
	public String toString() {
		return "BRComment [brcmno=" + brcmno + ", brno=" + brno + ", uno=" + uno + ", brcontent=" + brcontent
				+ ", brdate=" + brdate + "]";
	}

	public int getBrcmno() {
		return brcmno;
	}

	public void setBrcmno(int brcmno) {
		this.brcmno = brcmno;
	}

	public int getBrno() {
		return brno;
	}

	public void setBrno(int brno) {
		this.brno = brno;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getBrcontent() {
		return brcontent;
	}

	public void setBrcontent(String brcontent) {
		this.brcontent = brcontent;
	}

	public String getBrdate() {
		return brdate;
	}

	public void setBrdate(String brdate) {
		this.brdate = brdate;
	}
	
}
