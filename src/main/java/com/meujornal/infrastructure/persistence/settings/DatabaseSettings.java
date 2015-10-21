package com.meujornal.infrastructure.persistence.settings;

import javax.enterprise.context.ApplicationScoped;

/**
 * Provê as configurações do banco de dados. Internamente utiliza o pattern
 * state para alternar entre as configurações de desenvolvimento e de produção a
 * partir do valor da variável de ambiente VRAPTOR_ENV.
 * 
 * @author Alan Ghelardi
 *
 */
@ApplicationScoped
public class DatabaseSettings {

	private static final String PRODUCTION_ENVIRONMENT = "VRAPTOR_ENV";

	private final EnvironmentSettings settings;

	public DatabaseSettings() {
		if (isInProductionEnvironment()) {
			settings = new ProductionSettings();
		} else {
			settings = new DevelopmentSettings();
		}
	}

	private boolean isInProductionEnvironment() {
		String environment = System.getenv(PRODUCTION_ENVIRONMENT);
		return environment != null && environment.equals("PRODUCTION");
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
