package com.luisjav.reto.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luisjav.reto.Entity.Appointment;

public interface IAppointmentDao extends JpaRepository<Appointment, Long>{

}
