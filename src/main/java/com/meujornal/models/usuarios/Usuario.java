package com.meujornal.models.usuarios;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.meujornal.infrastructure.persistence.converters.RoleConverter;

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
	@Size(max = 60)
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
	@Convert(converter = RoleConverter.class)
	private Role papel = Role.USER;
	@NotBlank
	private String perguntaDeSeguranca;
	@NotBlank
	private String respostaDaPerguntaDeSeguranca;

	// Retorna o id do usuário
	public Long getId() {
		return id;
	}

	// Retorna o login do usuário
	public String getNome() {
		return nome;
	}

	// Atribui um login ao usuário
	public void setNome(String nome) {
		this.nome = nome;
	}

	// Retorna o id do usuário
	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	// Atribui um nome ao usuário
	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	// Retorna a senha do usuário
	public String getSenha() {
		return senha;
	}

	// Atribui uma senha ao usuário
	public void setSenha(String senha) {
		this.senha = SensitiveDataEncoder.encode(senha);
	}

	// Retorna o e-mail do usuário
	public String getEmail() {
		return email;
	}

	// Atribui um endereço de e-mail ao usuário
	public void setEmail(String email) {
		this.email = email;
	}

	// Retorna o papel participativo no sistema do usuário
	public Role getPapel() {
		return papel;
	}

	// Atribui um papel participativo ao usuário
	public void setPapel(Role papel) {
		this.papel = papel;
	}

	// Retorna a pergunta de segurança do usuário
	public String getPerguntaDeSeguranca() {
		return perguntaDeSeguranca;
	}

	// Atribui uma pergunta de segurança ao usuário
	public void setPerguntaDeSeguranca(String perguntaDeSeguranca) {
		this.perguntaDeSeguranca = perguntaDeSeguranca;
	}

	// Retorna a resposta para a pergunta de segurança do usuário
	public String getRespostaDaPerguntaDeSeguranca() {
		return respostaDaPerguntaDeSeguranca;
	}

	// Atribui uma resposta à pergunta de segurança do usuário
	public void setRespostaDaPerguntaDeSeguranca(
			String respostaDaPerguntaDeSeguranca) {
		this.respostaDaPerguntaDeSeguranca = SensitiveDataEncoder
				.encode(respostaDaPerguntaDeSeguranca);
	}

	// Retorna a string que está relaciona ao objeto Usuario
	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(nome)
				.addValue(nomeDeUsuario).addValue(email).addValue(papel)
				.toString();
	}

}
