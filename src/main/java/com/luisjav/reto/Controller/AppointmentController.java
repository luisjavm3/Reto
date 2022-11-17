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

import com.luisjav.reto.DTO.Appointment.AppointmentInsertDto;
import com.luisjav.reto.DTO.Appointment.AppointmentUpdateDto;
import com.luisjav.reto.Service.IAppointmentService;
import com.luisjav.reto.Utils.DateValidator;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {
	@Autowired
	private IAppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<?> Post(@Valid @RequestBody AppointmentInsertDto appointmentInsertDto) {
		try {
			appointmentService.Post(appointmentInsertDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<?> GetList() {
		try {
			var list = appointmentService.GetList();

			if (list.size() == 0)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping
	public ResponseEntity<?> Put(@Valid @RequestBody AppointmentUpdateDto appointmentUpdateDto) {
		try {
			appointmentService.Put(appointmentUpdateDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> Delete(@Valid @PathVariable long id) {
		try {
			appointmentService.Delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("getbyaffiliates/{affiliateId}")
	public ResponseEntity<?> GetByAffiliates(@PathVariable long affiliateId) {
		try {
			var result = appointmentService.GetByAffiliate(affiliateId);

			if (result.size() == 0)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("{day}/{month}/{year}")
	public ResponseEntity<?> GetByDate(@PathVariable int day, @PathVariable int month, @PathVariable int year) {
		try {
			var date = day + "/" + month + "/" + year;

			if (!DateValidator.isValid(date))
				throw new Exception();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
