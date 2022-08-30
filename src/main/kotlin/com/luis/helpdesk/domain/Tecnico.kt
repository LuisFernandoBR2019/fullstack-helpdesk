package com.luis.helpdesk.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Tecnico(
    id: Int?,
    nome: String?,
    cpf: String?,
    email: String?,
    senha: String?,
) : Pessoa(id, nome, cpf, email, senha) {

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    var chamados: MutableList<Chamado>? = mutableListOf()
}