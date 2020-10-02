package web.dto;

public class PImg {
	
    private int pino; 
    private int pno;
    private int pcode; //1-가게이미지, 2-메뉴이미지, 3-대표이미지
    private String originName;
    private String storedName;
    private int filesize;
    
	public int getPino() {
		return pino;
	}
	public void setPino(int pino) {
		this.pino = pino;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	
	
	
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getStoredName() {
		return storedName;
	}
	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	@Override
	public String toString() {
		return "PImg [pino=" + pino + ", pno=" + pno + ", pcode=" + pcode + ", originName=" + originName
				+ ", storedName=" + storedName + ", filesize=" + filesize + "]";
	}
	
	
	
    
    
    

}
