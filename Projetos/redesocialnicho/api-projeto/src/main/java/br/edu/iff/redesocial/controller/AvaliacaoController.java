package br.edu.iff.redesocial.controller;

import br.edu.iff.redesocial.dto.AvaliacaoRequestDTO;
import br.edu.iff.redesocial.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Mudamos para @Controller
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller // Não é mais @RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/salvar")
    public String salvar(@Valid AvaliacaoRequestDTO dto, BindingResult result, RedirectAttributes attributes) {
        
        // Se houver erro de validação (ex: nota vazia)
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagemErro", "Verifique os campos obrigatórios!");
            return "redirect:/avaliacoes/novo";
        }

        try {
            avaliacaoService.criar(dto);
            attributes.addFlashAttribute("mensagem", "Avaliação enviada com sucesso!");
        } catch (Exception e) {
            // Caso o Service jogue erro (ex: se o usuário já avaliou essa receita)
            attributes.addFlashAttribute("mensagemErro", "Erro: " + e.getMessage());
            return "redirect:/avaliacoes/novo";
        }

        return "redirect:/avaliacoes/lista";
    }
}
