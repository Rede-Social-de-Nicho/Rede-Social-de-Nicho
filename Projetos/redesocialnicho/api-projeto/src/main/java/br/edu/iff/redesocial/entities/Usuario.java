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
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receita> receitas; 

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<ColecaoPessoal> colecoes; 

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Comentario> comentarios; 

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Avaliacao> avaliacoes; 

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
    
    public boolean isEAdm() { return eAdm; }
    public void setEAdm(boolean eAdm) { this.eAdm = eAdm; }
}