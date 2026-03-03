package br.edu.iff.ccc.webdev.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String titulo;
    private String ingredientes;
    private String passoAPasso;
    private Date dataPostagem;
    private float mediaAvaliacao;

    // Relacionamento N:N com ColecaoPessoal
    @ManyToMany(mappedBy = "receitas")
    private List<ColecaoPessoal> colecoes;
}
