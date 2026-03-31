package br.edu.iff.redesocial.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Prepara para a herança do Administrador
@DiscriminatorColumn(name = "tipo_usuario") // Esta coluna vai indicar se é um "Usuario" ou um "Adm".
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeUsuario;
    private String email;
    private String senhaHash;
    
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    private boolean eAdm;

    // Relacionamentos vindos do diagrama
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true) // "autor" é o nome do atributo na classe Receita que referencia o Usuário.
    private List<Receita> receitas; // Um usuário pode criar várias receitas.

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) // "usuario" é o nome do atributo na classe ColecaoPessoal que referencia o Usuário.
    private List<ColecaoPessoal> colecoes; // Um usuário pode ter várias coleções pessoais.

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) // "usuario" é o nome do atributo na classe Comentario que referencia o Usuário.
    private List<Comentario> comentarios; // Um usuário pode fazer vários comentários.

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) // "usuario" é o nome do atributo na classe Avaliacao que referencia o Usuário.
    private List<Avaliacao> avaliacoes; // Um usuário pode fazer várias avaliações.

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    
    public boolean iseAdm() { return eAdm; }
    public void seteAdm(boolean eAdm) { this.eAdm = eAdm; }
}