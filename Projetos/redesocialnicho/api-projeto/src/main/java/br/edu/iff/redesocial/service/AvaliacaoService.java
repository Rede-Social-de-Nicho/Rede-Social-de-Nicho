package br.edu.iff.redesocial.service;

import br.edu.iff.redesocial.entities.Avaliacao;
import br.edu.iff.redesocial.entities.Receita;
import br.edu.iff.redesocial.entities.Usuario;
import br.edu.iff.redesocial.repository.AvaliacaoRepository;
import br.edu.iff.redesocial.repository.ReceitaRepository;
import br.edu.iff.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;

@Service
public class AvaliacaoService { // Esta classe é responsável por gerenciar as avaliações feitas pelos usuários nas receitas,
                            // incluindo a lógica de negócio para salvar avaliações e calcular a média de avaliações das receitas.

    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ReceitaRepository receitaRepository;

    public List<Avaliacao> listarTodas() {
        return avaliacaoRepository.findAll();
    }

    @Transactional // Isso garante que a lista de avaliações não venha vazia.
    public void salvarAvaliacao(Long usuarioId, Long receitaId, Integer estrelas) {
        
        if (avaliacaoRepository.existsByUsuarioIdAndReceitaId(usuarioId, receitaId)) {
            throw new IllegalArgumentException("Erro: Você já avaliou esta receita!");
        }

        // Busca o usuário e a receita no banco de dados usando os IDs fornecidos. Se algum deles não for encontrado, uma exceção será lançada.
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        Receita receita = receitaRepository.findById(receitaId)
                .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada."));

        Avaliacao avaliacao = new Avaliacao(); // Cria uma nova instância de Avaliação e define o usuário, a receita e a quantidade de estrelas da avaliação.
        avaliacao.setUsuario(usuario);
        avaliacao.setReceita(receita);
        avaliacao.setEstrelas(estrelas);

        // Salva a avaliação no banco de dados e associa a avaliação à receita. Em seguida, recalcula a média de avaliações da receita e salva as alterações.
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);

        receita.getAvaliacoes().add(avaliacaoSalva); // Adiciona a avaliação à lista de avaliações da receita.
        receita.calcularMediaAval(); // Recalcula a média de avaliações da receita com base na nova avaliação adicionada.
        receitaRepository.save(receita); // Salva as alterações na receita, incluindo a nova média de avaliações.
    }
}