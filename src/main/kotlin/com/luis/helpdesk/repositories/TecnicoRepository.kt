package com.luis.helpdesk.repositories

import com.luis.helpdesk.domain.Tecnico
import org.springframework.data.jpa.repository.JpaRepository

interface TecnicoRepository : JpaRepository<Tecnico, Int> {
}