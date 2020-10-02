package web.dto;

public class NTComment {

	private int ntcmno;// 공지사항 리뷰번호(시퀀스)
	private int noticeno;// 공지사항 번호 ex) ?번호의 공지사항
	private int uno; // 회원 번호
	private String nt_con_content;// 리뷰 내용
	private String nt_con_date;// 리뷰 작성 날짜
	
	public int getNtcmno() {
		return ntcmno;
	}
	public void setNtcmno(int ntcmno) {
		this.ntcmno = ntcmno;
	}
	public int getNoticeno() {
		return noticeno;
	}
	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getNt_con_content() {
		return nt_con_content;
	}
	public void setNt_con_content(String nt_con_content) {
		this.nt_con_content = nt_con_content;
	}
	public String getNt_con_date() {
		return nt_con_date;
	}
	public void setNt_con_date(String nt_con_date) {
		this.nt_con_date = nt_con_date;
	}
	
	@Override
	public String toString() {
		return "NTComment [ntcmno=" + ntcmno + ", noticeno=" + noticeno + ", uno=" + uno + ", nt_con_content="
				+ nt_con_content + ", nt_con_date=" + nt_con_date + "]";
	}
	

}
