package br.edu.iff.redesocial.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoRequestDTO(
    
    @NotNull(message = "O ID da receita é obrigatório")
    Long receitaId,

    @NotNull(message = "O ID do usuário é obrigatório")
    Long usuarioId,

    @NotNull(message = "A quantidade de estrelas é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1 estrela")
    @Max(value = 5, message = "A nota máxima é 5 estrelas")
    // A avaliação deve ser um número inteiro entre 1 e 5.
    
    Integer estrelas
    // Garantia de que a avaliação seja um número inteiro é feita pelo tipo integer. 
    // Não pode ser int por que se o Usuário esquecer de avaliar, o int jogaria zero.
    // Zero não é valor nulo, então daria erro por causa do limite definido de 1. 

) {}