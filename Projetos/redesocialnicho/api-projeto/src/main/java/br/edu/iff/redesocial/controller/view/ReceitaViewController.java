package br.edu.iff.redesocial.controller.view;

import br.edu.iff.redesocial.dto.ReceitaRequestDTO;
import br.edu.iff.redesocial.repository.UsuarioRepository;
import br.edu.iff.redesocial.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/receitas")
public class ReceitaViewController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String listarReceitas(Model model) {
        model.addAttribute("listaDeReceitas", receitaService.listarTodas());
        return "receitas/lista";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("listaDeAutores", usuarioRepository.findAll());
        model.addAttribute("receita", null); // Passamos null para a tela saber que é um cadastro novo
        return "receitas/form";
    }

    // --- NOVO MÉTODO: Editar Receita ---
    @GetMapping("/editar/{id}")
    public String editarReceita(@PathVariable Long id, Model model) {
        // Busca a receita pelo ID e envia preenchida para a tela
        model.addAttribute("receita", receitaService.buscarPorId(id));
        model.addAttribute("listaDeAutores", usuarioRepository.findAll());
        return "receitas/form";
    }

    @PostMapping("/salvar")
    public String salvarReceita(
            @RequestParam(required = false) Long id, // O ID agora é opcional (só vem se for edição)
            @RequestParam String titulo,
            @RequestParam String ingredientes,
            @RequestParam String passoApasso,
            @RequestParam Long autorId) {
        
        ReceitaRequestDTO dto = new ReceitaRequestDTO(titulo, ingredientes, passoApasso, autorId);
        
        // Se veio um ID, atualiza. Se não, cria uma nova.
        if (id != null) {
            receitaService.atualizar(id, dto);
        } else {
            receitaService.criar(dto);
        }
        
        return "redirect:/receitas/lista";
    }

    @GetMapping("/deletar/{id}")
    public String deletarReceita(@PathVariable Long id) {
        receitaService.deletar(id);
        return "redirect:/receitas/lista";
    }
}