package web.dto;

public class LikePlace {
	
	private int uno;
	private int pno;
	private String together;

	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	
	public String getTogether() {
		return together;
	}
	public void setTogether(String together) {
		this.together = together;
	}
	@Override
	public String toString() {
		return "LikePlace [uno=" + uno + ", pno=" + pno + ", together=" + together + "]";
	}
	
	

}
