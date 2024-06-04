package com.healthlong.hellong.model.service;

import java.util.List;

import com.healthlong.hellong.model.dto.Payment;

public interface PaymentService {

	// 결제내역 저장
	public boolean insertPayment(Payment payment);
	
	// 결제내역 조회
	public List<Payment> searchPayment(String email);

}
