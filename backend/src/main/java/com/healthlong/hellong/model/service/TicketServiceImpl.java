package com.healthlong.hellong.model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthlong.hellong.model.dao.TicketDao;
import com.healthlong.hellong.model.dto.Ticket;

@Service
public class TicketServiceImpl implements TicketService{

	private final TicketDao ticketDao;
	
	@Autowired
	public TicketServiceImpl(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	@Override
	public boolean startTime(String email) {
		return ticketDao.startTime(email)==1;
	}


	@Override
	public boolean endTime(Ticket ticket) {
		return ticketDao.endTime(ticket) == 1;
	}

	@Override
	public int isUseTimeCheck(String email, LocalDateTime ticketStart) {
		return ticketDao.isUseTimeCheck(email, ticketStart);
	}

	@Override
	public List<Ticket> searchUse(String email) {
		return ticketDao.searchList(email);
	}
}	
