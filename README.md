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


📂 Estrutura do Projeto (Entidades)

Usuario: Classe base que gerencia os perfis (Administrador, Criador, Consumidor).

Receita: Contém o título, ingredientes, passo a passo e a média de avaliações.

Avaliacao: Gerencia as estrelas deixadas pelos usuários (Lógica de voto único).

ColecaoPessoal: Permite aos usuários favoritar e organizar receitas.

⚙️ Regras de Negócio Implementadas

Média Automática: A média de avaliações de cada receita é calculada dinamicamente com base nas notas recebidas.

Voto Único: O sistema impede que um usuário avalie a mesma receita mais de uma vez (Integridade de Dados).

Herança de Usuários: Diferenciação entre usuários comuns e administradores através de @Inheritance no JPA.

Próximos Passos (To-do):

[ ] Implementar autenticação de usuários.

[ ] Criar filtros de busca por ingredientes.

[ ] Adicionar upload de imagens para as receitas.
