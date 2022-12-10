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

import com.luisjav.reto.DTO.Affiliate.AffiliateDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateInsertDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateUpdateDto;
import com.luisjav.reto.Exception.NoContentException;
import com.luisjav.reto.Exception.NotFoundException;
import com.luisjav.reto.Service.IAffiliateService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AffiliateControllerTest {
	@InjectMocks
	private AffiliateController affiliateController = new AffiliateController();

	@Mock
	private IAffiliateService affiliateServiceMock;

	@Test()
	public void GetList__RetornaStatus204__CuandoElResultadoEsUnaListaVacia() {
//		Arrange
		when(affiliateServiceMock.GetList()).thenReturn(Collections.emptyList());

		// Act - Assert
		Assertions.assertThrows(NoContentException.class, () -> affiliateController.GetList());
	}

	@Test
	public void GetList__RetornaStatus200__CuandoHayResultado() throws NoContentException {
//		Arrange
		var list = new ArrayList<AffiliateDto>();
		list.add(new AffiliateDto(1));

		when(affiliateServiceMock.GetList()).thenReturn(list);

//		Act
		var response = affiliateController.GetList();

//		Assert
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void GetById__RetornaStatus404__CuandoNoHayResultado() throws NotFoundException {
//		Arrange
		when(affiliateServiceMock.GetById(anyLong())).thenReturn(null);

//		Act - Assert
		Assertions.assertThrows(NotFoundException.class, () -> affiliateController.GetById(anyLong()));
	}

	@Test
	public void GetById__RetornaStatus200_CuandoHayResultado() throws NotFoundException {
//		Arrange
		when(affiliateServiceMock.GetById(anyLong())).thenReturn(new AffiliateDto(anyLong()));

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
	public void Put__RetornaStatus404__CuandoNoSeActualizoElAffiliado() throws NotFoundException {
//		Arrange
		try {
			doThrow(new RuntimeException()).when(affiliateServiceMock).Put(any(AffiliateUpdateDto.class));
		} catch (Exception e) {

		}

//		Act - Assert
		Assertions.assertThrows(RuntimeException.class, () -> affiliateController.Put(new AffiliateUpdateDto()));
	}

	@Test
	public void Put__RetornaStatus201__CuandoSeActualizoElAffiliado() throws NotFoundException {
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
	public void Delete__RetornaStatus204__CuandoElAffiliadoNoSeElimina() throws NoContentException {
//		Arrange
		try {
			doThrow(new RuntimeException()).when(affiliateServiceMock).Delete(anyLong());
		} catch (Exception e) {

		}

//		Act - Assert
		Assertions.assertThrows(RuntimeException.class, () -> affiliateController.Delete(14));
	}

	@Test
	public void Delete__RetornaStatus200__CuandoElAffiliadoSeElimina() throws NoContentException {
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
