package br.edu.iff.redesocial.controller;

import br.edu.iff.redesocial.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

// Controlador para lidar com avaliações de receitas pelos usuários.
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired private AvaliacaoService avaliacaoService; // Aplicação da camada de serviço relacionada às avaliações.

    @PostMapping("/salvar")
    public String salvarAvaliacao(@RequestParam Long usuarioId, 
                                  @RequestParam Long receitaId, 
                                  @RequestParam Integer estrelas, 
                                  RedirectAttributes attributes) {
        try { // Tenta salvar a avaliação usando o serviço. Se houver algum problema (ex: usuário ou receita não encontrados), uma exceção será lançada.
            avaliacaoService.salvarAvaliacao(usuarioId, receitaId, estrelas);
            attributes.addFlashAttribute("mensagem", "Avaliação registrada com sucesso!");
        } catch (Exception e) { // Em caso de erro, captura a exceção e adiciona uma mensagem de erro para ser exibida ao usuário.
            attributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        
        // Redireciona de volta para a página de detalhes da receita avaliada
        return "redirect:/receitas/detalhes/" + receitaId;
    }
}