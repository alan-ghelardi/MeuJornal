package com.meujornal.infrastructure.persistence.settings;

/**
 * Representa as configurações do banco de dados no ambiente de produção
 * (atualmente, o OpenShift). Provê as configurações para o uso do PostgreSQL.
 * 
 * @author Alan Ghelardi
 *
 */
final class ProductionSettings implements EnvironmentSettings {

	private static final String DATABASE_HOST = "HOST";
	private static final String DATABASE_PORT = "PORT";
	private static final String DATABASE_USERNAME = "USERNAME";
	private static final String DATABASE_PASSWORD = "PASSWORD";

	@Override
	public String getUsername() {
		return environmentVariableValueFor(DATABASE_USERNAME);
	}

	private String environmentVariableValueFor(String name) {
		return System.getenv("OPENSHIFT_POSTGRESQL_DB_" + name);
	}

	@Override
	public String getPassword() {
		return environmentVariableValueFor(DATABASE_PASSWORD);
	}

	@Override
	public String getJdbcUrl() {
		return "jdbc:postgresql://"
				+ environmentVariableValueFor(DATABASE_HOST) + ":"
				+ environmentVariableValueFor(DATABASE_PORT);
	}

	@Override
	public String getDriverName() {
		return "org.postgresql.Driver";
	}

	@Override
	public String getHibernateDialect() {
		return "org.hibernate.dialect.PostgreSQLDialect";
	}

}
