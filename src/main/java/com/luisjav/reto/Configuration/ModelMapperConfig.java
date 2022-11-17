package com.luisjav.reto.Configuration;

import java.sql.Date;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luisjav.reto.DTO.Appointment.AppointmentInsertDto;
import com.luisjav.reto.DTO.Appointment.AppointmentUpdateDto;
import com.luisjav.reto.Entity.Affiliate;
import com.luisjav.reto.Entity.Appointment;
import com.luisjav.reto.Entity.Test;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		Converter<AppointmentUpdateDto, Appointment> appoUpdate = new Converter<AppointmentUpdateDto, Appointment>() {
			@Override
			public Appointment convert(MappingContext<AppointmentUpdateDto, Appointment> context) {
				Appointment result = new Appointment();
				result.setId(context.getSource().getId());
				result.setDate(Date.valueOf(context.getSource().getDate()));
				result.setHourr(context.getSource().getHour());
				result.setAffiliate(new Affiliate(context.getSource().getAffiliate().longValue()));
				result.setTest(new Test(context.getSource().getTest().longValue()));

				return context.getSource() == null ? null : result;
			}
		};

		Converter<AppointmentInsertDto, Appointment> appoInsert = new Converter<AppointmentInsertDto, Appointment>() {
			@Override
			public Appointment convert(MappingContext<AppointmentInsertDto, Appointment> context) {
				Appointment result = new Appointment();
				result.setDate(Date.valueOf(context.getSource().getDate()));
				result.setHourr(context.getSource().getHour());
				result.setAffiliate(new Affiliate(context.getSource().getAffiliate().longValue()));
				result.setTest(new Test(context.getSource().getTest().longValue()));

				return context.getSource() == null ? null : result;
			}
		};

		mapper.addConverter(appoUpdate);
		mapper.addConverter(appoInsert);

		return mapper;
	}
}
