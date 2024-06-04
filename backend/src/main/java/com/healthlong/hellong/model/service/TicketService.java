package com.healthlong.hellong.model.service;

import java.time.LocalDateTime;
import java.util.List;

import com.healthlong.hellong.model.dto.Ticket;

public interface TicketService {

	// 시작버튼 눌렀을때
	public boolean startTime(String email);
	
	// 종료버튼 눌렀을때
	public boolean endTime(Ticket ticket);
	
	// 사용 시간 확인
	public int isUseTimeCheck(String email, LocalDateTime ticketStart);
	
	// 이용내역 조회
	public List<Ticket> searchUse(String email);

}
