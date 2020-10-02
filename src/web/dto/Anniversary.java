package web.dto;

public class Anniversary {
	
	 private int cno;
	 private String title;
	 private String av_date;
	 private int rest_day;
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAv_date() {
		return av_date;
	}
	public void setAv_date(String av_date) {
		this.av_date = av_date;
	}
	
	
	public int getRest_day() {
		return rest_day;
	}
	public void setRest_day(int rest_day) {
		this.rest_day = rest_day;
	}
	@Override
	public String toString() {
		return "Anniversary [cno=" + cno + ", title=" + title + ", av_date=" + av_date + ", rest_day=" + rest_day + "]";
	}
	 
}
