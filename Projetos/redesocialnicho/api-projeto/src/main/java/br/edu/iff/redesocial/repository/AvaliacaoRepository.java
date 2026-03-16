package br.edu.iff.redesocial.repository;

import br.edu.iff.redesocial.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    // Verifica se uma avaliação já existe para um usuário e receita especificados. 
    
    boolean existsByUsuarioIdAndReceitaId(Long usuarioId, Long receitaId);
}