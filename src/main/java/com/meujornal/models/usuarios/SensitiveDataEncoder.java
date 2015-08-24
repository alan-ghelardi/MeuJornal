package com.meujornal.models.usuarios;

import static com.google.common.base.Strings.nullToEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe auxiliar que encapsula o algoritmo de hash utilizado pelo sistema para
 * codificar dados que serão armazenados na base de dados.
 * 
 * @author Alan Ghelardi
 *
 */
final class SensitiveDataEncoder {

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	// Construtor do codificador para dados sigiliosos
	private SensitiveDataEncoder() {
	}

	// Retorna uma string codificada a partir de uma string
	static String encode(String rawData) {
		return encoder.encode(rawData);
	}

	// Compara e valida os dados brutos em comparação aos dados codificados, retornando verdadeiro em caso positivo e falso caso contrário
	static boolean matches(String rawData, String encodedData) {
		return encoder.matches(nullToEmpty(rawData), nullToEmpty(encodedData));
	}

}
