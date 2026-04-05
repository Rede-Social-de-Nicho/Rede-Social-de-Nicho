package br.edu.iff.redesocial.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    // Construtor que recebe uma mensagem personalizada para a exceção.
}