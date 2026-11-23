package com.alpha.theaterService.repo;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.theaterService.entity.Show;

public interface ShowRepository
        extends JpaRepository<Show, Long> {
	public boolean existsByTheater_IdAndShowDateAndShowTimeAndScreenNumber(
	        long theaterId,
	        LocalDate showDate,
	        LocalTime showTime,
	        int screenNumber
	);
	public boolean existsByShowDateAndShowTimeAndScreenNumber(
			LocalDate showDate,
			LocalTime showTime,
			int screenNumber
			);
	
	 public List<Show> findByMovie_TitleContainingIgnoreCaseAndMovie_LanguageIgnoreCaseAndShowDate(
	            String movieName,
	            String language,
	            LocalDate showDate
	    );
}
