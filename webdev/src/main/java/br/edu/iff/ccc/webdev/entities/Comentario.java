package br.edu.iff.ccc.webdev.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String conteudo;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataComentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;
}