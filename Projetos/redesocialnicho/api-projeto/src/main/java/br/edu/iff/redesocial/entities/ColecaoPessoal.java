package br.edu.iff.redesocial.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ColecaoPessoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Chave primária da tabela ColecaoPessoal, auto-increment pelo BD.
    private Long id;

    private String nomeColecao;

    private LocalDateTime dataColecao = LocalDateTime.now(); // Armazena a data e hora de criação da coleção pessoal, facilitando o controle e ordenação das coleções.

    @ManyToOne // Muitas coleções pessoais podem pertencer a um único usuário.
    @JoinColumn(name = "usuario_id") 
    // Define a coluna "usuario_id" na tabela ColecaoPessoal. Chave estrangeira que referencia a tabela Usuário.
    private Usuario usuario;

    @ManyToMany // Uma coleção pessoal pode conter muitas receitas e uma receita pode estar em muitas coleções pessoais.
    @JoinTable(
        name = "colecao_receita",
        joinColumns = @JoinColumn(name = "colecao_id"),
        inverseJoinColumns = @JoinColumn(name = "receita_id")
    )
    // Define a tabela de junção "colecao_receita" para a relação ManyToMany entre ColecaoPessoal e Receita.
    private List<Receita> receitas;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNomeColecao() { return nomeColecao; }
    public void setNomeColecao(String nomeColecao) { this.nomeColecao = nomeColecao; }
    
    public LocalDateTime getDataColecao() { return dataColecao; }
    public void setDataColecao(LocalDateTime dataColecao) { this.dataColecao = dataColecao; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public List<Receita> getReceitas() { return receitas; }
    public void setReceitas(List<Receita> receitas) { this.receitas = receitas; }
}