# 🍳 Rede Social de Nicho - Receitas Culinárias (v2.0)

Este projeto consiste em uma Aplicação Web MVC desenvolvida com Spring Boot para uma rede social focada no compartilhamento, avaliação e comentários de receitas. O sistema permite que usuários criem perfis, publiquem suas próprias receitas, interajam com conteúdos de outros usuários e gerenciem coleções pessoais de forma visual e intuitiva.

## 🚀 Tecnologias Utilizadas
* **Backend:** Java 21, Spring Boot 4.0.5, Spring Data JPA
* **Banco de Dados:** H2 Database (Em memória)
* **Frontend:** Thymeleaf, HTML5, CSS3
* **Ferramentas:** Lombok, Maven

## 🛠️ Manual de Execução

Siga os passos abaixo para rodar a aplicação localmente:

**1. Preparação do Ambiente:**
* Recomendamos que o projeto seja extraído e executado fora de pastas sincronizadas em nuvem (como OneDrive/Dropbox) para evitar conflitos de permissão e bloqueios do banco de dados local.
* Certifique-se de que a porta `8081` está disponível (configurada via `application.yml`).

**2. Executando o Projeto:**
* Clone ou extraia o repositório em uma pasta local (Ex: `C:\Projetos\redesocialnicho`).
* Abra o projeto na sua IDE de preferência.
* Execute a classe principal `ApiProjetoApplication.java` ou rode o comando no terminal:
  `mvn spring-boot:run`

**3. Acessando a Aplicação:**
* Com o servidor iniciado, abra seu navegador e acesse:
  👉 **http://localhost:8081/receitas/lista**

*Nota: Ao iniciar a aplicação com o banco vazio, o usuário **Administrador** padrão será criado automaticamente para facilitar os testes das funcionalidades.*

## 📂 Estrutura do Projeto (Entidades)
* **Usuario:** Classe base que gerencia os perfis (Administrador, Criador, Consumidor).
* **Receita:** Contém o título, ingredientes, passo a passo, avaliações e comentários associados.
* **Avaliacao:** Gerencia as estrelas deixadas pelos usuários (Lógica de voto único).
* **Comentario:** Permite a interação textual (mensagens) entre usuários nas receitas.
* **ColecaoPessoal:** Permite aos usuários favoritar e organizar receitas.

## ⚙️ Regras de Negócio Implementadas
* **Interface Dinâmica:** Telas geradas no lado do servidor utilizando fragmentos do Thymeleaf.
* **Média Automática:** A média de avaliações de cada receita é calculada dinamicamente com base nas notas recebidas e convertida em uma representação visual de estrelas.
* **Voto Único:** O sistema impede que um usuário avalie a mesma receita mais de uma vez através de regras de integridade do banco (Chave Única).
* **Herança de Usuários:** Diferenciação entre usuários comuns e administradores utilizando `@Inheritance` no JPA.
* **Tratamento de Exceções:** Uso de `@ControllerAdvice` para capturar falhas (como acessos a IDs inexistentes) e direcionar o usuário para páginas de erro customizadas (404 e 500) sem quebrar o sistema.

## 📸 Guia de Telas
* Lista de Usuários
  <img width="1917" height="858" alt="image" src="https://github.com/user-attachments/assets/7645123e-0aa6-422e-bf58-8e7087b2fc6d" />
* Novo Usuário
  <img width="1911" height="868" alt="image" src="https://github.com/user-attachments/assets/114ac1cd-abeb-424c-9ea5-be99091f7fa0" />
* Detalhes da Receita, Avaliação e Comentários
  <img width="1899" height="968" alt="image" src="https://github.com/user-attachments/assets/069075d7-1653-4ad4-8517-dabeac85f5db" />
* Nova Receita
  <img width="1900" height="971" alt="image" src="https://github.com/user-attachments/assets/dcffde2b-cddf-4ebf-8948-8a2715c2d2ed" />
* Erro 404
  <img width="1919" height="868" alt="image" src="https://github.com/user-attachments/assets/7acc5b9e-3379-46ec-aa0a-3c0664a5294c" />
* Erro Tratado
  <img width="1918" height="868" alt="image" src="https://github.com/user-attachments/assets/e0c0cd15-1919-412a-a373-79994e1cb21e" />
* Erro 500
  <img width="1918" height="865" alt="image" src="https://github.com/user-attachments/assets/2eed3db6-cb81-42ef-aa53-42b253dc10c8" />


## 📋 Próximos Passos (To-do)
- [ ] Implementar autenticação e login seguro de usuários (Spring Security).
- [ ] Criar barra de busca de receitas por título ou ingredientes.
- [ ] Adicionar upload de fotos reais no formulário de receitas.
- [ ] Criar a interface visual para os usuários favoritarem receitas e gerenciarem suas "Coleções Pessoais".

---
Desenvolvido por Marvin e Emily
Link da Release Final: https://github.com/Rede-Social-de-Nicho/Rede-Social-de-Nicho/releases/tag/v2.0.0-p2
