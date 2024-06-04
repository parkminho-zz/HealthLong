package com.healthlong.hellong.model.dao;

import java.util.List;

import com.healthlong.hellong.model.dto.Payment;

public interface PaymentDao {

	// 결제내역 저장
	public int insertPayment(Payment payment);
	
	//조회 
	public List<Payment> searchList(String email);

}
