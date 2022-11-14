package com.luis.helpdesk.services

import com.luis.helpdesk.domain.Tecnico
import com.luis.helpdesk.domain.dtos.TecnicoDTO
import com.luis.helpdesk.domain.exceptions.ObjectNotFoundException
import com.luis.helpdesk.repositories.PessoaRepository
import com.luis.helpdesk.repositories.TecnicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class TecnicoService {

    @Autowired
    private lateinit var tecnicoRepository: TecnicoRepository

    @Autowired
    private lateinit var pessoaRepository: PessoaRepository

    fun findById(id: Int?): Tecnico {
        val obj: Optional<Tecnico> = tecnicoRepository.findById(id!!)
        return obj.orElseThrow { ObjectNotFoundException("Objeto não encontrado id:$id", null) }
    }

    fun findAll(): List<Tecnico> = tecnicoRepository.findAll()
    fun save(tecnicoDto: TecnicoDTO): Tecnico {
        tecnicoDto.id = null
        return tecnicoRepository.save(Tecnico(tecnicoDto))
    }

    fun update(id: Int, tecnicoDTO: TecnicoDTO): Tecnico {
        tecnicoDTO.id = id
        var tecnicoOld = findById(id)
        validaPorCpfEEmail(tecnicoOld)
        tecnicoOld = Tecnico(tecnicoDTO)
        return tecnicoRepository.save(tecnicoOld)
    }

    private fun validaPorCpfEEmail(tecnicoOld: Tecnico) {
        var obj = pessoaRepository.findPessoaByCpf(tecnicoOld.cpf)
        if (obj.isPresent && obj.get().id != tecnicoOld.id) {
            throw DataIntegrityViolationException("CPF já cadastrado no sistema!")
        }

        obj = pessoaRepository.findPessoaByEmail(tecnicoOld.email)
        if (obj.isPresent && obj.get().id != tecnicoOld.id) {
            throw DataIntegrityViolationException("E-mail já cadastrado no sistema!")
        }
    }

    fun delete(id: Int) {
        val tecnico = findById(id)
        if (tecnico.chamados?.size!! > 0) {
            throw DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!")
        }

        tecnicoRepository.delete(tecnico)
    }
}