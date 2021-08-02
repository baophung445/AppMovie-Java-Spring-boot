package ptithcm.internship.movieapp.entrity;

public class CommentChart {
	private int cJanurary;
	private int cFebruary;
	private int cMarch;
	private int cApril;
	private int cMay;
	private int cJune;
	private int cJuly;
	private int cAugust;
	private int cSeptember;
	private int cOctober;
	private int cNovember;
	private int cDecember;

	public CommentChart() {
		super();
	}

	public CommentChart(int cJanurary, int cFebruary, int cMarch, int cApril, int cMay, int cJune, int cJuly,
			int cAugust, int cSeptember, int cOctober, int cNovember, int cDecember) {
		super();
		this.cJanurary = cJanurary;
		this.cFebruary = cFebruary;
		this.cMarch = cMarch;
		this.cApril = cApril;
		this.cMay = cMay;
		this.cJune = cJune;
		this.cJuly = cJuly;
		this.cAugust = cAugust;
		this.cSeptember = cSeptember;
		this.cOctober = cOctober;
		this.cNovember = cNovember;
		this.cDecember = cDecember;
	}

	public int getcJanurary() {
		return cJanurary;
	}

	public void setcJanurary(int cJanurary) {
		this.cJanurary = cJanurary;
	}

	public int getcFebruary() {
		return cFebruary;
	}

	public void setcFebruary(int cFebruary) {
		this.cFebruary = cFebruary;
	}

	public int getcMarch() {
		return cMarch;
	}

	public void setcMarch(int cMarch) {
		this.cMarch = cMarch;
	}

	public int getcApril() {
		return cApril;
	}

	public void setcApril(int cApril) {
		this.cApril = cApril;
	}

	public int getcMay() {
		return cMay;
	}

	public void setcMay(int cMay) {
		this.cMay = cMay;
	}

	public int getcJune() {
		return cJune;
	}

	public void setcJune(int cJune) {
		this.cJune = cJune;
	}

	public int getcJuly() {
		return cJuly;
	}

	public void setcJuly(int cJuly) {
		this.cJuly = cJuly;
	}

	public int getcAugust() {
		return cAugust;
	}

	public void setcAugust(int cAugust) {
		this.cAugust = cAugust;
	}

	public int getcSeptember() {
		return cSeptember;
	}

	public void setcSeptember(int cSeptember) {
		this.cSeptember = cSeptember;
	}

	public int getcOctober() {
		return cOctober;
	}

	public void setcOctober(int cOctober) {
		this.cOctober = cOctober;
	}

	public int getcNovember() {
		return cNovember;
	}

	public void setcNovember(int cNovember) {
		this.cNovember = cNovember;
	}

	public int getcDecember() {
		return cDecember;
	}

	public void setcDecember(int cDecember) {
		this.cDecember = cDecember;
	}

	@Override
	public String toString() {
		return "CommentChart [cJanurary=" + cJanurary + ", cFebruary=" + cFebruary + ", cMarch=" + cMarch + ", cApril="
				+ cApril + ", cMay=" + cMay + ", cJune=" + cJune + ", cJuly=" + cJuly + ", cAugust=" + cAugust
				+ ", cSeptember=" + cSeptember + ", cOctober=" + cOctober + ", cNovember=" + cNovember + ", cDecember="
				+ cDecember + "]";
	}

}
