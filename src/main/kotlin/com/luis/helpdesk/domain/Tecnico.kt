package com.luis.helpdesk.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.luis.helpdesk.domain.dtos.TecnicoDTO
import com.luis.helpdesk.domain.enums.Perfil
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Tecnico(
    id: Int?,
    nome: String?,
    cpf: String?,
    email: String?,
    senha: String?,
    perfis: MutableSet<Int>?,
    dataCriacao: LocalDate?
) : Pessoa(id, nome, cpf, email, senha, perfis ?: mutableSetOf(Perfil.TECNICO.codigo), dataCriacao ?: LocalDate.now()) {

    constructor(tecnico: TecnicoDTO) : this(
        id = tecnico.id,
        nome = tecnico.nome,
        cpf = tecnico.cpf,
        email = tecnico.email,
        senha = tecnico.senha,
        perfis = tecnico.perfis,
        dataCriacao = tecnico.dataCriacao
    )

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    var chamados: MutableList<Chamado>? = mutableListOf()
}