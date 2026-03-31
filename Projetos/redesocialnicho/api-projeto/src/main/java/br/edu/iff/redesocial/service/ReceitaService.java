package br.edu.iff.redesocial.service;

import br.edu.iff.redesocial.dto.ReceitaRequestDTO;
import br.edu.iff.redesocial.dto.ReceitaResponseDTO;
import br.edu.iff.redesocial.entities.Receita;
import br.edu.iff.redesocial.entities.Usuario;
import br.edu.iff.redesocial.exception.ReceitaNaoEncontradaException;
import br.edu.iff.redesocial.exception.UsuarioNaoEncontradoException;
import br.edu.iff.redesocial.repository.ReceitaRepository;
import br.edu.iff.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired 
    private ReceitaRepository receitaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ReceitaResponseDTO criar(ReceitaRequestDTO dto) {
        // Trocamos para a nossa exceção de Usuário
        Usuario autor = usuarioRepository.findById(dto.autorId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Autor não encontrado com o ID fornecido"));
                // Verifica se o título da receita já existe para evitar duplicidade.
                // Se não existir o programa para e retorna um 404, caso exista, o programa segue. 

        Receita receita = new Receita(); // Cria um novo objeto Receita e preenche com os dados do DTO e do autor encontrado.
        receita.setTitulo(dto.titulo()); // O titulo da receita é definido a partir do DTO recebido.
        receita.setIngredientes(dto.ingredientes()); // Os ingredientes da receita são definidos a partir do DTO recebido.
        receita.setPassoApasso(dto.passoApasso()); 
        receita.setAutor(autor);

        Receita receitaSalva = receitaRepository.save(receita);
        return converterParaDTO(receitaSalva);
    }

    public List<ReceitaResponseDTO> listarTodas() { 
        // Busca todas as receitas no banco de dados, converte cada uma para um DTO de resposta e retorna a lista de DTOs.
        return receitaRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ReceitaResponseDTO buscarPorId(Long id) {
        // Busca uma receita por ID no banco de dados. Se a receita não for encontrada, lança uma exceção com status 404.
        // Trocamos para a nossa exceção de Receita
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ReceitaNaoEncontradaException("Receita não encontrada com ID: " + id));
        return converterParaDTO(receita);
    }

    public ReceitaResponseDTO atualizar(Long id, ReceitaRequestDTO dto) {
        // Busca a receita existente por ID. Se a receita não for encontrada, lança uma exceção com status 404.
        // Trocamos para a nossa exceção de Receita
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ReceitaNaoEncontradaException("Receita não encontrada com ID: " + id));

        receita.setTitulo(dto.titulo());
        receita.setIngredientes(dto.ingredientes());
        receita.setPassoApasso(dto.passoApasso());

        Receita atualizada = receitaRepository.save(receita);
        return converterParaDTO(atualizada);
    }

    public void deletar(Long id) {
        // Verifica se a receita existe antes de tentar deletar. Se a receita não for encontrada, lança uma exceção com status 404.
        if (!receitaRepository.existsById(id)) {
            // Trocamos para a nossa exceção de Receita
            throw new ReceitaNaoEncontradaException("Não foi possível excluir. Receita não encontrada com ID: " + id);
        }
        receitaRepository.deleteById(id);
    }

    private ReceitaResponseDTO converterParaDTO(Receita receita) {
        // Converte uma entidade Receita para um DTO de resposta, incluindo informações do autor e a média de avaliação.
        
        return new ReceitaResponseDTO(
                receita.getId(),
                receita.getTitulo(),
                receita.getIngredientes(),
                receita.getPassoApasso(),
                receita.getMediaAvaliacao(),
                receita.getAutor().getId(),
                receita.getAutor().getNomeUsuario()
        );
    }
}
