package br.edu.iff.redesocial.controller;

import br.edu.iff.redesocial.entities.Receita;
import br.edu.iff.redesocial.repository.ReceitaRepository;
import br.edu.iff.redesocial.repository.UsuarioRepository;
import br.edu.iff.redesocial.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/receitas") // Define a rota base para todas as ações relacionadas às receitas. Todas as rotas dentro deste controlador começarão com "/receitas".
public class ReceitaController {

    @Autowired private ReceitaService receitaService;
    @Autowired private ReceitaRepository receitaRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private br.edu.iff.redesocial.repository.ComentarioRepository comentarioRepository;// Injeção do repositório de comentários para salvar os comentários das receitas.

    // Listagem de Receitas.
    @GetMapping("/lista") // Mapeia a rota "/receitas/lista" para este método, que será chamado quando um usuário acessar essa URL para ver a lista de receitas.
    public String listar(Model model) {
        // if(true) throw new RuntimeException("Simulando banco de dados fora do ar!");
        model.addAttribute("listaDeReceitas", receitaRepository.findAll());
        return "receitas/lista";
    }

    // Ver detalhes e Avaliar dentro
    @GetMapping("/detalhes/{id}") // Mapeia a rota "/receitas/detalhes/{id}" para este método, onde {id} é um parâmetro de caminho que representa o ID da receita a ser exibida.
    public String exibirDetalhes(@PathVariable Long id, Model model) {
        Receita receita = receitaRepository.findById(id) // Busca a receita no banco de dados pelo ID fornecido. Se a receita não for encontrada, lança uma exceção com status 404.
                .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada: " + id));
        
        model.addAttribute("receita", receita);
        model.addAttribute("usuarios", usuarioRepository.findAll()); 
        return "receitas/detalhes";
    }

    // Abre o formulário para uma nova receita
    @GetMapping("/novo")
    public String exibirFormularioNovo(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("receita", new Receita()); 
        return "receitas/formulario"; 
    }

    // Rota para editar (Busca a receita existente e preenche o formulário)
    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable Long id, Model model) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada: " + id));
        
        model.addAttribute("receita", receita);
        model.addAttribute("usuarios", usuarioRepository.findAll());
        
        return "receitas/formulario"; 
    }

    // Recebe os dados do formulário (Criação ou Edição) e salva no banco
    @PostMapping("/salvar")
    public String salvarReceita(@ModelAttribute Receita receita, @RequestParam Long autorId, RedirectAttributes attributes) {
        br.edu.iff.redesocial.entities.Usuario autor = usuarioRepository.findById(autorId)
                .orElseThrow(() -> new IllegalArgumentException("Autor inválido!"));
        
        receita.setAutor(autor);
        receitaRepository.save(receita);
        
        // Adiciona uma mensagem de sucesso para ser exibida na página de lista após o redirecionamento.
        attributes.addFlashAttribute("mensagem", "Receita salva com sucesso!");
        return "redirect:/receitas/lista";
    }

    // Rota para deletar uma receita.
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes attributes) {
        receitaService.deletar(id);
        
        attributes.addFlashAttribute("mensagemErro", "Receita excluída!"); // Mensagem de aviso para o usuário após a exclusão.
        return "redirect:/receitas/lista";
    }

    // Rota para adicionar um comentário a uma receita específica.
    @PostMapping("/comentar/{id}")
    public String adicionarComentario(@PathVariable("id") Long receitaId, 
                                      @RequestParam("autorId") Long autorId, 
                                      @RequestParam("texto") String texto) {
        
        // 1. Busca a receita e o usuário no banco
        Receita receita = receitaRepository.findById(receitaId)
                .orElseThrow(() -> new IllegalArgumentException("Receita inválida!"));
        br.edu.iff.redesocial.entities.Usuario autor = usuarioRepository.findById(autorId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário inválido!"));
        
        // 2. Cria o comentário e conecta tudo
        br.edu.iff.redesocial.entities.Comentario comentario = new br.edu.iff.redesocial.entities.Comentario();
        comentario.setTexto(texto);
        comentario.setReceita(receita);
        comentario.setUsuario(autor);
        
        // 3. Salva no banco de dados
        comentarioRepository.save(comentario);
        
        // 4. Recarrega a página de detalhes para mostrar o comentário novo
        return "redirect:/receitas/detalhes/" + receitaId;
    }
}