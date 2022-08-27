package com.luis.helpdesk.repositories

import com.luis.helpdesk.domain.Pessoa
import org.springframework.data.jpa.repository.JpaRepository

interface PessoaRepository : JpaRepository<Pessoa, Int>{
}