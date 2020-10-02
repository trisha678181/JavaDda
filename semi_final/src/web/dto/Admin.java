package web.dto;

public class Admin {

	private int    ano;
	private String id;//관리자 id
	private String pw;//관리자 pw
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "Admin [ano=" + ano + ", id=" + id + ", pw=" + pw + "]";
	}
	
}
