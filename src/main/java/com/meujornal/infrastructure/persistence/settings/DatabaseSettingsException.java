package com.meujornal.infrastructure.persistence.settings;

public final class DatabaseSettingsException extends RuntimeException {

	private static final long serialVersionUID = -6808649266288024625L;

	public DatabaseSettingsException(String message, Throwable cause) {
		super(message, cause);
	}

}
