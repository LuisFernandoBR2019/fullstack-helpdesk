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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class DBService {

    @Autowired
    private lateinit var tecnicoRepository: TecnicoRepository

    @Autowired
    private lateinit var clienteRepository: ClienteRepository

    @Autowired
    private lateinit var chamadoRepository: ChamadoRepository

    @Autowired
    private lateinit var encoder: BCryptPasswordEncoder


    fun instanciaDB() {
        val tecnico = Tecnico(null, "Luis", "12234568549", "nando@kotlin.com", encoder.encode("123"))

        val cliente = Cliente(null, "Diana", "15998526458", "diana@kotlin.com", encoder.encode("321"))

        val chamado = Chamado(
                null,
                prioridade = Prioridade.MEDIA,
                status = Status.ANDAMENTO,
                titulo = "Chamado 01",
                observacoes = "Primeiro Chamado",
                tecnico = tecnico,
                cliente = cliente
        )
        tecnicoRepository.save(tecnico)
        clienteRepository.save(cliente)
        chamadoRepository.save(chamado)
    }
}