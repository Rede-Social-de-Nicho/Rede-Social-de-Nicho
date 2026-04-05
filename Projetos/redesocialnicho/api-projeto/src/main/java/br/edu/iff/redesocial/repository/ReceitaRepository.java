package br.edu.iff.redesocial.repository;

import br.edu.iff.redesocial.entities.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    // Verifica se uma receita já existe para um título especificado. 
    @Query("SELECT r FROM Receita r WHERE UPPER(r.titulo) LIKE UPPER(:titulo) ESCAPE '\\'")
    List<Receita> findByTituloContainingIgnoreCase(String titulo);
    // Busca receitas por parte do título e categoria. 
}