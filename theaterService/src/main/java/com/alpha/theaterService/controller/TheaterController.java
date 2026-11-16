package com.alpha.theaterService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.entity.Theater;
import com.alpha.theaterService.service.TheaterServices;

@RestController
public class TheaterController {
	
	@Autowired
	private TheaterServices theaterServices;
	
	@PostMapping("/theater/createAccount")
	public ResponseEntity<ResponseStructure<Theater>> createTheaterAccount(@RequestBody Theater theater) {
		return theaterServices.createTheaterAcoount(theater);
	}
	
	@GetMapping("/theater/find")
	public ResponseEntity<ResponseStructure<Theater>> findTheater(@RequestParam long id) {
		return theaterServices.findTheater(id);
	}
	
	@DeleteMapping("/theater/delete")
	public ResponseEntity<ResponseStructure<Theater>> deleteTheater(@RequestParam long id) {
		return theaterServices.deleteTheaterById(id);
	}

	@GetMapping("/theater/findAllMovie")
	public ResponseEntity<ResponseStructure<List<Movie>>> getAllMovie() {
		return theaterServices.allMovieService();
	}
	
}
