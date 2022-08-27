package com.luis.helpdesk.repositories

import com.luis.helpdesk.domain.Chamado
import org.springframework.data.jpa.repository.JpaRepository

interface ChamadoRepository : JpaRepository<Chamado, Int>{
}