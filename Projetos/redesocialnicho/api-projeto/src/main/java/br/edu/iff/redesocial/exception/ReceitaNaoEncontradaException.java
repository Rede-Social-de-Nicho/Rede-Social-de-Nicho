package br.edu.iff.redesocial.exception;

public class ReceitaNaoEncontradaException extends RuntimeException {
    
    public ReceitaNaoEncontradaException(String mensagem) {
        super(mensagem);
    } // Construtor que recebe uma mensagem personalizada para a exceção.
}