package br.edu.iff.redesocial.repository;

import br.edu.iff.redesocial.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Verifica se um usuário já existe para um email especificado.
}