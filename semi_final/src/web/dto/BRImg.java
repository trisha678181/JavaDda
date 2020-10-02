package web.dto;

public class BRImg {
	private int brino;
	private int brno;
	private String originName;
	private String storeName;
	private int fileSize;
	
	@Override
	public String toString() {
		return "BRImg [brino=" + brino + ", brno=" + brno + ", originName=" + originName + ", storeName=" + storeName
				+ ", fileSize=" + fileSize + "]";
	}

	public int getBrino() {
		return brino;
	}

	public void setBrino(int brino) {
		this.brino = brino;
	}

	public int getBrno() {
		return brno;
	}

	public void setBrno(int brno) {
		this.brno = brno;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	
		
	
}
