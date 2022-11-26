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
import com.luisjav.reto.Service.IAffiliateService;

@RestController
@RequestMapping("api/controller/affiliates")
public class AffiliateController {

	@Autowired
	private IAffiliateService affiliateService;

	@GetMapping
	public ResponseEntity<?> GetList() {
		try {
			var affiliates = affiliateService.GetList();

			if (affiliates.size() == 0)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(affiliates, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> GetById(@PathVariable long id) {
		try {
			var affiliate = affiliateService.GetById(id);

			if (affiliate == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<>(affiliate, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
	public ResponseEntity<?> Put(@Valid @RequestBody AffiliateUpdateDto affiliateUpdateDto) {
		try {
			affiliateService.Put(affiliateUpdateDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> Delete(@PathVariable long id) {
		try {
			affiliateService.Delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
