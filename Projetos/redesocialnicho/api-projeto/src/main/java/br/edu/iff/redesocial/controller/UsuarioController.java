package br.edu.iff.redesocial.controller;

import br.edu.iff.redesocial.dto.UsuarioRequestDTO;
import br.edu.iff.redesocial.exception.UsuarioNaoEncontradoException;
import br.edu.iff.redesocial.entities.Usuario;
import br.edu.iff.redesocial.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // 1. Listar todos os usuários
    @GetMapping
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // 2. Buscar usuário específico por ID (Usa a nova exceção se não achar)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = repository.findById(id)
            .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com ID: " + id));
        
        return ResponseEntity.ok(usuario);
    }

    // 3. Criar novo usuário (Usa o DTO para validar @Email e @NotBlank)
    @PostMapping
    public ResponseEntity<Usuario> salvar(@Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.nomeUsuario());
        usuario.setEmail(dto.email());
        usuario.setSenhaHash(dto.senhaHash());
        usuario.seteAdm(dto.eAdm());

        Usuario novoUsuario = repository.save(usuario);
        
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    // 4. Deletar usuário (Usa a nova exceção se o ID não existir)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new UsuarioNaoEncontradoException("Não foi possível encontrar o usuário com ID " + id + " para exclusão.");
        }
        
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
