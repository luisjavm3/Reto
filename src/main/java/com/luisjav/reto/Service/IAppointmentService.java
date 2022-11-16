package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
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
	void Put(AppointmentUpdateDto appointmentUpdateDto) throws NoSuchObjectException;
	void Delete(long id) throws NoSuchObjectException;
	List<Appointment> GetByDate(LocalDate date);
	List<AppointmentDto> GetByAffiliate(long id);
}
