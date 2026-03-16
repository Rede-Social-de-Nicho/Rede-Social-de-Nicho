package br.edu.iff.redesocial.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Chave primária da tabela Receita. Auto-increment pelo BD.
    private Long id;

    private String titulo;
    
    @Column(columnDefinition = "TEXT") // Define a coluna "ingredientes" como texto no BD, permitindo listas longas.
    private String ingredientes;
    
    @Column(columnDefinition = "TEXT") // Define a coluna "passoApasso" como texto no BD, permitindo descrições detalhadas.
    private String passoApasso;
    
    private LocalDateTime dataPostagem = LocalDateTime.now();
    
    private float mediaAvaliacao = 0.0f; // Avaliação média da receita, iniciada com 0. Será atualizada conforme avaliações.

    @ManyToOne // Muitas receitas podem ser criadas por um único usuário.
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToMany(mappedBy = "receitas") // Muitas receitas podem estar em muitas coleções pessoais.
    private List<ColecaoPessoal> colecoes; 

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL) 
    // Uma receita pode ter muitos comentários. CascadeType.ALL garante que, ao deletar uma receita, os comentários tambem sejam.
    private List<Avaliacao> avaliacoes;

    public void calcularMediaAval() {
        if (avaliacoes == null || avaliacoes.isEmpty()) {
            this.mediaAvaliacao = 0;
            return;
            // Evita divisão por zero e define a média como 0 se não houver avaliações.
        }
        float soma = 0;
        for (Avaliacao aval : avaliacoes) {
            soma += aval.getEstrelas();
            // Soma as estrelas de todas as avaliações para calcular a média.
        }
        this.mediaAvaliacao = soma / avaliacoes.size(); // Calcula a média dividindo a soma pelo número de avaliações.
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getIngredientes() { return ingredientes; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
    
    public String getPassoApasso() { return passoApasso; }
    public void setPassoApasso(String passoApasso) { this.passoApasso = passoApasso; }
    
    public LocalDateTime getDataPostagem() { return dataPostagem; }
    public void setDataPostagem(LocalDateTime dataPostagem) { this.dataPostagem = dataPostagem; }
    
    public float getMediaAvaliacao() { return mediaAvaliacao; }
    
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    
    public List<Avaliacao> getAvaliacoes() { return avaliacoes; }
}