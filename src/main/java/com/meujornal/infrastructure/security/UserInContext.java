package com.meujornal.infrastructure.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.meujornal.infrastructure.persistence.dao.UsuariosDAO;
import com.meujornal.models.usuarios.Usuario;

@RequestScoped
public class UserInContext {

	@Inject
	private UsuariosDAO usuariosDAO;

	public Usuario getAuthenticated() {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String username = details.getUsername();
		return usuariosDAO.buscarPorNomeDeUsuarioOuEmail(username);
	}

}
