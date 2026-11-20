package com.alpha.theaterService.repo;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.entity.Theater;

public interface TheaterRepository
        extends JpaRepository<Theater, Long> {
		Optional<Theater> findBycontactNumber(long contactNumber);


}

