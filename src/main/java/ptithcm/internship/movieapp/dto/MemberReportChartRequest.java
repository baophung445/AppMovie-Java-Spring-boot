package ptithcm.internship.movieapp.dto;

import ptithcm.internship.movieapp.entrity.MemberReportChart;

public class MemberReportChartRequest {
	private MemberReportChart memberReportChart;

	public MemberReportChartRequest() {
		super();
	}

	public MemberReportChartRequest(MemberReportChart memberReportChart) {
		super();
		this.memberReportChart = memberReportChart;
	}

	public MemberReportChart getMemberReportChart() {
		return memberReportChart;
	}

	public void setMemberReportChart(MemberReportChart memberReportChart) {
		this.memberReportChart = memberReportChart;
	}

	@Override
	public String toString() {
		return "MemberReportChartRequest [memberReportChart=" + memberReportChart + "]";
	}

}
