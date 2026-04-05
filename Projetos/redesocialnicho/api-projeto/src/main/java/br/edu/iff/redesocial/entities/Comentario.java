package br.edu.iff.redesocial.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    // Relação: Vários comentários pertencem a um Usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relação: Vários comentários pertencem a uma Receita
    @ManyToOne
    @JoinColumn(name = "receita_id", nullable = false)
    private Receita receita;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Receita getReceita() { return receita; }
    public void setReceita(Receita receita) { this.receita = receita; }
}