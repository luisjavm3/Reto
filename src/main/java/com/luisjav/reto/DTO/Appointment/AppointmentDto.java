package com.luisjav.reto.DTO.Appointment;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class AppointmentDto {
	private long id;

	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private Date date;
	
	private Time hourr;
	
	private long testId;
	
	private String testName;
	
	private long affiliateId;
	
	private String affiliateName;
}
