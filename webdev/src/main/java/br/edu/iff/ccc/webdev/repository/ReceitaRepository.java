package br.edu.iff.ccc.webdev.repository;

import br.edu.iff.ccc.webdev.entities.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {

    @Query("SELECT AVG(a.estrelas) FROM Avaliacao a WHERE a.receita.id = :id")
    Double calcularMediaDeAvaliacoes(@Param("id") int id);
}