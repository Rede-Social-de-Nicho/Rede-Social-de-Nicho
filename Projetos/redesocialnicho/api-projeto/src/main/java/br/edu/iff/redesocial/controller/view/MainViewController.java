package br.edu.iff.redesocial.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {

    // Este método escuta tanto a rota "localhost:8081/" quanto "localhost:8081/home"
    @GetMapping({"", "/", "/home"})
    public String paginaInicial() {
        
        // O Thymeleaf procura o index.html na pasta templates e o renderiza para o usuário.
        return "index"; 
    }
}