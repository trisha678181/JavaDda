package web.dto;

public class Place {
	
	private int pno;// 가게 번호(시퀀스)
	private int lno; // 가게 장소 ex) 1.강남  2.동구
	private int pcode;// 카테고리번호 ex)1.카페 ,2. 술집 ...
	private String pname;// 가게이름
	private String pcontent;// 가게 내용
	private String pphone;// 가게 전화번호
	private String ploc; // 가게 주소
	private String pmenu_txt;// 가게 메뉴
	private String poperate;// 가게 운영시간
	private String pparking;// 가게 주차가능
	private int plike;// 가게 찜
	private String ppick; // 가게 관리자pick
	private String psubtitle;
	private double px;
	private double py;
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPphone() {
		return pphone;
	}
	public void setPphone(String pphone) {
		this.pphone = pphone;
	}
	public String getPloc() {
		return ploc;
	}
	public void setPloc(String ploc) {
		this.ploc = ploc;
	}
	public String getPmenu_txt() {
		return pmenu_txt;
	}
	public void setPmenu_txt(String pmenu_txt) {
		this.pmenu_txt = pmenu_txt;
	}
	public String getPoperate() {
		return poperate;
	}
	public void setPoperate(String poperate) {
		this.poperate = poperate;
	}
	public String getPparking() {
		return pparking;
	}
	public void setPparking(String pparking) {
		this.pparking = pparking;
	}
	public int getPlike() {
		return plike;
	}
	public void setPlike(int plike) {
		this.plike = plike;
	}
	public String getPpick() {
		return ppick;
	}
	public void setPpick(String ppick) {
		this.ppick = ppick;
	}
	public String getPsubtitle() {
		return psubtitle;
	}
	public void setPsubtitle(String psubtitle) {
		this.psubtitle = psubtitle;
	}
	public double getPx() {
		return px;
	}
	public void setPx(double px) {
		this.px = px;
	}
	public double getPy() {
		return py;
	}
	public void setPy(double py) {
		this.py = py;
	}
	@Override
	public String toString() {
		return "Place [pno=" + pno + ", lno=" + lno + ", pcode=" + pcode + ", pname=" + pname + ", pcontent=" + pcontent
				+ ", pphone=" + pphone + ", ploc=" + ploc + ", pmenu_txt=" + pmenu_txt + ", poperate=" + poperate
				+ ", pparking=" + pparking + ", plike=" + plike + ", ppick=" + ppick + ", psubtitle=" + psubtitle
				+ ", px=" + px + ", py=" + py + "]";
	}

}
