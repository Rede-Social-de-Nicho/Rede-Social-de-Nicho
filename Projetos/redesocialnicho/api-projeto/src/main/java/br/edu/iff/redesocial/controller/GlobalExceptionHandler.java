package br.edu.iff.redesocial.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Captura nossos erros de "Não encontrado" e joga um aviso na tela em vez de quebrar o sistema.
    @ExceptionHandler(IllegalArgumentException.class) // Especifica que este método deve ser chamado quando uma IllegalArgumentException for lançada em qualquer parte do aplicativo.
    public String handleIllegalArgument(IllegalArgumentException ex, RedirectAttributes attributes) { // Captura a exceção e adiciona uma mensagem de erro para ser exibida ao usuário.
        attributes.addFlashAttribute("mensagemErro", "Atenção: " + ex.getMessage()); // Adiciona uma mensagem de erro ao RedirectAttributes, que pode ser exibida na próxima página renderizada.
        return "redirect:/receitas/lista"; // Redireciona para a lista de receitas após capturar o erro. O usuário verá a mensagem de erro na página de lista.
    }

    // Captura qualquer outro erro grave (Erro 500) e manda para a página customizada
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) { // Captura a exceção e adiciona uma mensagem de erro detalhada para ser exibida na página de erro.
        model.addAttribute("erroDetalhe", ex.getMessage()); // Adiciona o detalhe do erro ao modelo para que possa ser exibido na página de erro.
        return "error/500"; // Retorna o nome da visão (HTML) que deve ser renderizada, neste caso, "error/500.html" na pasta templates. O Thymeleaf irá processar essa página e exibir a mensagem de erro detalhada.
    }
}