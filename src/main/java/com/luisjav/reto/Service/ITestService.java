package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;

import com.luisjav.reto.DTO.Test.TestDto;
import com.luisjav.reto.DTO.Test.TestInsertDto;
import com.luisjav.reto.DTO.Test.TestUpdateDto;

public interface ITestService {
	/**
	 * Guarda un Test en la base de datos.
	 * Recibe un DTO con todos los datos necesarios para crear un Test
	 * @param testInsertDto
	 */
	void Post(TestInsertDto testInsertDto);
	
	/**
	 * Retorna todos los Tests guardados en la base de datos.
	 * @return List<TestDto>
	 */
	List<TestDto> GetList();

	/**
	 * Devuelve un TestDto con el ID introducido por parametro.
	 * 
	 * @param id
	 * @return TestDto
	 */
	TestDto GetById(long id);

	/**
	 * Actualiza un Test, sino existe lanza un error.
	 * @param testUpdateDto
	 * @throws NoSuchObjectException
	 */
	void Put(TestUpdateDto testUpdateDto) throws NoSuchObjectException;

	/**
	 * Elimina un Test, sino existe lanza un error.
	 * @param id
	 * @throws NoSuchObjectException
	 */
	void Delete(long id) throws NoSuchObjectException;
}
