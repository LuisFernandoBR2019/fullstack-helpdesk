package com.luis.helpdesk.services

import com.luis.helpdesk.domain.Chamado
import com.luis.helpdesk.domain.dtos.ChamadoDTO
import com.luis.helpdesk.domain.enums.Prioridade
import com.luis.helpdesk.domain.enums.Status
import com.luis.helpdesk.domain.exceptions.ObjectNotFoundException
import com.luis.helpdesk.repositories.ChamadoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class ChamadoService {
    @Autowired
    private lateinit var chamadoRepository: ChamadoRepository
    @Autowired
    private lateinit var clienteService: ClienteService
    @Autowired
    private lateinit var tecnicoService: TecnicoService

    fun findById(id: Int): Chamado {
        val obj: Optional<Chamado> = chamadoRepository.findById(id)
        return obj.orElseThrow { ObjectNotFoundException("Objeto não encontrado id:$id", null) }
    }

    fun findAll(): List<Chamado> = chamadoRepository.findAll()

    fun save(chamadoDTO: ChamadoDTO) = chamadoRepository.save(newChamado(chamadoDTO))

    fun newChamado(chamadoDTO: ChamadoDTO): Chamado{
        val tecnico = tecnicoService.findById(chamadoDTO.tecnico)
        val cliente = clienteService.findById(chamadoDTO.cliente)
        var chamado = Chamado()
        if(chamadoDTO.id != null){
            chamado.id = chamadoDTO.id
        }

        if(chamado.status!!.equals(2)){
            chamado.dataFechamento = LocalDate.now()
        }

        chamado.tecnico = tecnico
        chamado.cliente = cliente
        chamado.prioridade = Prioridade.toEnum(chamadoDTO.prioridade)
        chamado.status = Status.toEnum(chamadoDTO.status)
        chamado.titulo = chamadoDTO.titulo
        chamado.observacoes = chamadoDTO.observacoes

        return chamado
    }

    fun update(id: Int, chamadoDTO: ChamadoDTO): Chamado {
        chamadoDTO.id = id
        var chamadoOld = findById(id)
        chamadoOld = newChamado(chamadoDTO)
        return chamadoRepository.save(chamadoOld)
    }
}