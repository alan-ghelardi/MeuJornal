package com.meujornal.infrastructure.persistence.settings;

import java.net.URI;
import java.net.URISyntaxException;

final class DeploymentSettings implements EnvironmentSettings {

	private final URI databaseURI;

	DeploymentSettings() {
		String databaseUrl = System.getenv("DATABASE_URL");

		try {
			databaseURI = new URI(databaseUrl);
		} catch (URISyntaxException e) {
			throw new DatabaseSettingsException(
					"There is a problem with URI sintax " + databaseUrl, e);
		}
	}

	@Override
	public String getUsername() {
		return databaseURI.getUserInfo().split(":")[0];
	}

	@Override
	public String getPassword() {
		return databaseURI.getUserInfo().split(":")[1];
	}

	@Override
	public String getJdbcUrl() {
		return "jdbc:postgresql://" + databaseURI.getHost() + ':'
				+ databaseURI.getPort() + databaseURI.getPath();

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
