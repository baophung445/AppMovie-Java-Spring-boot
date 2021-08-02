package ptithcm.internship.movieapp.dto;

import ptithcm.internship.movieapp.entrity.GroupVideoChart;
import ptithcm.internship.movieapp.entrity.VideoChart;

public class AcceptVideoChartRequest {
	private VideoChart videoChart;
	private GroupVideoChart groupVideoChart;

	public AcceptVideoChartRequest() {
		super();
	}

	public AcceptVideoChartRequest(VideoChart videoChart, GroupVideoChart groupVideoChart) {
		super();
		this.videoChart = videoChart;
		this.groupVideoChart = groupVideoChart;
	}

	public VideoChart getVideoChart() {
		return videoChart;
	}

	public void setVideoChart(VideoChart videoChart) {
		this.videoChart = videoChart;
	}

	public GroupVideoChart getGroupVideoChart() {
		return groupVideoChart;
	}

	public void setGroupVideoChart(GroupVideoChart groupVideoChart) {
		this.groupVideoChart = groupVideoChart;
	}

	@Override
	public String toString() {
		return "AcceptVideoChartRequest [videoChart=" + videoChart + ", groupVideoChart=" + groupVideoChart + "]";
	}

}
