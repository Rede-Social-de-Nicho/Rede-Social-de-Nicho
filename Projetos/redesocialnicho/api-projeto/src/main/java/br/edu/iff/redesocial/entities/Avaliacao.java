package br.edu.iff.redesocial.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = { // Garante que um usuário só possa avaliar uma receita uma vez, evitando avaliações duplicadas.
    @UniqueConstraint(columnNames = {"usuario_id", "receita_id"})
})
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera um ID único para cada avaliação, facilitando a identificação e manipulação das avaliações no banco de dados.
    private Long id;

    private Integer estrelas; // Armazena a quantidade de estrelas (1 a 5) que o usuário atribuiu à receita.
    private LocalDateTime dataAvaliacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id") // Define a chave estrangeira para a tabela de usuários, indicando qual usuário fez a avaliação.
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "receita_id") // Define a chave estrangeira para a tabela de receitas, indicando qual receita foi avaliada.
    private Receita receita;

    public Avaliacao() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getEstrelas() { return estrelas; }
    public void setEstrelas(Integer estrelas) { this.estrelas = estrelas; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Receita getReceita() { return receita; }
    public void setReceita(Receita receita) { this.receita = receita; }
    public LocalDateTime getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDateTime dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }
}