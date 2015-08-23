package com.meujornal.infrastructure.persistence.settings;

public final class DatabaseSettings {

	private static final String ENVIRONMENT_VARIABLE = "DATABASE_URL";

	private final EnvironmentSettings settings;

	public DatabaseSettings() {
		if (isInDeployEnvironment()) {
			settings = new DeploymentSettings();
		} else {
			settings = new DevelopmentSettings();
		}
	}

	private boolean isInDeployEnvironment() {
		return System.getenv(ENVIRONMENT_VARIABLE) != null;
	}

	public String getUsername() {
		return settings.getUsername();
	}

	public String getPassword() {
		return settings.getPassword();
	}

	public String getJdbcUrl() {
		return settings.getJdbcUrl();
	}

	public String getDriverName() {
		return settings.getDriverName();
	}

	public String getHibernateDialect() {
		return settings.getHibernateDialect();
	}

}
