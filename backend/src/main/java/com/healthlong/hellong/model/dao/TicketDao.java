package com.healthlong.hellong.model.dao;


import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.healthlong.hellong.model.dto.Ticket;

public interface TicketDao {
	
	// 시작버튼 눌렀을떄 실행
	public int startTime(String email);

	// 종료버튼 눌렀을 때 실행
	public int endTime(Ticket ticket);

	// 사용시간확인
	public int isUseTimeCheck(@Param("email") String email,  @Param("ticketStart")LocalDateTime ticketStart);
	
	//조회 
	public List<Ticket> searchList(String email);

}


//예외사항 : 잔여시간 초과 시 