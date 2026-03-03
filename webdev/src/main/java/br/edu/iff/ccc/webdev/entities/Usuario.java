package br.edu.iff.ccc.webdev.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nomeUsuario;
    private String email;
    private String senhaHash;
    private Date dataCriacao;
    private boolean eAdm;

    // Relacionamento 1:N com Comentario
    @OneToMany(mappedBy = "autor")
    private List<Comentario> comentarios;
}
