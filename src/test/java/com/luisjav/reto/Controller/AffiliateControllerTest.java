package com.luisjav.reto.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.luisjav.reto.DTO.Affiliate.AffiliateInsertDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateUpdateDto;
import com.luisjav.reto.Entity.Affiliate;
import com.luisjav.reto.Service.IAffiliateService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AffiliateControllerTest {
	@InjectMocks
	private AffiliateController affiliateController = new AffiliateController();

	@Mock
	private IAffiliateService affiliateServiceMock;

	@Test
	public void GetList__RetornaStatus204__CuandoElResultadoEsUnaListaVacia() {
//		Arrange
		when(affiliateServiceMock.GetList()).thenReturn(Collections.emptyList());

		// Act
		var response = affiliateController.GetList();

		// Assert
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void GetList__RetornaStatus200__CuandoHayResultado() {
//		Arrange
		var list = new ArrayList<Affiliate>();
		list.add(new Affiliate(1));

		when(affiliateServiceMock.GetList()).thenReturn(list);

//		Act
		var response = affiliateController.GetList();

//		Assert
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void GetById__RetornaStatus404__CuandoNoHayResultado() {
//		Arrange
		when(affiliateServiceMock.GetById(anyLong())).thenReturn(null);

//		Act
		var result = affiliateController.GetById(anyLong());

//		Assert
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void GetById__RetornaStatus200_CuandoHayResultado() {
//		Arrange
		when(affiliateServiceMock.GetById(anyLong())).thenReturn(new Affiliate(anyLong()));

//		Act
		var result = affiliateController.GetById(4);

//		Assert
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void Post__RetornaStatus404__CuandoNoSeGuardoElAffiliado() {
//		Arrange
		doThrow(new RuntimeException()).when(affiliateServiceMock).Post(any(AffiliateInsertDto.class));

//		Act
		var result = affiliateController.Post(new AffiliateInsertDto());

//		Assert
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void Post__RetornaStatus201__CuandoSeGuardoElAffiliado() {
//		Arrange
		doNothing().when(affiliateServiceMock).Post(any(AffiliateInsertDto.class));

//		Act
		var result = affiliateController.Post(new AffiliateInsertDto());

//		Assert
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void Put__RetornaStatus404__CuandoNoSeActualizoElAffiliado() {
//		Arrange
		try {
			doThrow(new RuntimeException()).when(affiliateServiceMock).Put(any(AffiliateUpdateDto.class));
		} catch (Exception e) {

		}

//		Act
		var result = affiliateController.Put(new AffiliateUpdateDto());

//		Assert
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void Put__RetornaStatus201__CuandoSeActualizoElAffiliado() {
//		Arrange
		try {
			doNothing().when(affiliateServiceMock).Put(any(AffiliateUpdateDto.class));
		} catch (Exception e) {

		}

//		Act
		var result = affiliateController.Put(new AffiliateUpdateDto());

//		Assert
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void Delete__RetornaStatus204__CuandoElAffiliadoNoSeElimina() {
//		Arrange
		try {
			doThrow(new RuntimeException()).when(affiliateServiceMock).Delete(anyLong());
		} catch (Exception e) {

		}

//		Act
		var result = affiliateController.Delete(15);

//		Assert
		Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}

	@Test
	public void Delete__RetornaStatus200__CuandoElAffiliadoSeElimina() {
//		Arrange
		try {
			doNothing().when(affiliateServiceMock).Delete(anyLong());
		} catch (Exception e) {

		}

//		Act
		var result = affiliateController.Delete(15);

//		Assert
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}
