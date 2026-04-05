package br.edu.iff.redesocial;

import br.edu.iff.redesocial.entities.Usuario;
import br.edu.iff.redesocial.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiProjetoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiProjetoApplication.class, args);
    }

    // Este método roda automaticamente assim que o servidor ligar.
    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
        return args -> {
            // Verifica se o banco está vazio. Se estiver, cria o autor Administrador.
            if (usuarioRepository.count() == 0) {
                Usuario autor = new Usuario();
                autor.setNomeUsuario("Administrador");
                autor.setEmail("administrador@iff.edu.br");
                autor.setSenhaHash("123456");
                
                autor.setEAdm(true);

                usuarioRepository.save(autor);

                System.out.println("=====================================================");
                System.out.println("USUARIO AUTOR CRIADO AUTOMATICAMENTE!");
                System.out.println("=====================================================");
            }
        };
    }
}