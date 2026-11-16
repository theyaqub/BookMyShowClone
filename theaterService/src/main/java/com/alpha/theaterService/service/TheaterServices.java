package com.alpha.theaterService.service;

import java.util.List;

import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.theaterService.dto.MovieRequestDTO;
import com.alpha.theaterService.dto.MovieResponseDTO;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.entity.Address;
import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.entity.Theater;
import com.alpha.theaterService.exception.MovieAlreadyExistException;
import com.alpha.theaterService.exception.MovieNotFoundException;
import com.alpha.theaterService.exception.TheaterAlreadyExistException;
import com.alpha.theaterService.exception.TheaterNotFoundException;
import com.alpha.theaterService.repo.AddressRepository;
import com.alpha.theaterService.repo.TheaterRepository;

@Service
public class TheaterServices {

	@Autowired
	TheaterRepository theaterRepository;
	@Autowired
	AddressRepository addressRepository;
	
	public ResponseEntity<ResponseStructure<Theater>> createTheaterAcoount(Theater theater) {
		ResponseStructure<Theater> rs = new ResponseStructure<Theater>();
		if (theaterRepository.findBycontactNumber(theater.getContactNumber()).isPresent()) {
		    throw new TheaterAlreadyExistException();
		}
		theater.setStatus("Closed");
//		theater.setAllPlayingShows(null);
		Address add = theater.getAddress();
		if(add == null) {
			System.out.println("add is null");
		}else {
			add = addressRepository.save(add);
		}
		Theater th1 = theaterRepository.save(theater);
		th1.setAddress(add);
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setMessage("Theater Created Successfully");
		rs.setData(th1);
		return new ResponseEntity<ResponseStructure<Theater>>(rs, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Theater>> findTheater(long id) {
		
		ResponseStructure<Theater> rs=new ResponseStructure<Theater>();
		Theater theater=theaterRepository.findById(id).orElseThrow(()-> new TheaterNotFoundException());
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("Theater Created Successfully");
		rs.setData(theater);
		return new ResponseEntity<ResponseStructure<Theater>>(rs, HttpStatus.FOUND);
		
	}

	public ResponseEntity<ResponseStructure<Theater>> deleteTheaterById(long id) {
		ResponseStructure<Theater> rs=new ResponseStructure<Theater>();
		Theater theater=theaterRepository.findById(id).orElseThrow(()-> new TheaterNotFoundException());
		theaterRepository.delete(theater);
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		rs.setMessage("Theater Deleted Successfully");
		rs.setData(theater);
		return new ResponseEntity<ResponseStructure<Theater>>(rs, HttpStatus.ACCEPTED);
	}
	
	@Autowired
	private com.alpha.theaterService.repo.addMovieRepo addMovieRepo;
	
	public ResponseEntity<ResponseStructure<MovieResponseDTO>> addMovieService(MovieRequestDTO dto) {
		ResponseStructure<MovieResponseDTO> rs= new ResponseStructure<MovieResponseDTO>();
		if(addMovieRepo.existsByTitleIgnoreCaseAndLanguageIgnoreCase(dto.getTitle(),dto.getLanguage())) {
			throw new MovieAlreadyExistException();
		}
		Movie movie=new Movie();
		movie.setTitle(dto.getTitle());
		movie.setDescription(dto.getDescription());
		movie.setCast(dto.getCast());
		movie.setLanguage(dto.getLanguage());
		
		Movie SaveMovie=addMovieRepo.save(movie);
		
		MovieResponseDTO movieResponseDTO=new MovieResponseDTO();
		movieResponseDTO.setMovieId(SaveMovie.getId());
		movieResponseDTO.setTitle(SaveMovie.getTitle());
		movieResponseDTO.setDescription(SaveMovie.getDescription());
		movieResponseDTO.setCast(SaveMovie.getCast());
		movieResponseDTO.setLanguage(SaveMovie.getLanguage());
		
		rs.setStatuscode(HttpStatus.CREATED.value());
        rs.setMessage("Movie added successfully");
        rs.setData(movieResponseDTO);

        return new ResponseEntity<ResponseStructure<MovieResponseDTO>>(rs,HttpStatus.CREATED);
		
	}

	public ResponseEntity<ResponseStructure<MovieResponseDTO>> findMovieService(long id) {
		ResponseStructure<MovieResponseDTO> rs= new ResponseStructure<MovieResponseDTO>();
		Movie movie=addMovieRepo.findById(id).orElseThrow(()->new MovieNotFoundException());
		MovieResponseDTO movieResponseDTO=new MovieResponseDTO();
		movieResponseDTO.setMovieId(movie.getId());
		movieResponseDTO.setTitle(movie.getTitle());
		movieResponseDTO.setDescription(movie.getDescription());
		movieResponseDTO.setCast(movie.getCast());
		movieResponseDTO.setLanguage(movie.getLanguage());
		rs.setStatuscode(HttpStatus.FOUND.value());
        rs.setMessage("Movie Found");
        rs.setData(movieResponseDTO);

        return new ResponseEntity<ResponseStructure<MovieResponseDTO>>(rs,HttpStatus.FOUND);
		
	}

	public ResponseEntity<ResponseStructure<List<Movie>>> allMovieService() {
		ResponseStructure<List<Movie>> rs=new ResponseStructure<List<Movie>>(); 
		List<Movie> allMovie = addMovieRepo.findAll();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("sucess");
		rs.setData(allMovie);
		return new ResponseEntity<ResponseStructure<List<Movie>>>(rs,HttpStatus.FOUND);
		
	}

	public ResponseEntity<ResponseStructure<MovieResponseDTO>> deleteMoviebyId(long id) {
		ResponseStructure<MovieResponseDTO> rs= new ResponseStructure<MovieResponseDTO>();
		Movie movie=addMovieRepo.findById(id).orElseThrow(()->new MovieNotFoundException());
		MovieResponseDTO movieResponseDTO=new MovieResponseDTO();
		movieResponseDTO.setMovieId(movie.getId());
		movieResponseDTO.setTitle(movie.getTitle());
		movieResponseDTO.setDescription(movie.getDescription());
		movieResponseDTO.setCast(movie.getCast());
		movieResponseDTO.setLanguage(movie.getLanguage());
		addMovieRepo.deleteById(id);
		rs.setStatuscode(HttpStatus.FOUND.value());
        rs.setMessage("Movie Found and deleted");
        rs.setData(movieResponseDTO);

        return new ResponseEntity<ResponseStructure<MovieResponseDTO>>(rs,HttpStatus.FOUND);
		
	}


}
