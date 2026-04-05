package br.edu.iff.redesocial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = "br.edu.iff.redesocial.controller") 
public class ApiGlobalAdviceException {

    // 1. Trata o erro 404 (Quando não acha o Usuário)
    @ExceptionHandler(UsuarioNaoEncontradoException.class) // Indica que este método deve ser chamado quando uma exceção do tipo UsuarioNaoEncontradoException for lançada em qualquer controlador dentro do pacote "br.edu.iff.redesocial.controller".
    public ResponseEntity<Object> tratarUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) { // Captura a exceção e constrói uma resposta personalizada para o cliente, contendo detalhes sobre o erro.
        
        Map<String, Object> body = new LinkedHashMap<>(); // Cria um mapa para armazenar os detalhes do erro, como timestamp, status, tipo de erro e mensagem.
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("erro", "Recurso não encontrado");
        body.put("mensagem", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // 2. Trata o erro 400 (Quando o DTO falha no @NotBlank ou @Email)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDTO>> tratarErrosValidacao(MethodArgumentNotValidException ex) {
        
        List<ErroValidacaoDTO> erros = ex.getBindingResult().getFieldErrors()
                .stream() // Transforma a lista de erros de validação em um stream para processar cada erro individualmente.
                .map(erro -> new ErroValidacaoDTO(erro.getField(), erro.getDefaultMessage())) // Mapeia cada erro de validação para um objeto ErroValidacaoDTO, que contém o nome do campo e a mensagem de erro correspondente.
                .collect(Collectors.toList()); // Coleta os objetos ErroValidacaoDTO em uma lista para ser retornada na resposta.

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(ReceitaNaoEncontradaException.class) // Indica que este método deve ser chamado quando uma exceção do tipo ReceitaNaoEncontradaException for lançada em qualquer controlador dentro do pacote "br.edu.iff.redesocial.controller".
    public ResponseEntity<Object> tratarReceitaNaoEncontrada(ReceitaNaoEncontradaException ex) { // Captura a exceção e constrói uma resposta personalizada para o cliente, contendo detalhes sobre o erro.
        
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("erro", "Receita não encontrada");
        body.put("mensagem", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}