package br.edu.iff.redesocial.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO para receber os dados de criação de um usuário, com validações para garantir que os campos sejam preenchidos corretamente.
public record UsuarioRequestDTO(
    @NotBlank(message = "O nome de usuário é obrigatório")
    String nomeUsuario,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    String email,

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    String senhaHash,

    boolean eAdm
) {
}