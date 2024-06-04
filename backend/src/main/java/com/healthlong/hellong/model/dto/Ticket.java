package com.healthlong.hellong.model.dto;

import java.time.LocalDateTime;

public class Ticket {
	private int ticketId;
	private String ticketUid;
	private LocalDateTime ticketStart;
	private LocalDateTime ticketEnd;
	private String gymName;
	private int ticketUse;

	public Ticket() {
		
	}

	public Ticket(int ticketId, String ticketUid, LocalDateTime ticketStart, LocalDateTime ticketEnd, String gymName,
			int ticketUse) {
		super();
		this.ticketId = ticketId;
		this.ticketUid = ticketUid;
		this.ticketStart = ticketStart;
		this.ticketEnd = ticketEnd;
		this.gymName = gymName;
		this.ticketUse = ticketUse;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketUid() {
		return ticketUid;
	}

	public void setTicketUid(String ticketUid) {
		this.ticketUid = ticketUid;
	}

	public LocalDateTime getTicketStart() {
		return ticketStart;
	}

	public void setTicketStart(LocalDateTime ticketStart) {
		this.ticketStart = ticketStart;
	}

	public LocalDateTime getTicketEnd() {
		return ticketEnd;
	}

	public void setTicketEnd(LocalDateTime ticketEnd) {
		this.ticketEnd = ticketEnd;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public int getTicketUse() {
		return ticketUse;
	}

	public void setTicketUse(int ticketUse) {
		this.ticketUse = ticketUse;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", ticketUid=" + ticketUid + ", ticketStart=" + ticketStart
				+ ", ticketEnd=" + ticketEnd + ", gymName=" + gymName + ", ticketUse=" + ticketUse + "]";
	}

}
