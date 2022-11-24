package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisjav.reto.DAO.IAffiliateDAO;
import com.luisjav.reto.DTO.Affiliate.AffiliateDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateInsertDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateUpdateDto;
import com.luisjav.reto.Entity.Affiliate;

@Service
@Transactional
public class AffiliateService implements IAffiliateService {

	@Autowired
	private IAffiliateDAO affiliateDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<AffiliateDto> GetList() {
		var affiliates = affiliateDao.findAll();

		var dtos = affiliates.stream().map(item -> mapper.map(item, AffiliateDto.class)).collect(Collectors.toList());

		return dtos;
	}

	@Override
	public AffiliateDto GetById(long id) {
		var affiliate = affiliateDao.findById(id).orElse(null);

		return mapper.map(affiliate, AffiliateDto.class);
	}

	@Override
	public void Post(AffiliateInsertDto affiliateInsertDto) {
		Affiliate toInsert = mapper.map(affiliateInsertDto, Affiliate.class);

		affiliateDao.save(toInsert);
	}

	@Override
	public void Put(AffiliateUpdateDto affiliateUpdateDto) throws NoSuchObjectException {
		long id = affiliateUpdateDto.getId().longValue();
		Affiliate toUpdate = mapper.map(affiliateUpdateDto, Affiliate.class);
		Affiliate existing = affiliateDao.findById(id).orElse(null);

		if (existing == null)
			throw new NoSuchObjectException("Affiliate with Id: " + id + " not found.");

		affiliateDao.save(toUpdate);
	}

	@Override
	public void Delete(long id) throws NoSuchObjectException {
		Affiliate existing = affiliateDao.findById(id).orElse(null);

		if (existing == null)
			throw new NoSuchObjectException("Affiliate with Id: " + id + " not found.");

		affiliateDao.deleteById(id);
	}

}
