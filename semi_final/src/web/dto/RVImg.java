package web.dto;

public class RVImg {

	private int rino;
	private int rno;
	private String originname;
	private String storedname;
	private int filesize;
	
	@Override
	public String toString() {
		return "RVImg [rino=" + rino + ", rno=" + rno + ", originname=" + originname + ", storedname=" + storedname
				+ ", filesize=" + filesize + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public int getRino() {
		return rino;
	}

	public void setRino(int rino) {
		this.rino = rino;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
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

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	
}
