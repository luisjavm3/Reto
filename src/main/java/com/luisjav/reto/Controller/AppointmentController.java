package com.luisjav.reto.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisjav.reto.DTO.Appointment.AppointmentInsertDto;
import com.luisjav.reto.Service.IAppointmentService;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {
	@Autowired
	private IAppointmentService appointmentService;
	
	@PostMapping
	public ResponseEntity<?> Post(@Valid @RequestBody AppointmentInsertDto appointmentInsertDto)
	{
		try {
			appointmentService.Post(appointmentInsertDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
