package com.luisjav.reto.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisjav.reto.Entity.Test;

@Repository
public interface ITestDAO extends JpaRepository<Test, Long>{

}
