package com.meujornal.infrastructure.persistence.settings;

import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.caelum.vraptor.events.VRaptorInitialized;

/**
 * Observer invocado durante a inicialização do sistema. Cria o arquivo
 * proxool.properties no diretório META-INF. O Proxool é um pool de conexões
 * utilizado para tornar o gerenciamento de conexões do Hibernate mais robusto,
 * visto que o pool de conexões padrão do ORM só deve ser utilizado para testes.
 * 
 * @author Alan Ghelardi
 *
 */
@ApplicationScoped
public class ProxoolConfigurator {

	private static final String ALIAS = "jdbc-0.proxool.alias";
	private static final String JDBC_URL = "jdbc-0.proxool.driver-url";
	private static final String JDBC_DRIVER_CLASS = "jdbc-0.proxool.driver-class";
	private static final String JDBC_USER = "jdbc-0.user";
	private static final String JDBC_PASSWORD = "jdbc-0.password";
	private static final String MAXIMUM_CONNECTION_COUNT = "jdbc-0.proxool.maximum-connection-count";
	private static final String HOUSING_KEEP_TEST_SQL = "jdbc-0.proxool.house-keeping-test-sql";

	@Inject
	private DatabaseSettings settings;

	/**
	 * @deprecated CDI eyes only.
	 */
	public ProxoolConfigurator() {
		this(null);
	}

	public ProxoolConfigurator(DatabaseSettings settings) {
		this.settings = settings;
	}

	public void configure(@Observes VRaptorInitialized event)
			throws IOException {
		Path path = getPathOfProxoolPropertiesFile();
		try {
			tryStoreThePropertiesInDisk(path);
		} catch (Exception e) {
			throw new RuntimeException(
					"Failed to create the proxool.properties file", e);
		}
	}

	private Path getPathOfProxoolPropertiesFile() {
		try {
			URL url = Thread.currentThread().getContextClassLoader()
					.getResource("META-INF");
			return Paths.get(url.toURI()).resolve("proxool.properties");
		} catch (URISyntaxException e) {
			throw new AssertionError("Malformed URI");
		}
	}

	private void tryStoreThePropertiesInDisk(Path path) throws IOException {
		try (Writer writer = Files.newBufferedWriter(path,
				Charset.forName("UTF-8"))) {
			Properties props = createProxoolProperties();

			props.store(writer,
					"Proxool (connection pool for Hibernate) settings file.");
		}
	}

	private Properties createProxoolProperties() {
		Properties props = new Properties();

		props.setProperty(ALIAS, "proxool-settings");
		props.setProperty(JDBC_URL, settings.getJdbcUrl());
		props.setProperty(JDBC_DRIVER_CLASS, settings.getDriverName());
		props.setProperty(JDBC_USER, settings.getUsername());
		props.setProperty(JDBC_PASSWORD, settings.getPassword());
		props.setProperty(MAXIMUM_CONNECTION_COUNT, "10");
		props.setProperty(HOUSING_KEEP_TEST_SQL, "select CURRENT_DATE");
		return props;
	}

}
