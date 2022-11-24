package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisjav.reto.DAO.ITestDAO;
import com.luisjav.reto.DTO.Test.TestDto;
import com.luisjav.reto.DTO.Test.TestInsertDto;
import com.luisjav.reto.DTO.Test.TestUpdateDto;
import com.luisjav.reto.Entity.Test;

@Service
@Transactional
public class TestService implements ITestService {

	@Autowired
	private ITestDAO testDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void Post(TestInsertDto testInsertDto) {
		Test toInsert = mapper.map(testInsertDto, Test.class);
		testDao.save(toInsert);
	}

	@Override
	public List<TestDto> GetList() {
		var tests = testDao.findAll();

		var dtos = tests.stream().map(item -> mapper.map(item, TestDto.class)).collect(Collectors.toList());

		return dtos;
	}

	@Override
	public TestDto GetById(long id) {
		var test = testDao.findById(id).orElse(null);

		return mapper.map(test, TestDto.class);
	}

	@Override
	public void Put(TestUpdateDto testUpdateDto) throws NoSuchObjectException {
		Test toUpdate = mapper.map(testUpdateDto, Test.class);

		Test existing = testDao.findById(toUpdate.getId()).orElse(null);

		if (existing == null)
			throw new NoSuchObjectException("Test with Id: " + toUpdate.getId() + " not found.");

		testDao.save(toUpdate);
	}

	@Override
	public void Delete(long id) throws NoSuchObjectException {
		Test existing = testDao.findById(id).orElse(null);

		if (existing == null)
			throw new NoSuchObjectException("Test with Id: " + id + " not found.");

		testDao.delete(existing);
	}

}
