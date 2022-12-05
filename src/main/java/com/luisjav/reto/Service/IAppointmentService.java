package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
import java.time.LocalDate;
import java.util.List;

import com.luisjav.reto.DTO.Appointment.AppointmentDto;
import com.luisjav.reto.DTO.Appointment.AppointmentInsertDto;
import com.luisjav.reto.DTO.Appointment.AppointmentUpdateDto;

public interface IAppointmentService {
	/**
	 * Metodo que devuelve todos los Appointment en la base de datos.
	 * @return List<<AppointmentDto>>
	 */
	List<AppointmentDto> GetList();
	
	/**
	 * Metodo que devuelve un AppointmentDto con el Id insertado por parametro.
	 * @param id
	 * @return AppointmentDto
	 */
	AppointmentDto GetById(long id);
	
	/**
	 * Inserta un Appointment en la base de datos, el Dto insertado por parametro tiene
	 * todo lo necesario para crear la entidad y guardarla.
	 * @param appointmentInsertDto
	 */
	void Post(AppointmentInsertDto appointmentInsertDto);
	
	/**
	 * Actualiza un Appointment, en caso de no encontrar el appointment lanza una exception,
	 * permitiendonos saber que la entidad no fué actualizada.
	 * @param appointmentUpdateDto
	 * @throws NoSuchObjectException
	 */
	void Put(AppointmentUpdateDto appointmentUpdateDto) throws NoSuchObjectException;
	
	/**
	 * Elimina un Appointment, en caso de intentar eliminar un appointment inexistente, lanza
	 * una exception para dejarnos saber que no se eliminó un registro.
	 * @param id
	 * @throws NoSuchObjectException
	 */
	void Delete(long id) throws NoSuchObjectException;
	
	/**
	 * Retorna una lista de Appointments que comparten el mismo afiliado
	 * insertado por parametro (id).
	 * @param id
	 * @return List<<AppointmentDto>>
	 */
	List<AppointmentDto> GetByAffiliate(long id);
	
	/**
	 * Retorna una lista de Appointments que comparten la misma fecha insertada por parametro (date)
	 * @param date
	 * @return List<<AppointmentDto>>
	 */
	List<AppointmentDto> GetByDate(LocalDate date);
}
