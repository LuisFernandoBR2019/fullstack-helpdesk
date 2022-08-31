package com.luis.helpdesk.domain.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.luis.helpdesk.domain.Tecnico
import java.io.Serializable
import java.time.LocalDate

class TecnicoDTO(

    open var id: Int? = null,

    open var nome: String? = null,

    open var cpf: String? = null,

    open var email: String? = null,

    open var senha: String? = null,

    open var perfis: MutableSet<Int> = HashSet(),

    @JsonFormat(pattern = "dd/MM/yyyy")
    open var dataCriacao: LocalDate? = LocalDate.now()

) : Serializable {
    constructor(tecnico: Tecnico) : this(
        id = tecnico.id,
        nome = tecnico.nome,
        cpf = tecnico.cpf,
        email = tecnico.email,
        senha = tecnico.senha,
        perfis = tecnico.perfis,
        dataCriacao = tecnico.dataCriacao
    )
}