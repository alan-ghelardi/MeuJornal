package com.meujornal.infrastructure.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.meujornal.models.usuarios.Role;

/**
 * Converter da JPA para transformar a instância de {@link Role} em uma String e
 * vice-versa, respeitando a convenção padrão do Spring Security para roles.
 * 
 */
@Converter(autoApply = true)
public final class RoleConverter implements AttributeConverter<Role, String> {

	@Override
	public String convertToDatabaseColumn(Role role) {
		return role.toString();
	}

	@Override
	public Role convertToEntityAttribute(String value) {
		return Role.fromString(value);
	}

}
