package com.luisjav.reto.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisjav.reto.DTO.Test.TestInsertDto;
import com.luisjav.reto.DTO.Test.TestUpdateDto;
import com.luisjav.reto.Entity.Test;
import com.luisjav.reto.Service.ITestService;

@RestController
@RequestMapping("api/tests")
public class TestController {

	@Autowired
	private ITestService testService;

	@GetMapping
	public ResponseEntity<?> GetList() {
		try {
			List<Test> tests = testService.GetList();
			
			if (tests.size() > 0)
				return new ResponseEntity<>(tests, HttpStatus.OK);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> GetById(@PathVariable("id") long id) {
		try {
			Test test = testService.GetById(id);
			
			if (test != null)
				return new ResponseEntity<>(test, HttpStatus.OK);
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> Post(@Valid @RequestBody TestInsertDto testInsertDto) {
		try {
			testService.Post(testInsertDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping
	public ResponseEntity<?> Put(@RequestBody TestUpdateDto testUpdateDto){
		try {
			testService.Put(testUpdateDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> Delete(@PathVariable("id") long id)
	{
		try {
			testService.Delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
