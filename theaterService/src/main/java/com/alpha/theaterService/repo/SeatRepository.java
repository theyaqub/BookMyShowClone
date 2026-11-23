package com.alpha.theaterService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.theaterService.entity.Seat;
import com.alpha.theaterService.enums.SeatCategory;
import com.alpha.theaterService.enums.SeatStatus;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	List<Seat> findByShow_IdAndStatus(
            Long showId,
            SeatStatus status
    );
	List<Seat> findByShow_Id(
			Long showId
			);

    List<Seat> findByShow_IdAndCategory(
            Long showId,
            SeatCategory category
    );

    List<Seat> findByShow_IdAndStatusAndCategory(
            Long showId,
            SeatStatus status,
            SeatCategory category
    );

	
}
