package com.luisjav.reto.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.luisjav.reto.DTO.Test.TestDto;
import com.luisjav.reto.DTO.Test.TestInsertDto;
import com.luisjav.reto.DTO.Test.TestUpdateDto;
import com.luisjav.reto.Exception.NoContentException;
import com.luisjav.reto.Exception.NotFoundException;
import com.luisjav.reto.Service.ITestService;

@ExtendWith(MockitoExtension.class)
public class TestControllerTest {
	@InjectMocks
	private TestController testController;

	@Mock
	private ITestService testServiceMock;

	@Test
	public void GetList__RetornaStatus204__CuantoResultadoEsUnaListaVacia() {
//		Arrange
		when(testServiceMock.GetList()).thenReturn(Collections.emptyList());

//		Act - Assert
		Assertions.assertThrows(NoContentException.class, () -> testController.GetList());
	}

	@Test
	public void GetList__RetornaStatus200__CuantoHayResultado() throws NoContentException {
//		Arrange
		var list = new ArrayList<TestDto>();
		list.add(new TestDto());
		when(testServiceMock.GetList()).thenReturn(list);

//		Act
		var result = testController.GetList();

//		Assert
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void GetById__RetornaStatus404__CuantoNoHayResultado() {
//		Arrange
		when(testServiceMock.GetById(anyLong())).thenReturn(null);

//		Act - Assert
		Assertions.assertThrows(NotFoundException.class, () -> testController.GetById(13));
	}

	@Test
	public void GetById__RetornaStatus200__CuantoHayResultado() throws NotFoundException {
//		Arrange
		when(testServiceMock.GetById(anyLong())).thenReturn(new TestDto(anyLong()));

//		Act
		var result = testController.GetById(13);

//		Assert
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void Post__RetornaStatus404__CuantoElTestNoSeGuarda() {
//		Arrange
		doThrow(new RuntimeException()).when(testServiceMock).Post(any(TestInsertDto.class));

//		Act
		var result = testController.Post(new TestInsertDto());

//		Assert
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void Post__RetornaStatus201__CuantoElTestSeGuarda() {
//		Arrange
		doNothing().when(testServiceMock).Post(any(TestInsertDto.class));

//		Act
		var result = testController.Post(new TestInsertDto());

//		Assert
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void Put__RetornaStatus404__CuantoElTestNoSeActualiza() {
//		Arrange
		try {
			doThrow(new RuntimeException()).when(testServiceMock).Put(any(TestUpdateDto.class));
		} catch (Exception e) {
		}

//		Act - Assert
		Assertions.assertThrows(RuntimeException.class, () -> testController.Put(new TestUpdateDto()));
	}

	@Test
	public void Put__RetornaStatus201__CuantoElTestSeActualiza() throws NoSuchObjectException {
//		Arrange
		try {
			doNothing().when(testServiceMock).Put(any(TestUpdateDto.class));
		} catch (Exception e) {
		}

//		Act
		var result = testController.Put(new TestUpdateDto());

//		Assert
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void Delete__RetornaStatus204__CuantoElTestNoSeElimina() {
//		Arrange
		try {
			doThrow(new RuntimeException()).when(testServiceMock).Delete(anyLong());
		} catch (Exception e) {
		}

//		Act - Assert
		Assertions.assertThrows(RuntimeException.class, () -> testController.Delete(12));
	}

	@Test
	public void Delete__RetornaStatus200__CuantoElTestSeElimina() throws NoSuchObjectException {
//		Arrange
		try {
			doNothing().when(testServiceMock).Delete(anyLong());
		} catch (Exception e) {
		}

//		Act
		var result = testController.Delete(12);

//		Assert
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

}
