package web.dto;

public class Location {
	
    private int lno;
    private String name;
    private float X;
    private float Y;
	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public float getX() {
		return X;
	}
	public void setX(float x) {
		X = x;
	}
	public float getY() {
		return Y;
	}
	public void setY(float y) {
		Y = y;
	}
	@Override
	public String toString() {
		return "Location [lno=" + lno + ", name=" + name + ", X=" + X + ", Y=" + Y + "]";
	}
	
    

}
