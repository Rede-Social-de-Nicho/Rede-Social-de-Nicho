package br.edu.iff.redesocial.service;

import br.edu.iff.redesocial.dto.AvaliacaoRequestDTO;
import br.edu.iff.redesocial.dto.AvaliacaoResponseDTO;
import br.edu.iff.redesocial.entities.Avaliacao;
import br.edu.iff.redesocial.entities.Receita;
import br.edu.iff.redesocial.entities.Usuario;
import br.edu.iff.redesocial.repository.AvaliacaoRepository;
import br.edu.iff.redesocial.repository.ReceitaRepository;
import br.edu.iff.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AvaliacaoService {

    @Autowired // Inserir os repositórios para acesso aos dados do banco. 
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO dto) {
        
        // Busca o Usuário no banco de dados.
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        // Busca a Receita no banco de dados.
        Receita receita = receitaRepository.findById(dto.receitaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));
                // Verifica se o usuário já avaliou a receita.
                if (avaliacaoRepository.existsByUsuarioIdAndReceitaId(dto.usuarioId(), dto.receitaId())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você já avaliou esta receita!");
                    // Verificação para garantir que a avaliação tenha entre 1 e 5 estrelas e o ID válido.
                }

        // Monta a Entidade, transformando o DTO em Objeto do BD. 
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setEstrelas(dto.estrelas());
        avaliacao.setUsuario(usuario);
        avaliacao.setReceita(receita);
        // A dataAvaliacao já é preenchida automaticamente pela entidade.

        // Salvar no BD. 
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);

        // Atualiza a média da receita
        receita.getAvaliacoes().add(avaliacaoSalva);
        receita.calcularMediaAval();
        receitaRepository.save(receita);

        // Retorna o DTO de Resposta.
        return new AvaliacaoResponseDTO(
                avaliacaoSalva.getId(),
                avaliacaoSalva.getEstrelas(),
                avaliacaoSalva.getDataAvaliacao(),
                usuario.getId(),
                usuario.getNomeUsuario(), // Pegando o nome direto da entidade Usuário encontrada
                receita.getId(),
                receita.getTitulo() // Pegando o título direto da entidade Receita encontrada
        );
    }
}