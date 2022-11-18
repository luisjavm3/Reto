package com.luisjav.reto.DAO;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luisjav.reto.Entity.Appointment;

@Repository
public interface IAppointmentDao extends JpaRepository<Appointment, Long> {

//	@Query(value = "SELECT * FROM appointments a WHERE a.id_affiliate = :affiliateId", nativeQuery = true)
//	public Collection<Appointment> findByAffiliateId(@Param("affiliateId") long affiliateId);

	@Query("SELECT a FROM Appointment a WHERE a.affiliate.id = :affiliateId")
	public Collection<Appointment> findByAffiliateId(@Param("affiliateId") long affiliateId);

	@Query("SELECT a FROM Appointment a WHERE a.date = :date ORDER BY a.affiliate")
	public Collection<Appointment> findByDate(@Param("date") LocalDate date);
}
