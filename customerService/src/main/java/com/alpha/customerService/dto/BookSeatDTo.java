package com.alpha.customerService.dto;

import java.util.List;

public class BookSeatDTo {

	private long customerid;
	private long showid;
	private List<String> avaibleSeats;
	public long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}
	public long getShowid() {
		return showid;
	}
	public void setShowid(long showid) {
		this.showid = showid;
	}
	public List<String> getAvaibleSeats() {
		return avaibleSeats;
	}
	public void setAvaibleSeats(List<String> avaibleSeats) {
		this.avaibleSeats = avaibleSeats;
	}
	
}
