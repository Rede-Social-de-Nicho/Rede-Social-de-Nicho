# Rede-Social-de-Nicho
Este projeto é uma rede social de nicho, focada em receitas culinárias ou resenhas de livros, onde os usuários podem: 
Criar e compatilhar suas própias postagens (receitas ou resenhas); 
Interagir com o conteúdo de outros usuários; 
Favoritas postagens e adicioná-las a coleções pessoais; 
Avaliar e comentar as publicações.
O sistema permite tanto criadores de conteúdo quanto usuários comuns, com o gerenciamento geral feito por administradores.
Perfis de usuários
Administrador: Responsável por gerenciar usuários, receitas/resenhas e manter o sistema.
Criador de Conteúdo: Usuário que publica receitas ou resenhas.
Usuário/Consumidor: Pode visualizar, favoritar, comentar e avaliar postagens.
Lógica de Negócio Principal:
Qualquer Usuário pode ser um Criador (postar Receitas).
Um Usuário pode "Favoritar" Receitas de outros usuários, adicionando-as a uma "Coleção Pessoal" (relação ManyToMany entre Usuario e Receita).
Um Usuário pode deixar uma Avaliação (ex: 1 a 5 estrelas) em uma Receita.
Lógica de Voto Único: A API deve impedir que o mesmo Usuário avalie a mesma Receita múltiplas vezes.
A API deve calcular e exibir a média de avaliações de cada Receita.
