package com.luisjav.reto.DTO.Test;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestDto {
	public TestDto(long id) {
		this.Id = id;
	}

	private long Id;

	private String name;

	private String description;
}
