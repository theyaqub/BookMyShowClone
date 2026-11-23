package com.alpha.theaterService.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.dto.ShowRequestDto;
import com.alpha.theaterService.dto.UpdateShowDto;
import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.entity.Show;
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
	
	@PostMapping("/theater/addNewShow")
	public ResponseEntity<ResponseStructure<Show>> addNewShow(@RequestBody ShowRequestDto dto ) {
		return theaterServices.addNewShowService(dto);
	}
	
	@GetMapping("/theater/FindAllShowPlaying")
	public ResponseEntity<ResponseStructure<List<Show>>> findAllShow(@RequestParam long theaterid) {
		return theaterServices.findallShowService(theaterid);
	}
	
	@GetMapping("/theater/findShowById")
	public ResponseEntity<ResponseStructure<Show>> findShowById(@RequestParam long showId) {
		return theaterServices.findShowByIdService(showId);
	}
	
	@PatchMapping("/theater/updateShow")
	public ResponseEntity<ResponseStructure<Show>> updateShowById(@RequestBody UpdateShowDto d) {
		return theaterServices.updateShowByIdService(d);
	}
	
	@GetMapping("/theater/findMovieByName")
	public ResponseEntity<ResponseStructure<List<Movie>>> findMovieByname(@RequestParam String MovieName) {
		return  theaterServices.findMovieByNameService(MovieName);
	}
	
	@GetMapping("theater/findShows")
	public ResponseEntity<ResponseStructure<List<Show>>> findShows(
	        @RequestParam String movieName,
	        @RequestParam String language,
	        @RequestParam
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	        LocalDate date) {
		
	    return ResponseEntity.ok(
	            theaterServices.findShowsByMovieDetails(
	                    movieName, language, date
	            )
	    );
	}
}
