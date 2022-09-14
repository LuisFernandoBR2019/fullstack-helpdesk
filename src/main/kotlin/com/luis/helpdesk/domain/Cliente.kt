package com.luis.helpdesk.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.luis.helpdesk.domain.dtos.ClienteDTO
import javax.persistence.Entity
import javax.persistence.OneToMany


@Entity
class Cliente(
    id: Int?,
    nome: String?,
    cpf: String?,
    email: String?,
    senha: String?
) : Pessoa(id, nome, cpf, email, senha) {

    constructor(cliente: ClienteDTO) : this(
        id = cliente.id,
        nome = cliente.nome,
        cpf = cliente.cpf,
        email = cliente.email,
        senha = cliente.senha
    ) {
        this.perfis = cliente.perfis
        this.dataCriacao = cliente.dataCriacao
    }

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    var chamados: MutableList<Chamado>? = mutableListOf()
}