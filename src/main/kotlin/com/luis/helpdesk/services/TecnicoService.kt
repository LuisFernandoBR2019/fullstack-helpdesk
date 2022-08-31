package com.luis.helpdesk.services

import com.luis.helpdesk.domain.Tecnico
import com.luis.helpdesk.domain.exceptions.ObjectNotFoundException
import com.luis.helpdesk.repositories.TecnicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TecnicoService {

    @Autowired
    private lateinit var tecnicoRepository: TecnicoRepository

    fun findById(id: Int): Tecnico {
        var obj: Optional<Tecnico> = tecnicoRepository.findById(id)
        return obj.orElseThrow { ObjectNotFoundException("Objeto não encontrado id:$id", null) }
    }

    fun findAll(): List<Tecnico> = tecnicoRepository.findAll()


}