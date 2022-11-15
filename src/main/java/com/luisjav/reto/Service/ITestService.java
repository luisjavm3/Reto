package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;

import com.luisjav.reto.DTO.Test.TestInsertDto;
import com.luisjav.reto.DTO.Test.TestUpdateDto;
import com.luisjav.reto.Entity.Test;

public interface ITestService {
	void Post(TestInsertDto testInsertDto);
	List<Test> GetList();
	Test GetById(long id);
	void Put(TestUpdateDto testUpdateDto) throws NoSuchObjectException;
	void Delete(long id) throws NoSuchObjectException;
}
