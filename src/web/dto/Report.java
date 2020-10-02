package web.dto;

public class Report {
	
	private int rpno;
	private int uno;
	private int targetpno;
	private String reportcontent;
	private String reportdate;
	
	@Override
	public String toString() {
		return "Report [rpno=" + rpno + ", uno=" + uno + ", targetpno=" + targetpno + ", reportcontent=" + reportcontent
				+ ", reportdate=" + reportdate + "]";
	}

	public int getRpno() {
		return rpno;
	}

	public void setRpno(int rpno) {
		this.rpno = rpno;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public int getTargetpno() {
		return targetpno;
	}

	public void setTargetpno(int targetpno) {
		this.targetpno = targetpno;
	}

	public String getReportcontent() {
		return reportcontent;
	}

	public void setReportcontent(String reportcontent) {
		this.reportcontent = reportcontent;
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	
	
}
