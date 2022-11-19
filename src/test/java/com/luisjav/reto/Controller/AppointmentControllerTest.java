package com.luisjav.reto.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.luisjav.reto.DTO.Appointment.AppointmentDto;
import com.luisjav.reto.DTO.Appointment.AppointmentInsertDto;
import com.luisjav.reto.DTO.Appointment.AppointmentUpdateDto;
import com.luisjav.reto.Service.IAppointmentService;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {
	@InjectMocks
	private AppointmentController appointmentController;

	@Mock
	private IAppointmentService appointmentServiceMock;

	@Test
	public void GetList__RetornaStatus204__CuandoNoHayResultado() {
//		Arrange
		when(appointmentServiceMock.GetList()).thenReturn(Collections.emptyList());

//		Act
		var result = appointmentController.GetList();

//		Assert
		Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}

	@Test
	public void GetList__RetornaStatus200__CuandoHayResultado() {
//		Arrange
		var list = new ArrayList<AppointmentDto>();
		list.add(new AppointmentDto());
		when(appointmentServiceMock.GetList()).thenReturn(list);

//		Act
		var result = appointmentController.GetList();

//		Assert
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void Post__RetornaStatus404__CuandoAppointmentNoSeGuarda() {
//		Arrange
		doThrow(new RuntimeException()).when(appointmentServiceMock).Post(any(AppointmentInsertDto.class));

//		Act
		var result = appointmentController.Post(new AppointmentInsertDto());

//		Assert
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void Post__RetornaStatus201__CuandoAppointmentSeGuarda() {
//		Arrange
		doNothing().when(appointmentServiceMock).Post(any(AppointmentInsertDto.class));

//		Act
		var result = appointmentController.Post(new AppointmentInsertDto());

//		Assert
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void Put__RetornaStatus404__CuandoAppointmentNoSeActualiza() {
//		Arrange
		try {
			doThrow(new RuntimeException()).when(appointmentServiceMock).Put(any(AppointmentUpdateDto.class));
		} catch (Exception e) {
		}

//		Act
		var result = appointmentController.Put(new AppointmentUpdateDto());

//		Assert
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void Put__RetornaStatus201__CuandoAppointmentSeActualiza() {
//		Arrange
		try {
			doNothing().when(appointmentServiceMock).Put(any(AppointmentUpdateDto.class));
		} catch (Exception e) {
		}

//		Act
		var result = appointmentController.Put(new AppointmentUpdateDto());

//		Assert
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void Delete__RetornaStatus204__CuandoAppointmentNoSeElimina() {
//		Arrange
		try {
			doThrow(new RuntimeException()).when(appointmentServiceMock).Delete(anyLong());
		} catch (Exception e) {
		}

//		Act
		var result = appointmentController.Delete(23);

//		Assert
		Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}

	@Test
	public void Delete__RetornaStatus200__CuandoAppointmentSeElimina() {
//		Arrange
		try {
			doNothing().when(appointmentServiceMock).Delete(anyLong());
		} catch (Exception e) {
		}

//		Act
		var result = appointmentController.Delete(12);

//		Assert
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void GetByDate__RetornaStatus204__CuandoNoHayResultado() {
//		Arrange
		when(appointmentServiceMock.GetByDate(any(LocalDate.class))).thenReturn(Collections.emptyList());

//		Act
		var result = appointmentController.GetByDate(12, 12, 1990);

//		Assert
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void GetByDate__RetornaStatus200__CuandoNoHayResultado() {
//		Arrange
		var list = new ArrayList<AppointmentDto>();
		list.add(new AppointmentDto());
		when(appointmentServiceMock.GetByDate(any(LocalDate.class))).thenReturn(list);

//		Act
		var result = appointmentController.GetByDate(12, 12, 1990);

//		Assert
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void GetByAffiliates__RetornaStatus204__CuandoNoHayResultado() {
//		Arrange
		when(appointmentServiceMock.GetByAffiliate(anyLong())).thenReturn(Collections.emptyList());

//		Act
		var result = appointmentController.GetByAffiliates(12);

//		Assert
		Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}

	@Test
	public void GetByAffiliates__RetornaStatus200__CuandoHayResultado() {
//		Arrange
		var list = new ArrayList<AppointmentDto>();
		list.add(new AppointmentDto());
		when(appointmentServiceMock.GetByAffiliate(anyLong())).thenReturn(list);

//		Act
		var result = appointmentController.GetByAffiliates(12);

//		Assert
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}
