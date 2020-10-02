package web.dto;

public class Notice {
	
	private int noticeno; // 공지사항 번호(시퀀스)
	private int ano;// 사업자 번호
	private String title;// 공지사항 제목
	private String ntcontent;// 공지사항 내용
	private String ntdate; // 업로드 날짜
	private int hit;
	
	@Override
	public String toString() {
		return "Notice [noticeno=" + noticeno + ", ano=" + ano + ", title=" + title + ", ntcontent=" + ntcontent
				+ ", ntdate=" + ntdate + ", hit=" + hit + "]";
	}

	public int getNoticeno() {
		return noticeno;
	}

	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNtcontent() {
		return ntcontent;
	}

	public void setNtcontent(String ntcontent) {
		this.ntcontent = ntcontent;
	}

	public String getNtdate() {
		return ntdate;
	}

	public void setNtdate(String ntdate) {
		this.ntdate = ntdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	

}
