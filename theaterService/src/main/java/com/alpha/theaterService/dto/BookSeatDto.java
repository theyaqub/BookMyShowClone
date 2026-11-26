package com.alpha.theaterService.dto;

import java.util.List;

public class BookSeatDto {

	    private long customerId;
	    private long showId;
	    private List<String> seatNo;

	    public long getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(long customerId) {
	        this.customerId = customerId;
	    }

	    public long getShowId() {
	        return showId;
	    }

	    public void setShowId(long showId) {
	        this.showId = showId;
	    }

	    public List<String> getSeatNo() {
	        return seatNo;
	    }

	    public void setSeatNo(List<String> seatNo) {
	        this.seatNo = seatNo;
	    }
	}

