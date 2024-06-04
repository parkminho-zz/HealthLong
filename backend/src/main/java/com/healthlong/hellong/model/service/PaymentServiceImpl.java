package com.healthlong.hellong.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthlong.hellong.model.dao.PaymentDao;
import com.healthlong.hellong.model.dto.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	private final PaymentDao paymentDao;

	@Autowired
	public PaymentServiceImpl(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	@Override
	public boolean insertPayment(Payment payment) {
		// TODO Auto-generated method stub
		return paymentDao.insertPayment(payment) == 1;
	}

	@Override
	public List<Payment> searchPayment(String email) {
		return paymentDao.searchList(email);
	}
}
