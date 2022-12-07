package com.luisjav.reto.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luisjav.reto.DTO.Appointment.AppointmentInsertDto;
import com.luisjav.reto.DTO.Appointment.AppointmentUpdateDto;
import com.luisjav.reto.Service.IAppointmentService;
import com.luisjav.reto.Utils.DateValidator;

@RestController
@RequestMapping("api/controller/appointments")
public class AppointmentController {
	@Autowired
	private IAppointmentService appointmentService;

	@GetMapping
	public ResponseEntity<?> GetList(@RequestParam(required = false, name = "affiliate") Long affiliateId, @RequestParam(required = false, name = "date") String date) {
		try {
			if (affiliateId != null) {
				var appointments = appointmentService.GetByAffiliate(affiliateId);

				if (appointments.size() == 0)
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);

				return new ResponseEntity<>(appointments, HttpStatus.OK);
			}
			
			if(date != null) {
				if (!DateValidator.isValid(date))
					return new ResponseEntity<String>("Wrong date format. Type dd/MM/yyyy instead.", HttpStatus.NO_CONTENT);
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        LocalDate localDate = LocalDate.parse(date, formatter);
		        
		        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        formatter1.format(localDate);
		        
		        var result = appointmentService.GetByDate(localDate);

				if (result.size() == 0)
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);

				return new ResponseEntity<>(result, HttpStatus.OK);
			}

			var list = appointmentService.GetList();

			if (list.size() == 0)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(list, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> GetById(@PathVariable long id) {
		try {
			var result = appointmentService.GetById(id);

			if (result == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> Post(@Valid @RequestBody AppointmentInsertDto appointmentInsertDto) {
		try {
			appointmentService.Post(appointmentInsertDto);

			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

	@GetMapping("{day}/{month}/{year}")
	public ResponseEntity<?> GetByDate(@PathVariable int day, @PathVariable int month, @PathVariable int year) {
		try {
			var date = day + "/" + month + "/" + year;

			if (!DateValidator.isValid(date))
				throw new Exception();

			LocalDate d = LocalDate.of(year, month, day);
			var result = appointmentService.GetByDate(d);

			if (result.size() == 0)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
