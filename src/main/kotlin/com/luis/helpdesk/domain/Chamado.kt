package com.luis.helpdesk.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.luis.helpdesk.domain.dtos.ChamadoDTO
import com.luis.helpdesk.domain.enums.Prioridade
import com.luis.helpdesk.domain.enums.Status
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
class Chamado(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @JsonFormat(pattern = "dd/MM/yyyy")
    var dataAbertura: LocalDate? = LocalDate.now(),

    @JsonFormat(pattern = "dd/MM/yyyy")
    var dataFechamento: LocalDate? = null,

    var prioridade: Prioridade? = Prioridade.BAIXA,

    var status: Status? = Status.ABERTO,

    var titulo: String? = null,

    var observacoes: String? = null,

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    var tecnico: Tecnico? = null,

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    var cliente: Cliente? = null

) : Serializable {

    constructor(chamadoDTO: ChamadoDTO): this(
        id = chamadoDTO.id,
        dataAbertura = chamadoDTO.dataAbertura,
        dataFechamento = chamadoDTO.dataFechamento,
        prioridade = Prioridade.toEnum(chamadoDTO.prioridade),
        status = Status.toEnum(chamadoDTO.status),
        titulo = chamadoDTO.titulo,
        observacoes = chamadoDTO.observacoes
    )

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