package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;

import com.luisjav.reto.DTO.Affiliate.AffiliateDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateInsertDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateUpdateDto;

public interface IAffiliateService {
	List<AffiliateDto> GetList();
	AffiliateDto GetById(long id);
	void Post(AffiliateInsertDto affiliateInsertDto);
	void Put(AffiliateUpdateDto affiliateUpdateDto) throws NoSuchObjectException;
	void Delete(long id) throws NoSuchObjectException;
}
