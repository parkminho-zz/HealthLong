package com.healthlong.hellong.model.dto;

public class Payment {
	private String impUid;		//결제번호			
	private String buyerEmail;	// 결제자 이메일
	private String buyerName;	// 결제자 이름
	private int paidAmount;	     	// 금액
	private String payDay;	 	// 결제일
	
	public Payment() {
		
	}

	public Payment(String impUid, String buyerEmail, String buyerName, int paidAmount, String payDay) {
		super();
		this.impUid = impUid;
		this.buyerEmail = buyerEmail;
		this.buyerName = buyerName;
		this.paidAmount = paidAmount;
		this.payDay = payDay;
	}

	public String getImpUid() {
		return impUid;
	}

	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPayDay() {
		return payDay;
	}

	public void setPayDay(String payDay) {
		this.payDay = payDay;
	}

	@Override
	public String toString() {
		return "Payment [impUid=" + impUid + ", buyerEmail=" + buyerEmail + ", buyerName=" + buyerName + ", amount="
				+ paidAmount + ", payDay=" + payDay + "]";
	}
	

}
