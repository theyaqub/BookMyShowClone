package com.alpha.theaterService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.entity.Theater;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TheaterAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> TheaterAlreadyExistExceptionHandler() {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.CONFLICT.value());
		rs.setMessage("Theater Already Exist");
		rs.setData("Theater Already Exist");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(TheaterNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> TheaterNotFoundExceptionHandler() {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Theater Not found");
		rs.setData("Theater Not found");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MovieAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> MovieAlreadyExistExceptionHandler() {
		ResponseStructure<String> rs= new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.CONFLICT.value());
		rs.setMessage("Movie already exists");
		rs.setData("movie already exists");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> MovieNotFoundExceptionHandler() {
		ResponseStructure<String> rs= new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Movie Not Found");
		rs.setData("movie Not Found");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ShowAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> ShowAlreadyExistExceptionHandler() {
		ResponseStructure<String> rs= new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.CONFLICT.value());
		rs.setMessage("Show already exists");
		rs.setData("Show already exists");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CONFLICT);
	}
	
	
}
