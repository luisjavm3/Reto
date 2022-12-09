package com.luisjav.reto.Controller;

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

import com.luisjav.reto.DTO.Affiliate.AffiliateInsertDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateUpdateDto;
import com.luisjav.reto.Exception.NoContentException;
import com.luisjav.reto.Exception.NotFoundException;
import com.luisjav.reto.Service.IAffiliateService;

@RestController
@RequestMapping("api/controller/affiliates")
public class AffiliateController {

	@Autowired
	private IAffiliateService affiliateService;

	@GetMapping
	public ResponseEntity<?> GetList() throws NoContentException {
		var affiliates = affiliateService.GetList();

		if (affiliates.size() == 0)
			throw new NoContentException();

		return new ResponseEntity<>(affiliates, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> GetById(@PathVariable long id) throws NotFoundException {
		var affiliate = affiliateService.GetById(id);

		if (affiliate == null)
			throw new NotFoundException();

		return new ResponseEntity<>(affiliate, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> Post(@Valid @RequestBody AffiliateInsertDto affiliateInsertDto) {
		try {
			affiliateService.Post(affiliateInsertDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping
	public ResponseEntity<?> Put(@Valid @RequestBody AffiliateUpdateDto affiliateUpdateDto) throws NotFoundException {
		affiliateService.Put(affiliateUpdateDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> Delete(@PathVariable long id) throws NoContentException {
		affiliateService.Delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
