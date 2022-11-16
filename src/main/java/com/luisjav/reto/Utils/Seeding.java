package com.luisjav.reto.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.luisjav.reto.DAO.IAffiliateDAO;
import com.luisjav.reto.DAO.ITestDAO;
import com.luisjav.reto.Entity.Affiliate;
import com.luisjav.reto.Entity.Test;

@Component
public class Seeding implements CommandLineRunner {
	@Autowired
	private ITestDAO testDao;

	@Autowired
	private IAffiliateDAO affiliateDao;
	
//	@Autowired
//	private IAppointmentDao appointmentDao;

	@Override
	public void run(String... args) throws Exception {
		loadTests();
		loadAffiliates();
	}

	private void loadTests() {
		if (testDao.count() == 0) {
			Test t1 = new Test(0, "Test Name 1", "Test Description 1");
			Test t2 = new Test(0, "Test Name 2", "Test Description 2");
			Test t3 = new Test(0, "Test Name 3", "Test Description 3");
			testDao.save(t1);
			testDao.save(t2);
			testDao.save(t3);
		}
	}
	
	private void loadAffiliates()
	{
		if(affiliateDao.count() == 0)
		{
			Affiliate a1 = new Affiliate(0, "Name 1", 21, "mail1@mail.com");
			Affiliate a2 = new Affiliate(0, "Name 2", 22, "mail2@mail.com");
			Affiliate a3 = new Affiliate(0, "Name 3", 23, "mail3@mail.com");
			affiliateDao.save(a1);
			affiliateDao.save(a2);
			affiliateDao.save(a3);
		}
	}
	
//	private void loadAppointments()
//	{
////		if(appointmentDao.count() == 0) {
////			Appointment a1 = new Appointment(0, "15/11/2022", "");
////		}
//	}

}
