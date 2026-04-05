// Definição de pacotes para receber os dados de fora sem vazar a estrutura interna do sistema. 

package br.edu.iff.redesocial.dto;

public record ReceitaResponseDTO( 
    Long id,
    String titulo,
    String ingredientes,
    String passoApasso,
    float mediaAvaliacao,
    Long autorId,
    String nomeAutor
) {}