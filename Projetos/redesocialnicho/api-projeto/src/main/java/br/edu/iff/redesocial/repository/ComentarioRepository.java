package br.edu.iff.redesocial.repository;

import br.edu.iff.redesocial.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    // Verifica se um comentário já existe para um usuário e receita especificados. 
}