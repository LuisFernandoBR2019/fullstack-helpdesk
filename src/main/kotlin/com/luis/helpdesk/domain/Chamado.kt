package com.luis.helpdesk.domain

import com.luis.helpdesk.domain.enums.Prioridade
import com.luis.helpdesk.domain.enums.Status
import java.time.LocalDate

class Chamado {
    val id : Int? = null
    val dataAbertura : LocalDate = LocalDate.now()
    val dataFechamento : LocalDate? = null
    val prioridade : Prioridade? = Prioridade.BAIXA
    val status : Status? = Status.ABERTO
    val titulo : String? = null
    val observacoes : String? = null

    val tecnico : Tecnico? = null
    val cliente : Cliente? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Chamado

        if (id != other.id) return false
        if (dataAbertura != other.dataAbertura) return false
        if (dataFechamento != other.dataFechamento) return false
        if (prioridade != other.prioridade) return false
        if (status != other.status) return false
        if (titulo != other.titulo) return false
        if (observacoes != other.observacoes) return false
        if (tecnico != other.tecnico) return false
        if (cliente != other.cliente) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + dataAbertura.hashCode()
        result = 31 * result + (dataFechamento?.hashCode() ?: 0)
        result = 31 * result + (prioridade?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + (titulo?.hashCode() ?: 0)
        result = 31 * result + (observacoes?.hashCode() ?: 0)
        result = 31 * result + (tecnico?.hashCode() ?: 0)
        result = 31 * result + (cliente?.hashCode() ?: 0)
        return result
    }


}