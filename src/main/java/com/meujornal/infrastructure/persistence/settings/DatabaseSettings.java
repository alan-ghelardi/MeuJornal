package com.meujornal.infrastructure.persistence.settings;

import javax.enterprise.context.ApplicationScoped;

/**
 * Provê as configurações do banco de dados. Internamente utiliza o pattern
 * state para alternar entre as configurações de desenvolvimento e de produção
 * dependendo da presença ou não da variável de sistema DATABASE_URL.
 * 
 * @author Alan Ghelardi
 *
 */
@ApplicationScoped
public class DatabaseSettings {

	private static final String ENVIRONMENT_VARIABLE = "DATABASE_URL";

	private final EnvironmentSettings settings;

	public DatabaseSettings() {
		if (isInDeployEnvironment()) {
			settings = new DeploymentSettings(
					System.getenv(ENVIRONMENT_VARIABLE));
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
