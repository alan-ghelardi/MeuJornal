package com.meujornal.infrastructure.persistence.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.meujornal.models.usuarios.Usuario;

@RequestScoped
public class UsuariosDAO {

	@Inject
	private EntityManager entityManager;

	// Recebe um objeto Usuario e o persiste
	public void salvar(Usuario umUsuario) {
		entityManager.persist(umUsuario);
	}

	// Recebe uma string com o Login do usuário ou e-mail e retorna verdadeiro caso ele já exista no sistema e falso c.c.
	public boolean jaExisteUmUsuarioComNomeDeUsuarioOuEmail(
			String nomeDeUsuarioOuEmail) {
		String query = "SELECT u FROM Usuario AS u WHERE u.nomeDeUsuario = :nomeDeUsuarioOuEmail OR u.email = :nomeDeUsuarioOuEmail";

		return entityManager.createQuery(query, Usuario.class)
				.setParameter("nomeDeUsuarioOuEmail", nomeDeUsuarioOuEmail)
				.getResultList().size() != 0;
	}

}
