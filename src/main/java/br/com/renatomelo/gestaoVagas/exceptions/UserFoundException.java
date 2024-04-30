package br.com.renatomelo.gestaoVagas.exceptions;

public class UserFoundException extends RuntimeException {

	public UserFoundException() {
		super("Usuário já existe");
	}

}
