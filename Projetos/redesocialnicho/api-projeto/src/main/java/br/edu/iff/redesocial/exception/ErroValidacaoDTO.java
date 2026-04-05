package br.edu.iff.redesocial.exception;

public record ErroValidacaoDTO(String campo, String mensagem) {
    // Este record é usado para encapsular informações sobre 
    // erros de validação, contendo o nome do campo que causou o erro e a mensagem de erro associada.
}