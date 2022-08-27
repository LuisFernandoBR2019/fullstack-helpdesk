package com.luis.helpdesk.repositories

import com.luis.helpdesk.domain.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository : JpaRepository<Cliente, Int>{
}