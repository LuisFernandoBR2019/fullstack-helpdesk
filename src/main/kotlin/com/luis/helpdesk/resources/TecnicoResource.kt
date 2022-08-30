package com.luis.helpdesk.resources

import com.luis.helpdesk.domain.Tecnico
import com.luis.helpdesk.services.TecnicoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tecnicos")
class TecnicoResource {

    @Autowired
    private lateinit var tecnicoService: TecnicoService

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Tecnico> {
        val obj = tecnicoService.findById(id)

        return ResponseEntity.ok().body(obj)
    }
}