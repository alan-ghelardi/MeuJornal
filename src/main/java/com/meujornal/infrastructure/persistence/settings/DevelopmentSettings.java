package com.meujornal.infrastructure.persistence.settings;

class DevelopmentSettings implements EnvironmentSettings {

	@Override
	public String getUsername() {
		return "admin";
	}

	@Override
	public String getPassword() {
		return "123456";
	}

	@Override
	public String getJdbcUrl() {
		return "jdbc:h2:~/MeuJornal";
	}

	@Override
	public String getDriverName() {
		return "org.h2.Driver";
	}

	@Override
	public String getHibernateDialect() {
		return "org.hibernate.dialect.H2Dialect";
	}

}
