package com.healthlong.hellong.model.dto;

public class Corporation {
	private int id;
	private String corpEmail;
	private String corpName;
	private String corpNum; // 사업자 번호
	private String corpPhNum;
	private String regDate;
	private String address;
	
	public Corporation() {
		
	}
	
	public Corporation(int id, String corpEmail, String corpName, String corpNum, String corpPhNum, String regDate,
			String address) {
		super();
		this.id = id;
		this.corpEmail = corpEmail;
		this.corpName = corpName;
		this.corpNum = corpNum;
		this.corpPhNum = corpPhNum;
		this.regDate = regDate;
		this.address = address;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getCorpEmail() {
		return corpEmail;
	}
	public void setCorpEmail(String corpEmail) {
		this.corpEmail = corpEmail;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpNum() {
		return corpNum;
	}
	public void setCorpNum(String corpNum) {
		this.corpNum = corpNum;
	}
	public String getCorpPhNum() {
		return corpPhNum;
	}
	public void setCorpPhNum(String corpPhNum) {
		this.corpPhNum = corpPhNum;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Corporation [corpEmail=" + corpEmail + ", corpName=" + corpName + ", corpNum=" + corpNum
				+ ", corpPhNum=" + corpPhNum + ", regDate=" + regDate + ", address=" + address + "]";
	}
	
	
	
}
