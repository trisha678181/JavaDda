package web.dto;

public class User {
	
	private int uno;

	private String name;
	private String id;
	private String pw;
	private String birth;
	private String nick;
	private String gender;
	private String phone;
	private String mail;
	
	private int hot;
	private int cold;
	private int rain;
	private int snow;
	
	private String uprofileimg;
	private String ubgimg;
	private String uques;
	private String uansw;
	
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUprofileimg() {
		return uprofileimg;
	}
	public void setUprofileimg(String uprofileimg) {
		this.uprofileimg = uprofileimg;
	}
	public String getUbgimg() {
		return ubgimg;
	}
	public void setUbgimg(String ubgimg) {
		this.ubgimg = ubgimg;
	}
	public String getUques() {
		return uques;
	}
	public void setUques(String uques) {
		this.uques = uques;
	}
	public String getUansw() {
		return uansw;
	}
	public void setUansw(String uansw) {
		this.uansw = uansw;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public int getCold() {
		return cold;
	}
	public void setCold(int cold) {
		this.cold = cold;
	}
	public int getRain() {
		return rain;
	}
	public void setRain(int rain) {
		this.rain = rain;
	}
	public int getSnow() {
		return snow;
	}
	public void setSnow(int snow) {
		this.snow = snow;
	}
	@Override
	public String toString() {
		return "User [uno=" + uno + ", name=" + name + ", id=" + id + ", pw=" + pw + ", birth=" + birth + ", nick="
				+ nick + ", gender=" + gender + ", phone=" + phone + ", mail=" + mail + ", hot=" + hot + ", cold="
				+ cold + ", rain=" + rain + ", snow=" + snow + ", uprofileimg=" + uprofileimg + ", ubgimg=" + ubgimg
				+ ", uques=" + uques + ", uansw=" + uansw + "]";
	}
	
	
	

	
	
}
