package com.luisjav.reto.DTO.Test;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestUpdateDto {
	@NotNull
	@Min(value = 1, message = "Id must be greater or equal to 1.")
	private long id;
	
	@NotBlank(message = "Name must not be blank.")
	@Size(min = 2, max = 100, message = "Name must have at least 2 charaters and have less than or equal to 100 characters.")
	private String name;

	@NotBlank(message = "Name must not be blank.")
	@Size(min = 2, max = 200, message = "Description must have at least 2 charaters and have less than or equal to 200 characters.")
	private String description;
}
