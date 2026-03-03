package br.edu.iff.ccc.webdev.repository;

import br.edu.iff.ccc.webdev.entities.ColecaoPessoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ColecaoPessoalRepository extends JpaRepository<ColecaoPessoal, Integer> {

    @Query("SELECT c FROM ColecaoPessoal c WHERE c.usuario.id = ?1")
    List<ColecaoPessoal> buscarPorUsuarioId(int usuarioId);
}