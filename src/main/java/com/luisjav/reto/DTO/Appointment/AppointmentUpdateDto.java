package com.luisjav.reto.DTO.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AppointmentUpdateDto {
	
	@NotNull(message = "Id must not be null.")
	@Min(value = 1, message = "Id must be greater or equal to 1.")
	private Long Id;
	
	@NotNull(message = "Date must not be null.")
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate date;
	
	@NotNull(message = "Hourr must not be null.")
	@JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
	private LocalTime hour;
	
	@NotNull(message = "Test must not be null.")
	@Min(value = 1, message = "Test must be greater or equal to 1.")
	private Long test;
	
	@NotNull(message = "Affiliate must not be null.")
	@Min(value = 1, message = "Affiliate must be greater or equal to 1.")
	private Long affiliate;
}
