package com.meujornal.infrastructure.persistence.converters;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA converter para instâncias de {@link LocalDateTime}.
 * 
 * @author Alan Ghelardi
 *
 */
@Converter
public final class LocalDateTimeConverter implements
		AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {
		return Timestamp.valueOf(dateTime);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		return timestamp.toLocalDateTime();
	}

}
