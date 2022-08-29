package com.luis.helpdesk.services

import com.luis.helpdesk.domain.Chamado
import com.luis.helpdesk.domain.Cliente
import com.luis.helpdesk.domain.Tecnico
import com.luis.helpdesk.domain.enums.Prioridade
import com.luis.helpdesk.domain.enums.Status
import com.luis.helpdesk.repositories.ChamadoRepository
import com.luis.helpdesk.repositories.ClienteRepository
import com.luis.helpdesk.repositories.TecnicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DBService {

    @Autowired
    private lateinit var tecnicoRepository: TecnicoRepository

    @Autowired
    private lateinit var clienteRepository: ClienteRepository

    @Autowired
    private lateinit var chamadoRepository: ChamadoRepository


    fun instanciaDB() {
        val tecnico: Tecnico = Tecnico().toEntity(null, "Luis", "12234568549", "nando@kotlin.com", "123")

        val cliente = Cliente().toEntity(null, "Diana", "15998526458", "diana@kotlin.com", "321")

        val chamado = Chamado(
            null,
            prioridade = Prioridade.MEDIA,
            status = Status.ANDAMENTO,
            titulo = "Chamado 01",
            observacoes = "Primeiro Chamado",
            tecnico = tecnico,
            cliente = cliente
        )
        tecnicoRepository.saveAll(Arrays.asList(tecnico))
        clienteRepository.saveAll(Arrays.asList(cliente))
        chamadoRepository.saveAll(Arrays.asList(chamado))
    }
}