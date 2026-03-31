package br.edu.iff.redesocial.controller.view;

import br.edu.iff.redesocial.entities.Usuario;
import br.edu.iff.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("listaDeUsuarios", usuarios);
        return "usuarios/lista"; 
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form"; 
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        // O JPA é inteligente: se o usuário já tiver um ID, ele Atualiza. Se não tiver, ele Cria um novo!
        usuarioRepository.save(usuario);
        return "redirect:/usuarios/lista";
    }

    // --- NOVO MÉTODO: Editar Usuário ---
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        // Busca o usuário no banco pelo ID
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        
        // Manda o usuário PREENCHIDO para a mesma tela de formulário
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios/lista";
    }
}