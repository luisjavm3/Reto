package com.luisjav.reto.Controller;

import java.rmi.NoSuchObjectException;

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
import com.luisjav.reto.Exception.NoContentException;
import com.luisjav.reto.Exception.NotFoundException;
import com.luisjav.reto.Service.ITestService;

@RestController
@RequestMapping("api/controller/tests")
public class TestController {

	@Autowired
	private ITestService testService;

	@GetMapping
	public ResponseEntity<?> GetList() throws NoContentException {
		var tests = testService.GetList();

		if (tests.size() == 0)
			throw new NoContentException();

		return new ResponseEntity<>(tests, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> GetById(@PathVariable("id") long id) throws NotFoundException {
		var test = testService.GetById(id);

		if (test == null)
			throw new NotFoundException();

		return new ResponseEntity<>(test, HttpStatus.OK);
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
	public ResponseEntity<?> Put(@RequestBody TestUpdateDto testUpdateDto) throws NoSuchObjectException {
		testService.Put(testUpdateDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> Delete(@PathVariable("id") long id) throws NoSuchObjectException {
		testService.Delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
