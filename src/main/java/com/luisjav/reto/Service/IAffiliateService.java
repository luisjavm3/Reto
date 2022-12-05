package com.luisjav.reto.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;

import com.luisjav.reto.DTO.Affiliate.AffiliateDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateInsertDto;
import com.luisjav.reto.DTO.Affiliate.AffiliateUpdateDto;

public interface IAffiliateService {
	/**
	 * Retorna todos los Affiliate guardados en la base de datos.
	 * @return List«AffiliateDto»
	 */
	List<AffiliateDto> GetList();
	
	/**
	 * Retorna un AffiliateDto con el Id introducido por parametro o null si no existe.
	 * @param id
	 * @return AffiliateDto
	 */
	AffiliateDto GetById(long id);
	
	/**
	 * Guarda un Affiliate en la base de datos.
	 * @param affiliateInsertDto
	 */
	void Post(AffiliateInsertDto affiliateInsertDto);
	
	/**
	 * Actualiza un Affiliate en la base de datos, si no existe lanza un error.
	 * @param affiliateUpdateDto
	 * @throws NoSuchObjectException
	 */
	void Put(AffiliateUpdateDto affiliateUpdateDto) throws NoSuchObjectException;
	
	/**
	 * Elimina un Affiliate de la base de datos, o lanza un error si no existe. 
	 * @param id
	 * @throws NoSuchObjectException
	 */
	void Delete(long id) throws NoSuchObjectException;
}
