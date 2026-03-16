package br.edu.iff.redesocial.controller;

import br.edu.iff.redesocial.dto.AvaliacaoRequestDTO;
import br.edu.iff.redesocial.dto.AvaliacaoResponseDTO;
import br.edu.iff.redesocial.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> avaliarReceita(@RequestBody @Valid AvaliacaoRequestDTO dto) {
        AvaliacaoResponseDTO resposta = avaliacaoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

}