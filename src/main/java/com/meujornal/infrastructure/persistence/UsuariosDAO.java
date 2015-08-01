package com.meujornal.infrastructure.persistence;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.meujornal.models.usuarios.Usuario;

@RequestScoped
public class UsuariosDAO {

	@Inject
	private EntityManager entityManager;

	public void salvar(Usuario umUsuario) {
		entityManager.persist(umUsuario);
	}

}
