package com.luisjav.reto.Configuration;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luisjav.reto.DTO.Appointment.AppointmentDto;
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
				result.setHourr(Time.valueOf(context.getSource().getHour()));
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
				result.setHourr(Time.valueOf(context.getSource().getHour()));
				result.setAffiliate(new Affiliate(context.getSource().getAffiliate().longValue()));
				result.setTest(new Test(context.getSource().getTest().longValue()));

				return context.getSource() == null ? null : result;
			}
		};

		Converter<Appointment, AppointmentDto> appoToDtoConv = new Converter<Appointment, AppointmentDto>() {

			@Override
			public AppointmentDto convert(MappingContext<Appointment, AppointmentDto> context) {
				var hour = context.getSource().getHourr().getHours();
				var minutes = context.getSource().getHourr().getMinutes();

				AppointmentDto result = new AppointmentDto();
				result.setId(context.getSource().getId());
				result.setDate(context.getSource().getDate());
				result.setHour(LocalTime.of(hour, minutes));
				result.setTestId(context.getSource().getTest().getId());
				result.setTestName(context.getSource().getTest().getName());
				result.setAffiliateId(context.getSource().getAffiliate().getId());
				result.setAffiliateName(context.getSource().getAffiliate().getName());

				return context.getSource() == null ? null : result;
			}
		};

		mapper.addConverter(appoUpdate);
		mapper.addConverter(appoInsert);
		mapper.addConverter(appoToDtoConv);

		return mapper;
	}
}
