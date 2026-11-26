package com.alpha.theaterService.repo;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.entity.Theater;

public interface TheaterRepository
        extends JpaRepository<Theater, Long> {
		Optional<Theater> findBycontactNumber(long contactNumber);
		List<Theater> findByCityIgnoreCase(String city);
		@Query("SELECT DISTINCT t.city FROM Theater t ORDER BY t.city")
		List<String> findAllCities();


}

