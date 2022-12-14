package com.luisjav.reto.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateValidator {
	public static boolean isValid(String date) {

		boolean valid = false;

		try {

			// ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
			LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));

			valid = true;

		} catch (DateTimeParseException e) {
//            e.printStackTrace();
			valid = false;
		}

		return valid;
	}

}
