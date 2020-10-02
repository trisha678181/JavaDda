package web.dto;

public class Business {
	
	private int bno;// 사업자 번호(시퀀스번호)
	private int pno;// 장소 번호(시퀀스번호)
	private String id;// 사업자 id
	private String pw;// 사업자 pw
	private String name;// 사업자 이름 
	private String bsnum;// 사업자 사업번호
	private String phone;// 사업자 폰번호
	private String birth;
	private String gender;
	private String mail;
	private String ques;
	private String answ;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBsnum() {
		return bsnum;
	}
	public void setBsnum(String bsnum) {
		this.bsnum = bsnum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	public String getAnsw() {
		return answ;
	}
	public void setAnsw(String answ) {
		this.answ = answ;
	}
	@Override
	public String toString() {
		return "Business [bno=" + bno + ", pno=" + pno + ", id=" + id + ", pw=" + pw + ", name=" + name + ", bsnum="
				+ bsnum + ", phone=" + phone + ", birth=" + birth + ", gender=" + gender + ", mail=" + mail + ", ques="
				+ ques + ", answ=" + answ + "]";
	}
	
	
	
	

}
