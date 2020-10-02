package web.dto;

public class Coupon {
	
    private int cpno;
    private int pno; 
    private String originname; 
    private String storedname; 
    private String expdate;
    private int filesize;
    
    
	public int getCpno() {
		return cpno;
	}
	public void setCpno(int cpno) {
		this.cpno = cpno;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getOriginname() {
		return originname;
	}
	public void setOriginname(String originname) {
		this.originname = originname;
	}
	public String getStoredname() {
		return storedname;
	}
	public void setStoredname(String storedname) {
		this.storedname = storedname;
	}
	public String getExpdate() {
		return expdate;
	}
	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}	
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	@Override
	public String toString() {
		return "Coupon [cpno=" + cpno + ", pno=" + pno + ", originname=" + originname + ", storedname=" + storedname
				+ ", expdate=" + expdate + ", filesize=" + filesize + "]";
	}

	
    
    
    
}
