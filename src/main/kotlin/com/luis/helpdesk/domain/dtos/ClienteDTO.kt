package com.luis.helpdesk.domain.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.luis.helpdesk.domain.Cliente
import java.io.Serializable
import java.time.LocalDate
import javax.validation.constraints.NotNull

data class ClienteDTO(
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
    constructor(cliente: Cliente) : this(
        id = cliente.id,
        nome = cliente.nome,
        cpf = cliente.cpf,
        email = cliente.email,
        senha = cliente.senha,
        perfis = cliente.perfis,
        dataCriacao = cliente.dataCriacao
    )
}