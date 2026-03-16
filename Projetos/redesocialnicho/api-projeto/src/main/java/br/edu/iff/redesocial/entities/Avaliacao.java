package br.edu.iff.redesocial.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {
    // Garante que a combinação Usuário + Receita não se repita no banco, ou seja, seja única.
    @UniqueConstraint(columnNames = {"usuario_id", "receita_id"})
})
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Chave primária da tabela Avaliação, auto-increment pelo BD.
    private Long id;

    private Integer estrelas; // mínimo 1, máximo 5, representando a avaliação dada pelo usuário à receita.
    private LocalDateTime dataAvaliacao = LocalDateTime.now(); // Armazena a data e hora em que a avaliação foi feita.

    @ManyToOne
    // Muitas avaliações podem ser feitas por um único usuário.

    @JoinColumn(name = "usuario_id")
    // Define a coluna "usuario_id" na tabela Avaliação. Chave estrangeira que referencia a tabela Usuário.

    private Usuario usuario;

    @ManyToOne
    // Muitas avaliações podem ser feitas para uma única receita.

    @JoinColumn(name = "receita_id")
    // Define a coluna "receita_id" na tabela Avaliação. Chave estrangeira que referencia a tabela Receita.
    
    private Receita receita;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getEstrelas() { return estrelas; }
    public void setEstrelas(Integer estrelas) { this.estrelas = estrelas; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Receita getReceita() { return receita; }
    public void setReceita(Receita receita) { this.receita = receita; }
    public LocalDateTime getDataAvaliacao() {
    return dataAvaliacao; }
    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
    this.dataAvaliacao = dataAvaliacao; }
    
}