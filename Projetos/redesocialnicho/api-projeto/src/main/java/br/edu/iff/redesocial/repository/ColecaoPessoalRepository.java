package br.edu.iff.redesocial.repository;

import br.edu.iff.redesocial.entities.ColecaoPessoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColecaoPessoalRepository extends JpaRepository<ColecaoPessoal, Long> {
    // Verifica se uma coleção pessoal já existe para um usuário e receita especificados. 
}