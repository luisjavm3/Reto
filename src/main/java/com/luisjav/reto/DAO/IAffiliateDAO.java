package com.luisjav.reto.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisjav.reto.Entity.Affiliate;

@Repository
public interface IAffiliateDAO extends JpaRepository<Affiliate, Long>{

}
