package com.luisjav.reto.DTO.Affiliate;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AffiliateDto {

	public AffiliateDto(long id) {
		this.Id = id;
	}

	private long Id;

	private String name;

	private int age;

	private String email;
}
