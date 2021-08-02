package ptithcm.internship.movieapp.entrity;

public class MemberReportChart {
	private int rTypeError;
	private int rTypeWarning;
	private int rTypeOk;

	public MemberReportChart(int rTypeError, int rTypeWarning, int rTypeOk) {
		super();
		this.rTypeError = rTypeError;
		this.rTypeWarning = rTypeWarning;
		this.rTypeOk = rTypeOk;
	}

	public MemberReportChart() {
		super();
	}

	public int getrTypeError() {
		return rTypeError;
	}

	public void setrTypeError(int rTypeError) {
		this.rTypeError = rTypeError;
	}

	public int getrTypeWarning() {
		return rTypeWarning;
	}

	public void setrTypeWarning(int rTypeWarning) {
		this.rTypeWarning = rTypeWarning;
	}

	public int getrTypeOk() {
		return rTypeOk;
	}

	public void setrTypeOk(int rTypeOk) {
		this.rTypeOk = rTypeOk;
	}

	@Override
	public String toString() {
		return "MemberChart [rTypeError=" + rTypeError + ", rTypeWarning=" + rTypeWarning + ", rTypeOk=" + rTypeOk
				+ "]";
	}

}
