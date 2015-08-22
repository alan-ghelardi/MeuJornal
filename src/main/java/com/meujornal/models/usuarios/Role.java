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

	public static Role fromString(String name) {
		Preconditions.checkArgument(stringToConstantValue.containsKey(name),
				"%s is an invalid name for enum.", name);
		return stringToConstantValue.get(name);
	}

	@Override
	public String toString() {
		return "ROLE_" + this.name().charAt(0)
				+ this.name().substring(1).toLowerCase();
	};

}
