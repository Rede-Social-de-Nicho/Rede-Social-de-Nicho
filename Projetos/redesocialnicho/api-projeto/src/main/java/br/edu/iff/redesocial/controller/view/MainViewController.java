package br.edu.iff.redesocial.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Atenção: É apenas @Controller, e não @RestController!
public class MainViewController {

    // Este método escuta tanto a rota "localhost:8081/" quanto "localhost:8081/home"
    @GetMapping({"", "/", "/home"})
    public String paginaInicial() {
        
        // O retorno é exatamente o nome do arquivo HTML que criamos (sem o ".html")
        // O Thymeleaf vai automaticamente procurar o "index.html" na pasta "templates"
        return "index"; 
    }
}