package com.luisjav.reto.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "affiliates")
@DynamicUpdate
public class Affiliate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private int age;
	
	@Column(unique = true, nullable = false)
	private String email;
}
