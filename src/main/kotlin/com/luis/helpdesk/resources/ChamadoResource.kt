package com.luis.helpdesk.resources

import com.luis.helpdesk.domain.dtos.ChamadoDTO
import com.luis.helpdesk.services.ChamadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chamados")
class ChamadoResource {
    @Autowired
    private lateinit var chamadoService: ChamadoService

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<ChamadoDTO> {
        val obj = chamadoService.findById(id)

        return ResponseEntity.ok().body(ChamadoDTO(obj))
    }
}