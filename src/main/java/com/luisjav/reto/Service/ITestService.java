package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;

import com.luisjav.reto.DTO.Test.TestDto;
import com.luisjav.reto.DTO.Test.TestInsertDto;
import com.luisjav.reto.DTO.Test.TestUpdateDto;

public interface ITestService {
	void Post(TestInsertDto testInsertDto);

	List<TestDto> GetList();

	TestDto GetById(long id);

	void Put(TestUpdateDto testUpdateDto) throws NoSuchObjectException;

	void Delete(long id) throws NoSuchObjectException;
}
