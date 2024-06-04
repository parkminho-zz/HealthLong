package com.healthlong.hellong.model.dto;

public class UseDetails{
	private int id;
	private String detailUid; // 해당 사용자
	private String detailStart; // 시작시간
	private String detailEnd; // 종료시간
	private String detailGymName; // 사용한 헬스장이름
	
	public UseDetails() {
		
	}

	public UseDetails(int id, String detailUid, String detailStart, String detailEnd, String detailGymName) {
		super();
		this.id = id;
		this.detailUid = detailUid;
		this.detailStart = detailStart;
		this.detailEnd = detailEnd;
		this.detailGymName = detailGymName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetailUid() {
		return detailUid;
	}

	public void setDetailUid(String detailUid) {
		this.detailUid = detailUid;
	}

	public String getDetailStart() {
		return detailStart;
	}

	public void setDetailStart(String detailStart) {
		this.detailStart = detailStart;
	}

	public String getDetailEnd() {
		return detailEnd;
	}

	public void setDetailEnd(String detailEnd) {
		this.detailEnd = detailEnd;
	}

	public String getDetailGymName() {
		return detailGymName;
	}

	public void setDetailGymName(String detailGymName) {
		this.detailGymName = detailGymName;
	}

	@Override
	public String toString() {
		return "UseDetails [id=" + id + ", detailUid=" + detailUid + ", detailStart=" + detailStart + ", detailEnd="
				+ detailEnd + ", detailGymName=" + detailGymName + "]";
	}
	
}
