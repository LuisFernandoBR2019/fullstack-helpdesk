package com.luis.helpdesk.domain.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.luis.helpdesk.domain.Tecnico
import java.io.Serializable
import java.time.LocalDate
import javax.validation.constraints.NotNull

class TecnicoDTO(

    open var id: Int? = null,

    @field:NotNull(message = "O campo Nome é requerido")
    open var nome: String? = null,

    @field:NotNull(message = "O campo CPF é requerido")
    open var cpf: String? = null,

    @field:NotNull(message = "O campo Email é requerido")
    open var email: String? = null,

    @field:NotNull(message = "O campo Senha é requerido")
    open var senha: String? = null,

    open var perfis: MutableSet<Int>? = HashSet(),

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