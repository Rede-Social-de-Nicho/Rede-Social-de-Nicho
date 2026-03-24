package br.edu.iff.redesocial.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}