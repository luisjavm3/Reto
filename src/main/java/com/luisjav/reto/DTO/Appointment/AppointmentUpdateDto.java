package com.luisjav.reto.DTO.Appointment;

import java.sql.Date;
import java.sql.Time;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class AppointmentUpdateDto {
	
	@NotNull(message = "Id must not be null.")
	@Min(value = 1, message = "Id must be greater or equal to 1.")
	private Long Id;
	
	@NotNull(message = "Date must not be null.")
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private Date date;
	
	@NotNull(message = "Hourr must not be null.")
	private Time hour;
	
	@NotNull(message = "Test must not be null.")
	@Min(value = 1, message = "Test must be greater or equal to 1.")
	private Long test;
	
	@NotNull(message = "Affiliate must not be null.")
	@Min(value = 1, message = "Affiliate must be greater or equal to 1.")
	private Long affiliate;
}
