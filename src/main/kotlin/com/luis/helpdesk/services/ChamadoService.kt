package com.luis.helpdesk.services

import com.luis.helpdesk.domain.Chamado
import com.luis.helpdesk.domain.exceptions.ObjectNotFoundException
import com.luis.helpdesk.repositories.ChamadoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChamadoService {
    @Autowired
    private lateinit var repository: ChamadoRepository

    fun findById(id: Int): Chamado {
        val obj: Optional<Chamado> = repository.findById(id)
        return obj.orElseThrow { ObjectNotFoundException("Objeto não encontrado id:$id", null) }
    }
}