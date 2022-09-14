package com.luis.helpdesk.repositories

import com.luis.helpdesk.domain.Pessoa
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PessoaRepository : JpaRepository<Pessoa, Int>{
    fun findPessoaByCpf(cpf: String?) : Optional<Pessoa>
    fun findPessoaByEmail(email: String?): Optional<Pessoa>
}