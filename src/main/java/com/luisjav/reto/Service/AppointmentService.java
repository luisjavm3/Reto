package com.luisjav.reto.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisjav.reto.DAO.IAppointmentDao;
import com.luisjav.reto.DTO.Appointment.AppointmentInsertDto;
import com.luisjav.reto.DTO.Appointment.AppointmentUpdateDto;
import com.luisjav.reto.Entity.Affiliate;
import com.luisjav.reto.Entity.Appointment;
import com.luisjav.reto.Entity.Test;

@Service
public class AppointmentService implements IAppointmentService {
	@Autowired
	private IAppointmentDao appointmentDao;

//	@Autowired
//	private ModelMapper mapper;

	@Override
	public List<Appointment> GetList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment GetById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Post(AppointmentInsertDto appointmentInsertDto) {
		var toInsert = mapToEntity(appointmentInsertDto);

		try {
			ObjectMapper om = new ObjectMapper();
			System.out.println("___ Object: ___ " + om.writeValueAsString(toInsert));
		} catch (Exception e) {
			System.out.println("___ Error ___:" + e.getMessage());
		}

		appointmentDao.save(toInsert);
	}

	@Override
	public void Put(AppointmentUpdateDto appointmentUpdateDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Appointment> GetByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> GetByAffiliate(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	private Appointment mapToEntity(AppointmentInsertDto appointmentInsertDto) {
		Appointment appointment = new Appointment();
		Test test = new Test();
		test.setId(appointmentInsertDto.getTest().longValue());
		Affiliate affiliate = new Affiliate();
		affiliate.setId(appointmentInsertDto.getAffiliate().longValue());

		appointment.setDate(appointmentInsertDto.getDate());
		appointment.setHourr(appointmentInsertDto.getHour());
		appointment.setTest(test);
		appointment.setAffiliate(affiliate);

		return appointment;
	}
}
