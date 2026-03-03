package br.edu.iff.ccc.webdev.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ColecaoPessoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nomeColecao;
    
    @Temporal(TemporalType.DATE)
    private Date dataColecao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "colecao_receita",
        joinColumns = @JoinColumn(name = "colecao_id"),
        inverseJoinColumns = @JoinColumn(name = "receita_id")
    )
    private List<Receita> receitas;
}