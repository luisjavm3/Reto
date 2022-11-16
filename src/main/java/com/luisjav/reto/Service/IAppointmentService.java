package com.luisjav.reto.Service;

import java.time.LocalDate;
import java.util.List;

import com.luisjav.reto.DTO.Appointment.AppointmentDto;
import com.luisjav.reto.DTO.Appointment.AppointmentInsertDto;
import com.luisjav.reto.DTO.Appointment.AppointmentUpdateDto;
import com.luisjav.reto.Entity.Appointment;

public interface IAppointmentService {
	List<AppointmentDto> GetList();
	AppointmentDto GetById(long id);
	void Post(AppointmentInsertDto appointmentInsertDto);
	void Put(AppointmentUpdateDto appointmentUpdateDto);
	void Delete(long id);
	List<Appointment> GetByDate(LocalDate date);
	List<Appointment> GetByAffiliate(long id);
}
