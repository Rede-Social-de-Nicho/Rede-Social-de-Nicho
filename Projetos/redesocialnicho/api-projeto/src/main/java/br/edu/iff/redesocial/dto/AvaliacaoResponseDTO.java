package br.edu.iff.redesocial.dto;

import java.time.LocalDateTime;

public record AvaliacaoResponseDTO(
    Long id,
    Integer estrelas,
    LocalDateTime dataAvaliacao,
    Long usuarioId,
    String nomeUsuario,
    Long receitaId,
    String tituloReceita
) {}