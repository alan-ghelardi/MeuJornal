package com.meujornal.infrastructure.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
		http.authorizeRequests()
				.antMatchers("/entrar", "/registrar/**", "/css/**",
						"/fonts/**", "/js/**").permitAll()
				.antMatchers("/admin/**").hasRole("Administrator").anyRequest()
				.authenticated().and().formLogin().loginPage("/entrar")
				.loginProcessingUrl("/entrar").failureUrl("/entrar?error=true")
				.defaultSuccessUrl("/").usernameParameter("nomeDeUsuario")
				.passwordParameter("senha").and().logout().logoutUrl("/sair")
				.logoutSuccessUrl("/entrar?logout=true")
				.invalidateHttpSession(true);
	}

}
