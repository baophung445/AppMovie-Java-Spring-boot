package ptithcm.internship.movieapp.dto;

import ptithcm.internship.movieapp.entrity.CommentChart;

public class CommentChartRequest {
	private CommentChart commentChart;

	public CommentChartRequest() {
		super();
	}

	public CommentChartRequest(CommentChart commentChart) {
		super();
		this.commentChart = commentChart;
	}

	public CommentChart getCommentChart() {
		return commentChart;
	}

	public void setCommentChart(CommentChart commentChart) {
		this.commentChart = commentChart;
	}

	@Override
	public String toString() {
		return "CommentChartRequest [commentChart=" + commentChart + "]";
	}

}
