package br.edu.iff.redesocial.repository;

import br.edu.iff.redesocial.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    boolean existsByUsuarioIdAndReceitaId(Long usuarioId, Long receitaId);
    // Este método verifica se já existe uma avaliação feita por um usuário específico para uma receita específica,
    // retornando true se existir e false caso contrário.
}