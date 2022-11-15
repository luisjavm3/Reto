package com.luisjav.reto.DTO.Affiliate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AffiliateInsertDto {
	@NotBlank(message = "Name must not be blank.")
	@Size(min = 2, message = "Name must have at least 2 character and have less or equal to 100 characters.")
	private String name;

	@NotNull(message = "Age must not be null.")
	@Min(value = 0, message = "Age must be greater than o equal to 0.")
	private Integer age;

	@NotBlank(message = "Email must not be blank.")
	@Email(message = "Email must be a valid email address.")
	private String email;
}
