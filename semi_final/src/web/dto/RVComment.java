package web.dto;

public class RVComment {

	private int rvcmno;// 리뷰 리뷰댓글번호(시퀀스)
	private int rno;// 리뷰 번호 ex) ?번호의 리뷰
	private int uno; // 회원 번호
	private String rv_com_content;// 리뷰 내용
	private String rv_com_date;// 리뷰 작성 날짜
	public int getRvcmno() {
		return rvcmno;
	}
	public void setRvcmno(int rvcmno) {
		this.rvcmno = rvcmno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getRv_com_content() {
		return rv_com_content;
	}
	public void setRv_com_content(String rv_con_content) {
		this.rv_com_content = rv_con_content;
	}
	public String getRv_com_date() {
		return rv_com_date;
	}
	public void setRv_com_date(String rv_con_date) {
		this.rv_com_date = rv_con_date;
	}
	@Override
	public String toString() {
		return "RVComment [rvcmno=" + rvcmno + ", rno=" + rno + ", uno=" + uno + ", rv_com_content=" + rv_com_content
				+ ", rv_com_date=" + rv_com_date + "]";
	}
	
	
	
}
