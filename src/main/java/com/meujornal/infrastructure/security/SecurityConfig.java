package com.meujornal.infrastructure.security;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.meujornal.infrastructure.persistence.settings.DatabaseSettings;

/**
 * Esta classe é responsável por configurar o Spring Security Framework.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void globalSecurityConfiguration(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource())
				.usersByUsernameQuery(
						"select u.nomeDeUsuario, u.senha, true from usuario as u where ? in (u.nomeDeUsuario, u.email)")
				.authoritiesByUsernameQuery(
						"select nomeDeUsuario, papel from usuario where nomeDeUsuario = ?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	private DataSource dataSource() {
		DatabaseSettings settings = new DatabaseSettings();

		DataSource dataSource = new DriverManagerDataSource(
				settings.getJdbcUrl(), settings.getUsername(),
				settings.getPassword());
		return dataSource;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.requireCsrfProtectionMatcher(new CSRFRequestMatcher())
				.and()
				.authorizeRequests()
				.antMatchers("/entrar", "/registrar/**", "/jobs/**", "/css/**",
						"/fonts/**", "/js/**").permitAll()
				.antMatchers("/admin/**").hasRole("Administrator").anyRequest()
				.authenticated().and().formLogin().loginPage("/entrar")
				.loginProcessingUrl("/entrar").failureUrl("/entrar?error=true")
				.defaultSuccessUrl("/").usernameParameter("nomeDeUsuario")
				.passwordParameter("senha").and().logout().logoutUrl("/sair")
				.logoutSuccessUrl("/entrar?logout=true")
				.invalidateHttpSession(true);
	}

	/**
	 * Request matcher customizado para a proteção contra cross-site request
	 * forgery. Por padrão, o csrf token não será exigido em requisições GET,
	 * HEAD, OPTIONS e TRACE. Também exclue a URL /jobs/find-news (que dispara o
	 * job responsável pelo consumo das notícias) do processo de verificação.
	 * 
	 * @author Alan Ghelardi
	 *
	 */
	private static class CSRFRequestMatcher implements RequestMatcher {

		private final RegexRequestMatcher unprotected = new RegexRequestMatcher(
				"/jobs/find-news", "POST");

		private final Pattern allowedMethods = Pattern
				.compile("^(GET|HEAD|OPTIONS|TRACE)$");

		@Override
		public boolean matches(HttpServletRequest request) {
			return !unprotected.matches(request)
					&& !allowedMethods.matcher(request.getMethod()).matches();
		}
	}

}
