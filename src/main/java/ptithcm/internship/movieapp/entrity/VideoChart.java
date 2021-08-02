package ptithcm.internship.movieapp.entrity;

public class VideoChart {
	private int vJanurary;
	private int vFebruary;
	private int vMarch;
	private int vApril;
	private int vMay;
	private int vJune;
	private int vJuly;
	private int vAugust;
	private int vSeptember;
	private int vOctober;
	private int vNovember;
	private int vDecember;

	public VideoChart() {
		super();
	}

	public VideoChart(int vJanurary, int vFebruary, int vMarch, int vApril, int vMay, int vJune, int vJuly, int vAugust,
			int vSeptember, int vOctober, int vNovember, int vDecember) {
		super();
		this.vJanurary = vJanurary;
		this.vFebruary = vFebruary;
		this.vMarch = vMarch;
		this.vApril = vApril;
		this.vMay = vMay;
		this.vJune = vJune;
		this.vJuly = vJuly;
		this.vAugust = vAugust;
		this.vSeptember = vSeptember;
		this.vOctober = vOctober;
		this.vNovember = vNovember;
		this.vDecember = vDecember;
	}

	public int getvJanurary() {
		return vJanurary;
	}

	public void setvJanurary(int vJanurary) {
		this.vJanurary = vJanurary;
	}

	public int getvFebruary() {
		return vFebruary;
	}

	public void setvFebruary(int vFebruary) {
		this.vFebruary = vFebruary;
	}

	public int getvMarch() {
		return vMarch;
	}

	public void setvMarch(int vMarch) {
		this.vMarch = vMarch;
	}

	public int getvApril() {
		return vApril;
	}

	public void setvApril(int vApril) {
		this.vApril = vApril;
	}

	public int getvMay() {
		return vMay;
	}

	public void setvMay(int vMay) {
		this.vMay = vMay;
	}

	public int getvJune() {
		return vJune;
	}

	public void setvJune(int vJune) {
		this.vJune = vJune;
	}

	public int getvJuly() {
		return vJuly;
	}

	public void setvJuly(int vJuly) {
		this.vJuly = vJuly;
	}

	public int getvAugust() {
		return vAugust;
	}

	public void setvAugust(int vAugust) {
		this.vAugust = vAugust;
	}

	public int getvSeptember() {
		return vSeptember;
	}

	public void setvSeptember(int vSeptember) {
		this.vSeptember = vSeptember;
	}

	public int getvOctober() {
		return vOctober;
	}

	public void setvOctober(int vOctober) {
		this.vOctober = vOctober;
	}

	public int getvNovember() {
		return vNovember;
	}

	public void setvNovember(int vNovember) {
		this.vNovember = vNovember;
	}

	public int getvDecember() {
		return vDecember;
	}

	public void setvDecember(int vDecember) {
		this.vDecember = vDecember;
	}

	@Override
	public String toString() {
		return "VideoChart [vJanurary=" + vJanurary + ", vFebruary=" + vFebruary + ", vMarch=" + vMarch + ", vApril="
				+ vApril + ", vMay=" + vMay + ", vJune=" + vJune + ", vJuly=" + vJuly + ", vAugust=" + vAugust
				+ ", vSeptember=" + vSeptember + ", vOctober=" + vOctober + ", vNovember=" + vNovember + ", vDecember="
				+ vDecember + "]";
	}

}
