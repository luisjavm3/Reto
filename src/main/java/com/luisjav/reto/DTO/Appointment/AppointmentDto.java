package com.luisjav.reto.DTO.Appointment;

import java.sql.Date;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class AppointmentDto {
	private long id;

	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private Date date;

	@JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
	private LocalTime hour;
	
	private long testId;
	
	private String testName;
	
	private long affiliateId;
	
	private String affiliateName;
}
