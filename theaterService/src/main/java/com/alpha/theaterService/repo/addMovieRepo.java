package com.alpha.theaterService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.theaterService.entity.Movie;



public interface addMovieRepo extends JpaRepository<Movie, Long> {

	public boolean existsByTitleIgnoreCaseAndLanguageIgnoreCase(String title, String language);

	List<Movie> findByTitleContainingIgnoreCase(String movieName);

}
