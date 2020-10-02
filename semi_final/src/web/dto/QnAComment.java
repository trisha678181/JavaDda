package web.dto;

public class QnAComment {
	private int qnacmno;//QnA리뷰 번호(시퀀스)
	private int qnano;//QnA번호
	private int ano;// 관리자 번호
	private String qna_con_content;//리뷰 내용
	private String qna_con_date; //리뷰 작성 날짜
	
	@Override
	public String toString() {
		return "QnAComment [qnacmno=" + qnacmno + ", qnano=" + qnano + ", ano=" + ano + ", qna_con_content="
				+ qna_con_content + ", qna_con_date=" + qna_con_date + "]";
	}

	public int getQnacmno() {
		return qnacmno;
	}

	public void setQnacmno(int qnacmno) {
		this.qnacmno = qnacmno;
	}

	public int getQnano() {
		return qnano;
	}

	public void setQnano(int qnano) {
		this.qnano = qnano;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getQna_con_content() {
		return qna_con_content;
	}

	public void setQna_con_content(String qna_con_content) {
		this.qna_con_content = qna_con_content;
	}

	public String getQna_con_date() {
		return qna_con_date;
	}

	public void setQna_con_date(String qna_con_date) {
		this.qna_con_date = qna_con_date;
	}


	

}
