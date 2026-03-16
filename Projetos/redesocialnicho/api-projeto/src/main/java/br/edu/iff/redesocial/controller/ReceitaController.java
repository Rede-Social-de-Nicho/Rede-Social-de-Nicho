package br.edu.iff.redesocial.controller;

import br.edu.iff.redesocial.dto.ReceitaRequestDTO;
import br.edu.iff.redesocial.dto.ReceitaResponseDTO;
import br.edu.iff.redesocial.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicação de classe controladora (REST) que irá receber requisições http e retornar respostas. 
@RequestMapping("/api/receitas") // Definimos o endereço base para acessar as informações de receita. 
public class ReceitaController {

    @Autowired // Aqui no controlador vamos chamar a camada de serviço, onde fica a lógica de negócio.
    private ReceitaService receitaService;

    @PostMapping // Recebe dados para criar uma nova receita. 
    public ResponseEntity<ReceitaResponseDTO> criar(@RequestBody @Valid ReceitaRequestDTO dto) {
        // @requestBody indica que os dados virão no corpo da requisição;
        // @valid valida as informações presentes no DTO, garantindo que os campos obrigatórios sejam válidos;
        ReceitaResponseDTO resposta = receitaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        // Retorna a resposta com o status http 201 (Criado) e o corpo contendo a receita criada.
    }

    @GetMapping // Retorna a lista de todas as receitas já cadastradas.
    public ResponseEntity<List<ReceitaResponseDTO>> listar() {
        return ResponseEntity.ok(receitaService.listarTodas());
        // Retorna com o status http 200 (Ok) e no corpo a lista de receitas cadastradas.
    }

    @GetMapping("/{id}") // Retorna uma receita específica, identificada pelo ID.
    public ResponseEntity<ReceitaResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.buscarPorId(id));
        // Retorna o status http 200 (Ok) e no corpo a receita cadastrada.
        // Se não encontrar a receita a camada de serviço retornará o erro 404 informando que a receita não foi localizada. 
    }

    @PutMapping("/{id}") // Substitui os dados de uma receita existente pelas novas informações dadas. 
    public ResponseEntity<ReceitaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ReceitaRequestDTO dto) {
        return ResponseEntity.ok(receitaService.atualizar(id, dto));
        // Retorna o status http 200 (ok) e no corpo a receita atualizada. 
    }

    @DeleteMapping("/{id}") // Remove uma receita do sistema identificada pelo ID. 
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletar(id); 
        // A camada de serviço irá deletar a receita. 
        // Se o ID não for encontrado retornará o erro 404 informando que não foi localizada. 
        return ResponseEntity.noContent().build();
        // Retorna http 204 (No content) indicando sucesso na operação, sem conteúdo no corpo. 
    }
}