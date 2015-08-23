package com.meujornal.infrastructure.persistence.settings;

interface EnvironmentSettings {

	String getUsername();

	String getPassword();

	String getJdbcUrl();

	String getDriverName();

	String getHibernateDialect();

}
