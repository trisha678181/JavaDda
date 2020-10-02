package web.dto;

public class QnA {
	private int qnano;// qna번호 (시퀀스)
	private int bno;// 사업자 번호(시퀀스)
	private String title;// 문의 제목
	private String qcontent;// 문의 내용
	private String qdate;// 문의 날짜
	public int getQnano() {
		return qnano;
	}
	public void setQnano(int qnano) {
		this.qnano = qnano;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getQdate() {
		return qdate;
	}
	public void setQdate(String qdate) {
		this.qdate = qdate;
	}
	@Override
	public String toString() {
		return "QnA [qnano=" + qnano + ", bno=" + bno + ", title=" + title + ", qcontent=" + qcontent + ", qdate="
				+ qdate + "]";
	}
	
	  
	

}
