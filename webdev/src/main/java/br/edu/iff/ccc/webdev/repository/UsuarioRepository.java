package br.edu.iff.ccc.webdev.repository;

import br.edu.iff.ccc.webdev.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.eAdm = true")
    List<Usuario> buscarAdministradores();

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario buscarPorEmail(String email);
}