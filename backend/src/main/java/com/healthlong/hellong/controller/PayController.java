package com.healthlong.hellong.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthlong.hellong.model.dto.Payment;
import com.healthlong.hellong.model.service.PaymentService;
import com.healthlong.hellong.model.service.UserService;

import ch.qos.logback.core.model.Model;

@RestController
@RequestMapping("/pay")
public class PayController {
	
	private final PaymentService paymentService;
	private final UserService userService;
	
	@Autowired
	public PayController(PaymentService paymentService, UserService userService) {
		this.paymentService = paymentService;
		this.userService = userService;
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveData(Payment payment, @RequestBody Map<String, String> data) {
		System.out.println("받아온데이;터:" + data);
		 
		String paidAmount = data.get("paid_amount");
		String remainTime = data.get("name");
		
		payment.setImpUid(data.get("imp_uid"));
		payment.setBuyerEmail(data.get("buyer_email"));
		payment.setBuyerName(data.get("buyer_name"));
		payment.setPaidAmount(Integer.parseInt(paidAmount));
		userService.updateRemainTime(data.get("buyer_email"), Integer.parseInt(remainTime));
		
		int plusRemainTime = Integer.parseInt(data.get("name"));
		System.out.println(12345);
		System.out.println(payment);
		paymentService.insertPayment(payment);

		return new ResponseEntity<>(plusRemainTime, HttpStatus.CREATED);
	}

	
}
