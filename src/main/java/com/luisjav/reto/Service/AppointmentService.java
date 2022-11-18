package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisjav.reto.DAO.IAppointmentDao;
import com.luisjav.reto.DTO.Appointment.AppointmentDto;
import com.luisjav.reto.DTO.Appointment.AppointmentInsertDto;
import com.luisjav.reto.DTO.Appointment.AppointmentUpdateDto;
import com.luisjav.reto.Entity.Appointment;

@Service
public class AppointmentService implements IAppointmentService {
	@Autowired
	private IAppointmentDao appointmentDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<AppointmentDto> GetList() {
		var appointments = appointmentDao.findAll();

		var dtos = appointments.stream().map(item -> mapper.map(item, AppointmentDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

	@Override
	public AppointmentDto GetById(long id) {
		var appointment = appointmentDao.findById(id).orElse(null);

		return mapper.map(appointment, AppointmentDto.class);
	}

	@Override
	public void Post(AppointmentInsertDto appointmentInsertDto) {
		var toInsert = mapper.map(appointmentInsertDto, Appointment.class);

		appointmentDao.save(toInsert);
	}

	@Override
	public void Put(AppointmentUpdateDto appointmentUpdateDto) throws NoSuchObjectException {
		var toUpdate = mapper.map(appointmentUpdateDto, Appointment.class);
		var existing = appointmentDao.findById(toUpdate.getId()).orElse(null);

		if (existing == null)
			throw new NoSuchObjectException("Appointment with Id: " + toUpdate.getId() + " not found.");

		appointmentDao.save(toUpdate);
	}

	@Override
	public void Delete(long id) throws NoSuchObjectException {
		var existing = appointmentDao.findById(id).orElse(null);

		if (existing == null)
			throw new NoSuchObjectException("Appointment with Id: " + id + " not found.");

		appointmentDao.deleteById(id);
	}

	@Override
	public List<AppointmentDto> GetByDate(Date date) {
		var appointments = appointmentDao.findByDate(date);

		var dtos = appointments.stream().map(item -> mapper.map(item, AppointmentDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

	@Override
	public List<AppointmentDto> GetByAffiliate(long id) {
		var appointments = appointmentDao.findByAffiliateId(id);

		var dtos = appointments.stream().map(item -> mapper.map(item, AppointmentDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

}
