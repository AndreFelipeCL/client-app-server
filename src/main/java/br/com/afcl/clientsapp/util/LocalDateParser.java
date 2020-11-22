package br.com.afcl.clientsapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author André Felipe C. Leite
 * @since 21/11/2020 - 14:36
 * @version 1.0
 */
public class LocalDateParser {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	public static LocalDate of(final String localDateAsString){
		return LocalDate.parse(localDateAsString, DateTimeFormatter.ofPattern(DD_MM_YYYY));
	}

}
