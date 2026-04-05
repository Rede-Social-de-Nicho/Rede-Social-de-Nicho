🍳 Rede Social de Nicho - Receitas Culinárias


Este projeto consiste em uma API REST desenvolvida com Spring Boot para uma rede social focada no compartilhamento e avaliação de receitas. O sistema permite que usuários criem perfis, publiquem suas próprias receitas, interajam com conteúdos de outros usuários e gerenciem coleções pessoais.

🚀 Tecnologias Utilizadas


Java 25 (OpenJDK)
Spring Boot 4.0.3
Spring Data JPA
Banco de Dados H2 (Em memória)
Lombok (Para produtividade)
Maven (Gerenciador de dependências)

🛠️ Configuração de Ambiente

Durante o desenvolvimento, identificamos que o projeto deve ser executado fora de pastas sincronizadas (como OneDrive/Dropbox) para evitar conflitos de permissão de escrita.

Executando o Projeto
Clone o repositório em uma pasta local (Ex: C:\Projetos\).

Certifique-se de que a porta 8081 está disponível (configurada via application.yml).

No terminal, execute:

mvn spring-boot:run
<img width="1918" height="952" alt="image" src="https://github.com/user-attachments/assets/93da5217-6bc1-4a04-89ce-561093eb3797" />


📂 Estrutura do Projeto (Entidades)

Usuario: Classe base que gerencia os perfis (Administrador, Criador, Consumidor).

Receita: Contém o título, ingredientes, passo a passo e a média de avaliações.

Avaliacao: Gerencia as estrelas deixadas pelos usuários (Lógica de voto único).

ColecaoPessoal: Permite aos usuários favoritar e organizar receitas.

⚙️ Regras de Negócio Implementadas

Média Automática: A média de avaliações de cada receita é calculada dinamicamente com base nas notas recebidas.

Voto Único: O sistema impede que um usuário avalie a mesma receita mais de uma vez (Integridade de Dados).

Herança de Usuários: Diferenciação entre usuários comuns e administradores através de @Inheritance no JPA.

🧪 Guia de Testes da API

Para testar o sistema, utilize o Insomnia seguindo a ordem abaixo. É necessário criar o Usuário primeiro, pois a Receita depende de um ID de autor válido.

1. Contexto de Usuários (User Management)
   
A. Criar Novo Usuário (POST)

Endpoint: http://localhost:8081/api/usuarios

Descrição: Cadastra um novo perfil no sistema.

Exemplo de Chamada (JSON):

{

  "nomeUsuario": "Kelryn Ramos",
  
  "email": "kelryn@email.com",
  
  "senhaHash": "senha123",
  
  "eAdm": false
  
}

B. Listar Todos os Usuários (GET)

Endpoint: http://localhost:8081/api/usuarios

Descrição: Retorna a lista de todos os usuários (incluindo o Marvin criado automaticamente).

C. Deletar Usuário (DELETE)

Endpoint: http://localhost:8081/api/usuarios/{id}

Descrição: Remove um usuário. Retorna 204 No Content em caso de sucesso.

2. Contexto de Receitas (Recipe Management)
   
A. Criar Nova Receita (POST)

Endpoint: http://localhost:8081/api/receitas

Descrição: Publica uma receita vinculada a um autor.

Exemplo de Chamada (JSON):

{

  "titulo": "Torta de Limão Especial",
  
  "ingredientes": "Leite condensado, limão, biscoito, manteiga.",
  
  "passoApasso": "Triture a bolacha, faça a base, adicione o mousse e resfrie.",
  
  "autorId": 1
  
}

B. Listar Todas as Receitas (GET)

Endpoint: http://localhost:8081/api/receitas

Descrição: Retorna todas as receitas postadas no sistema, com os dados dos respectivos autores.

Próximos Passos (To-do):

[ ] Implementar autenticação de usuários.

[ ] Criar filtros de busca por ingredientes.

[ ] Adicionar upload de imagens para as receitas.
