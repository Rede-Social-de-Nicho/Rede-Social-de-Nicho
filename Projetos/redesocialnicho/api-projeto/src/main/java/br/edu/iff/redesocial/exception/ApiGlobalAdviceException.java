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
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Object> tratarUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        
        Map<String, Object> body = new LinkedHashMap<>();
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
                .stream()
                .map(erro -> new ErroValidacaoDTO(erro.getField(), erro.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(ReceitaNaoEncontradaException.class)
    public ResponseEntity<Object> tratarReceitaNaoEncontrada(ReceitaNaoEncontradaException ex) {
        
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("erro", "Receita não encontrada");
        body.put("mensagem", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}