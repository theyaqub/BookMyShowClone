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

import com.alpha.theaterService.dto.MovieRequestDTO;
import com.alpha.theaterService.dto.MovieResponseDTO;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.service.TheaterServices;



@RestController
public class AdminController {
	
	@Autowired
	private TheaterServices theaterServices;
	
	@PostMapping("/admin/addMovie")
	public ResponseEntity<ResponseStructure<MovieResponseDTO>> addMovie(@RequestBody MovieRequestDTO dto) {
		return theaterServices.addMovieService(dto);
	}
	
	@GetMapping("/movie/findMovie")
	public ResponseEntity<ResponseStructure<MovieResponseDTO>> findMovieById(@RequestParam long id) {
		return theaterServices.findMovieService(id);
	}
	
	@GetMapping("/movie/getAllMovie")
	public ResponseEntity<ResponseStructure<List<Movie>>> getAllMovie() {
		return theaterServices.allMovieService();
	}
	
	@DeleteMapping("/movie/delete")
	public ResponseEntity<ResponseStructure<MovieResponseDTO>> deleteMoviebyId(@RequestParam long id) {
		 return theaterServices.deleteMoviebyId(id);
	}
}
