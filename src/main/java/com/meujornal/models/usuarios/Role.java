package com.meujornal.models.usuarios;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

/**
 * Este Enum representa os papéis que um usuário pode assumir no sistema.
 * 
 * @author Alan Ghelardi
 *
 */
public enum Role {

	ADMINISTRATOR, USER;

	private static final Map<String, Role> stringToConstantValue = new HashMap<>();

	static {
		for (Role role : Role.values()) {
			stringToConstantValue.put(role.toString(), role);
		}
	}

	// Retorna um objeto de papel participativo no sistema a partir de uma string
	public static Role fromString(String name) {
		Preconditions.checkArgument(stringToConstantValue.containsKey(name),
				"%s is an invalid name for enum.", name);
		return stringToConstantValue.get(name);
	}

	// Retorna uma string, descrevendo o papel participativo atualmente associado à entidade
	@Override
	public String toString() {
		return "ROLE_" + this.name().charAt(0)
				+ this.name().substring(1).toLowerCase();
	};

}
