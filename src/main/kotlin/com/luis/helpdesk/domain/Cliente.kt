package com.luis.helpdesk.domain

import javax.persistence.Entity
import javax.persistence.OneToMany


@Entity
class Cliente(
    id: Int?,
    nome: String?,
    cpf: String?,
    email: String?,
    senha: String?,
) : Pessoa(id, nome, cpf, email, senha) {

    @OneToMany(mappedBy = "cliente")
    var chamados: MutableList<Chamado>? = mutableListOf()
}