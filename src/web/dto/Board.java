package web.dto;

import java.util.Date;

public class Board {
	private int brno;
	private int uno;
	private String brtitle;
	private String brcontent;
	private String brdate;
	private int brviewcnt;
	private int brlike;
	
	@Override
	public String toString() {
		return "Board [brno=" + brno + ", uno=" + uno + ", brtitle=" + brtitle + ", brcontent=" + brcontent
				+ ", brdate=" + brdate + ", brviewcnt=" + brviewcnt + ", brlike=" + brlike + "]";
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

	public String getBrtitle() {
		return brtitle;
	}

	public void setBrtitle(String brtitle) {
		this.brtitle = brtitle;
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

	public int getBrviewcnt() {
		return brviewcnt;
	}

	public void setBrviewcnt(int brviewcnt) {
		this.brviewcnt = brviewcnt;
	}

	public int getBrlike() {
		return brlike;
	}

	public void setBrlike(int brlike) {
		this.brlike = brlike;
	}
	
	
		
	
		
}
