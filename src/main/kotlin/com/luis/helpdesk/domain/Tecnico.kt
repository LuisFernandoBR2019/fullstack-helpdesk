package com.luis.helpdesk.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.luis.helpdesk.domain.dtos.TecnicoDTO
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Tecnico(
    id: Int?,
    nome: String?,
    cpf: String?,
    email: String?,
    senha: String?
) : Pessoa(id, nome, cpf, email, senha) {

    constructor(tecnico: TecnicoDTO) : this(
        id = tecnico.id,
        nome = tecnico.nome,
        cpf = tecnico.cpf,
        email = tecnico.email,
        senha = tecnico.senha
    ) {
        this.perfis = tecnico.perfis
        this.dataCriacao = tecnico.dataCriacao
    }

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    var chamados: MutableList<Chamado>? = mutableListOf()
}