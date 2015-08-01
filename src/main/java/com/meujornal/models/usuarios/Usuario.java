package com.meujornal.models.usuarios;

import static javax.persistence.EnumType.STRING;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;

/**
 * Representa um usuário no contexto do sistema.
 * 
 * @author Alan Ghelardi
 *
 */
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = -8000608833275588277L;

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	@Pattern(regexp = "^(?=.*?[a-z]{3,})[a-z0-9\\-_]+$")
	@Size(max = 30)
	@Column(unique = true)
	private String nomeDeUsuario;
	@NotBlank
	private String senha;
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	@NotNull
	@Enumerated(STRING)
	private Papel papel = Papel.USUARIO;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(nome)
				.addValue(nomeDeUsuario).addValue(email).addValue(papel)
				.toString();
	}

}
