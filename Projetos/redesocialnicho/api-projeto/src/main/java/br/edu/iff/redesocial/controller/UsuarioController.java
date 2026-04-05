package br.edu.iff.redesocial.controller;

import br.edu.iff.redesocial.entities.Usuario;
import br.edu.iff.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
@RequestMapping("/usuarios") // Define a rota base para todas as ações relacionadas aos usuários. Todas as rotas dentro deste controlador começarão com "/usuarios".
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // 1. Listar todos os usuários (Puxa o HTML da lista)
    @GetMapping("/lista")
    public String listarTodos(Model model) {
        model.addAttribute("listaDeUsuarios", repository.findAll());
        return "usuarios/lista";
    }

    // 2. Abre o formulário limpo para um NOVO usuário
    @GetMapping("/novo")
    public String exibirFormularioNovo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    // 3. Abre o formulário preenchido para EDITAR um usuário
    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable Long id, Model model) {
        // Usamos o IllegalArgumentException para o nosso GlobalExceptionHandler capturar
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
        
        model.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }

    // 4. Recebe os dados do formulário e salva (Criar ou Editar)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario, RedirectAttributes attributes) {
        repository.save(usuario);
        
        // Mensagem de sucesso (Flash Attribute)
        attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
        return "redirect:/usuarios/lista";
    }

    // 5. Deleta o usuário
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes attributes) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Não foi possível encontrar o usuário para exclusão.");
        } 
        
        repository.deleteById(id);
        
        // Mensagem de exclusão (Flash Attribute)
        attributes.addFlashAttribute("mensagemErro", "Usuário excluído!");
        return "redirect:/usuarios/lista";
    }
}