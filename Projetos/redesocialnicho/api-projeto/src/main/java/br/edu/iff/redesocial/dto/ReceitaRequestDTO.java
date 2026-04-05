// Objeto usado para receber os dados de fora para dentro do sistema, ou seja, para criar ou alterar uma receita.

package br.edu.iff.redesocial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReceitaRequestDTO( // Classe criada apenas para carregar dados de forma direta.
    
    @NotBlank(message = "O título é obrigatório")
    /*@NotBlank garante:
    que o valor não seja nulo;
    que não seja uma lista vazia;
    que não seja preenchido com espaços em branco. */

    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    // @Size define o valor mínimo de 3 e o valor máximo de 100 para o título da receita.

    String titulo,

    @NotBlank(message = "Os ingredientes são obrigatórios")
    String ingredientes,

    @NotBlank(message = "O passo a passo é obrigatório")
    String passoApasso,

    @NotNull(message = "O ID do autor é obrigatório")
    // @NotNull garante que o valor do ID não seja nulo. 

    Long autorId

) {}