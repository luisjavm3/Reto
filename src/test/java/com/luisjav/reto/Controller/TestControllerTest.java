package com.luisjav.reto.Controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.luisjav.reto.DTO.Test.TestInsertDto;
import com.luisjav.reto.DTO.Test.TestUpdateDto;
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

//		Act
		var result = testController.GetList();

//		Assert
		Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}

	@Test
	public void GetList__RetornaStatus200__CuantoHayResultado() {
//		Arrange
		var list = new ArrayList<com.luisjav.reto.Entity.Test>();
		list.add(new com.luisjav.reto.Entity.Test());
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

//		Act
		var result = testController.GetById(13);

//		Assert
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void GetById__RetornaStatus200__CuantoHayResultado() {
//		Arrange
		when(testServiceMock.GetById(anyLong())).thenReturn(new com.luisjav.reto.Entity.Test(anyLong()));

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

//		Act
		var result = testController.Put(new TestUpdateDto());

//		Assert
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void Put__RetornaStatus201__CuantoElTestSeActualiza() {
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

//		Act
		var result = testController.Delete(12);

//		Assert
		Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}

	@Test
	public void Delete__RetornaStatus200__CuantoElTestSeElimina() {
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
