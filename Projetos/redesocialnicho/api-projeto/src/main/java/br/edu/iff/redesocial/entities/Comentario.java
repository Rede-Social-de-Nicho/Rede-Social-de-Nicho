package br.edu.iff.redesocial.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Chave primária da tabela Comentario. Auto-increment pelo BD.
    private Long id;

    @Column(columnDefinition = "TEXT") // Define a coluna "conteudo" como texto no BD para permitir comentários longos.
    private String conteudo;
    
    private LocalDateTime dataComentario = LocalDateTime.now();

    @ManyToOne // Muitos comentários podem ser feitos por um único usuário.
    @JoinColumn(name = "usuario_id") 
    // Define a coluna "usuario_id" na tabela Comentario. Chave estrangeira que referencia a tabela Usuário.

    private Usuario usuario;

    @ManyToOne // Muitas receitas podem ter muitos comentários.
    @JoinColumn(name = "receita_id")
    private Receita receita;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    
    public LocalDateTime getDataComentario() { return dataComentario; }
    public void setDataComentario(LocalDateTime dataComentario) { this.dataComentario = dataComentario; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public Receita getReceita() { return receita; }
    public void setReceita(Receita receita) { this.receita = receita; }
}