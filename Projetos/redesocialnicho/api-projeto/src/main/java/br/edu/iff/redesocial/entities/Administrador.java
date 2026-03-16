package br.edu.iff.redesocial.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrador extends Usuario {
    
    public Administrador() {
        this.seteAdm(true);
    }
    
    /* 
    Classe administrador herda da classe usuário, ou seja, tem as mesmas características de um user comum.
    A diferença é que o administrador tem acesso a funções extras, como moderar conteúdo e gerenciar users
    que serão implementadas na camada de serviço, sendo chamadas pelos métodos do controlador.
    Os métodos do diagrama (gerenciarUsuario e moderarConteudo).
    */
}