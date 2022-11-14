package com.luis.helpdesk.services

import com.luis.helpdesk.domain.Cliente
import com.luis.helpdesk.domain.dtos.ClienteDTO
import com.luis.helpdesk.domain.exceptions.ObjectNotFoundException
import com.luis.helpdesk.repositories.ClienteRepository
import com.luis.helpdesk.repositories.PessoaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClienteService {

    @Autowired
    private lateinit var clienteRepository: ClienteRepository

    @Autowired
    private lateinit var pessoaRepository: PessoaRepository

    fun findById(id: Int?): Cliente {
        val obj: Optional<Cliente> = clienteRepository.findById(id!!)
        return obj.orElseThrow { ObjectNotFoundException("Objeto não encontrado id:$id", null) }
    }

    fun findAll(): List<Cliente> = clienteRepository.findAll()

    fun save(clienteDTO: ClienteDTO): Cliente {
        clienteDTO.id = null
        return clienteRepository.save(Cliente(clienteDTO))
    }

    fun update(id: Int, clienteDTO: ClienteDTO): Cliente {
        clienteDTO.id = id
        var clienteOld = findById(id)
        validaPorCpfEEmail(clienteOld)
        clienteOld = Cliente(clienteDTO)
        return clienteRepository.save(clienteOld)
    }

    private fun validaPorCpfEEmail(clienteDTO: Cliente) {
        var obj = pessoaRepository.findPessoaByCpf(clienteDTO.cpf)
        if (obj.isPresent && obj.get().id != clienteDTO.id) {
            throw DataIntegrityViolationException("CPF já cadastrado no sistema!")
        }

        obj = pessoaRepository.findPessoaByEmail(clienteDTO.email)
        if (obj.isPresent && obj.get().id != clienteDTO.id) {
            throw DataIntegrityViolationException("E-mail já cadastrado no sistema!")
        }
    }

    fun delete(id: Int) {
        val tecnico = findById(id)
        if (tecnico.chamados?.size!! > 0) {
            throw DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!")
        }

        clienteRepository.delete(tecnico)
    }
}