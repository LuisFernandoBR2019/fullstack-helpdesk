package com.luis.helpdesk.domain.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.luis.helpdesk.domain.Chamado
import java.io.Serializable
import java.time.LocalDate
import javax.validation.constraints.NotNull

class ChamadoDTO(

    var id: Int? = null,

    @JsonFormat(pattern = "dd/MM/yyyy")
    var dataAbertura: LocalDate? = LocalDate.now(),

    @JsonFormat(pattern = "dd/MM/yyyy")
    var dataFechamento: LocalDate? = null,

    @field:NotNull(message = "O campo Prioridade é requerido")
    var prioridade: Int? = null,

    @field:NotNull(message = "O campo Status é requerido")
    var status: Int? = null,

    @field:NotNull(message = "O campo Titulo é requerido")
    var titulo: String? = null,

    @field:NotNull(message = "O campo Observações é requerido")
    var observacoes: String? = null,

    @field:NotNull(message = "O campo Técnico é requerido")
    var tecnico: Int? = null,

    @field:NotNull(message = "O campo Cliente é requerido")
    var cliente: Int? = null,

    var nomeTecnico: String? = null,

    var nomeCliente: String? = null
) : Serializable {

    constructor(chamado: Chamado) : this(
        id = chamado.id,
        dataAbertura = chamado.dataAbertura,
        dataFechamento = chamado.dataFechamento,
        prioridade = chamado.prioridade?.codigo,
        status = chamado.status?.codigo,
        titulo = chamado.titulo,
        observacoes = chamado.observacoes,
        tecnico = chamado.tecnico?.id,
        cliente = chamado.cliente?.id,
        nomeTecnico = chamado.tecnico?.nome,
        nomeCliente = chamado.cliente?.nome
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChamadoDTO

        if (id != other.id) return false
        if (dataAbertura != other.dataAbertura) return false
        if (dataFechamento != other.dataFechamento) return false
        if (prioridade != other.prioridade) return false
        if (status != other.status) return false
        if (titulo != other.titulo) return false
        if (observacoes != other.observacoes) return false
        if (tecnico != other.tecnico) return false
        if (cliente != other.cliente) return false
        if (nomeTecnico != other.nomeTecnico) return false
        if (nomeCliente != other.nomeCliente) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (dataAbertura?.hashCode() ?: 0)
        result = 31 * result + (dataFechamento?.hashCode() ?: 0)
        result = 31 * result + (prioridade ?: 0)
        result = 31 * result + (status ?: 0)
        result = 31 * result + (titulo?.hashCode() ?: 0)
        result = 31 * result + (observacoes?.hashCode() ?: 0)
        result = 31 * result + (tecnico ?: 0)
        result = 31 * result + (cliente ?: 0)
        result = 31 * result + (nomeTecnico?.hashCode() ?: 0)
        result = 31 * result + (nomeCliente?.hashCode() ?: 0)
        return result
    }


}