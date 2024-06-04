package com.healthlong.hellong.model.dto;

public class Gym {
	private int id;
	private String gymName;
	private String gymAddress;
	private String img;
	private String gymPhNum;
	private String operatingTime;
//	private 위도
//	private 경도
	
	public Gym() {
		
	}
	
	public Gym(int id, String gymName, String gymAddress, String img, String gymPhNum, String operatingTime) {
		super();
		this.id = id;
		this.gymName = gymName;
		this.gymAddress = gymAddress;
		this.img = img;
		this.gymPhNum = gymPhNum;
		this.operatingTime = operatingTime;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public String getGymAddress() {
		return gymAddress;
	}

	public void setGymAddress(String gymAddress) {
		this.gymAddress = gymAddress;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getGymPhNum() {
		return gymPhNum;
	}

	public void setGymPhNum(String gymPhNum) {
		this.gymPhNum = gymPhNum;
	}

	public String getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}

	@Override
	public String toString() {
		return "Gym [id=" + id + ", gymName=" + gymName + ", gymAddress=" + gymAddress + ", img=" + img + ", gymPhNum="
				+ gymPhNum + ", operatingTime=" + operatingTime + ", getId()=" + getId() + ", getGymName()="
				+ getGymName() + ", getGymAddress()=" + getGymAddress() + ", getImg()=" + getImg() + ", getGymPhNum()="
				+ getGymPhNum() + ", getOperatingTime()=" + getOperatingTime() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
