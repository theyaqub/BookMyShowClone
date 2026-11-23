package com.alpha.theaterService.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.theaterService.dto.MovieRequestDTO;
import com.alpha.theaterService.dto.MovieResponseDTO;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.dto.ShowRequestDto;
import com.alpha.theaterService.dto.UpdateShowDto;
import com.alpha.theaterService.entity.Address;
import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.entity.Show;
import com.alpha.theaterService.entity.Theater;
import com.alpha.theaterService.exception.MovieAlreadyExistException;
import com.alpha.theaterService.exception.MovieNotFoundException;
import com.alpha.theaterService.exception.ShowAlreadyExistException;
import com.alpha.theaterService.exception.TheaterAlreadyExistException;
import com.alpha.theaterService.exception.TheaterNotFoundException;
import com.alpha.theaterService.repo.AddressRepository;
import com.alpha.theaterService.repo.ShowRepository;
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
		rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Movie Found");
        rs.setData(movieResponseDTO);

        return new ResponseEntity<ResponseStructure<MovieResponseDTO>>(rs,HttpStatus.OK);
		
	}

	public ResponseEntity<ResponseStructure<List<Movie>>> allMovieService() {
		ResponseStructure<List<Movie>> rs=new ResponseStructure<List<Movie>>(); 
		List<Movie> allMovie = addMovieRepo.findAll();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("sucess");
		rs.setData(allMovie);
		return new ResponseEntity<ResponseStructure<List<Movie>>>(rs,HttpStatus.OK);
		
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
		rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Movie Found and deleted");
        rs.setData(movieResponseDTO);

        return new ResponseEntity<ResponseStructure<MovieResponseDTO>>(rs,HttpStatus.OK);
		
	}
	
	
	@Autowired
	private ShowRepository repository; 
	public ResponseEntity<ResponseStructure<Show>> addNewShowService(ShowRequestDto dto) {
		
		ResponseStructure<Show> rs=new ResponseStructure<Show>();
		// Find theater
		Theater theater = theaterRepository.findById(dto.getTheaterId())
		        .orElseThrow(() -> new TheaterNotFoundException());
		// Find movie
		Movie movie = addMovieRepo.findById(dto.getMovieId())
		        .orElseThrow(() -> new MovieNotFoundException());
		boolean exists = repository
				.existsByTheater_IdAndShowDateAndShowTimeAndScreenNumber(
				        dto.getTheaterId(),
				        dto.getShowDate(),
				        dto.getShowTime(),
				        dto.getScreenNumber()
				    );

			if (exists) {
			    throw new ShowAlreadyExistException();
			}
		// Create a new show
		Show show = new Show();

		show.setMovie(movie);
		show.setTheater(theater);
		show.setShowDate(dto.getShowDate());
		show.setShowTime(dto.getShowTime());
		show.setScreenNumber(dto.getScreenNumber());
		repository.save(show);
		
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setMessage("movie :"+show.getMovie().getTitle()+" added");
		rs.setData(show);
		return new ResponseEntity<ResponseStructure<Show>>(rs,HttpStatus.CREATED);
		
	}

	public ResponseEntity<ResponseStructure<List<Show>>> findallShowService(long theaterid) {
		Theater theater = theaterRepository.findById(theaterid)
		        .orElseThrow(() -> new TheaterNotFoundException());
		List<Show> allShows=theater.getAllPlayingShows();
		
		ResponseStructure<List<Show>> rs=new ResponseStructure<List<Show>>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Success");
		rs.setData(allShows);
		
		return new ResponseEntity<ResponseStructure<List<Show>>>(rs,HttpStatus.OK);

		
	}
		
	
	public ResponseEntity<ResponseStructure<Show>> findShowByIdService(long showId) {
		Show show = repository.findById(showId).orElseThrow(()->new ShowAlreadyExistException());
		ResponseStructure<Show>rs =new ResponseStructure<Show>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("success");
		rs.setData(show);
		return new ResponseEntity<ResponseStructure<Show>>(rs,HttpStatus.OK);
		
	}

	public ResponseEntity<ResponseStructure<Show>> updateShowByIdService(UpdateShowDto d) {
		
		boolean exists=repository.existsByShowDateAndShowTimeAndScreenNumber(d.getShowDate(), d.getShowTime(), d.getScreenNumber());
		if(exists) {
			throw new ShowAlreadyExistException();
		}
		Show show = repository.findById(d.getId()).orElseThrow(()->new ShowAlreadyExistException());
		ResponseStructure<Show>rs =new ResponseStructure<Show>();		
		show.setScreenNumber(d.getScreenNumber());
		show.setShowDate(d.getShowDate());
		show.setShowTime(d.getShowTime());
		
		Show savedShow=repository.save(show);
		
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("success");
		rs.setData(savedShow);
		
		return new ResponseEntity<ResponseStructure<Show>>(rs,HttpStatus.OK);
		
		
	}

	public ResponseEntity<ResponseStructure<List<Movie>>> findMovieByNameService(String movieName) {

	    List<Movie> movie =
	            addMovieRepo.findByTitleContainingIgnoreCase(movieName.trim());

	    ResponseStructure<List<Movie>> rs = new ResponseStructure<>();

	    rs.setStatuscode(HttpStatus.OK.value());
	    rs.setMessage("success");
	    rs.setData(movie);

	    return new ResponseEntity<ResponseStructure<List<Movie>>>(rs,HttpStatus.OK);
	}

	public ResponseStructure<List<Show>> findShowsByMovieDetails(
	        String movieName,
	        String language,
	        LocalDate date) {

	    List<Show> shows =
	            repository
	            .findByMovie_TitleContainingIgnoreCaseAndMovie_LanguageIgnoreCaseAndShowDate(
	                    movieName.trim(),
	                    language.trim(),
	                    date
	            );

	    ResponseStructure<List<Show>> rs = new ResponseStructure<>();

	    rs.setStatuscode(HttpStatus.OK.value());
	    rs.setMessage("Shows fetched successfully");
	    rs.setData(shows);

	    return rs;
	}

}
