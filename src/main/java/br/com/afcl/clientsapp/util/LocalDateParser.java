package br.com.afcl.clientsapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author André Felipe C. Leite
 * @since 21/11/2020 - 14:36
 * @version 1.0
 */
public class LocalDateParser {

	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static LocalDate of(final String localDateAsString){
		return LocalDate.parse(localDateAsString, DateTimeFormatter.ofPattern(YYYY_MM_DD));
	}

}
