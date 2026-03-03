package br.edu.iff.ccc.webdev.controller.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.webdev.entities.Usuario;
import br.edu.iff.ccc.webdev.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestMainApiController {

    @GetMapping("/usuarios")
    public List<Usuario> listarTodos() {
        return UsuarioRepository.findAll();
    }

    @PostMapping("/usuarios/teste")
    public ResponseEntity<Usuario> criarUsuarioTeste() {
        Usuario novo = new Usuario();
        novo.setNomeUsuario("Marvin Ramos");
        novo.setEmail("marvin@iff.edu.br");
        novo.setSenhaHash("123456");
        novo.setDataCriacao(new Date());
        novo.setEAdm(true); // Definindo como administrador conforme seu diagrama

        Usuario salvo = UsuarioRepository.save(novo);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/admins")
    public List<Usuario> listarAdmins() {
        // Testando seu método JPQL customizado
        return UsuarioRepository.buscarAdministradores();
    }
}