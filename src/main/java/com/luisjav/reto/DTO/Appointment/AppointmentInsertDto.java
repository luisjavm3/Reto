package com.luisjav.reto.DTO.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentInsertDto {
	@NotNull(message = "Date must not be null.")
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate date;

	@NotNull(message = "Hourr must not be null.")
	@JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
	private LocalTime hour;

	@NotNull(message = "Test must not be null.")
	private Long test;

	@NotNull(message = "Affiliate must not be null.")
	private Long affiliate;
}
