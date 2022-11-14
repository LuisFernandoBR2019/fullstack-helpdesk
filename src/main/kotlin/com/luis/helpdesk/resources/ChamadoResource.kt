package com.luis.helpdesk.resources

import com.luis.helpdesk.domain.dtos.ChamadoDTO
import com.luis.helpdesk.services.ChamadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.stream.Collectors
import javax.validation.Valid

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

    @GetMapping
    fun findAll(): ResponseEntity<List<ChamadoDTO>> {
        val list = chamadoService.findAll()
        val listDTO = list.stream().map { ChamadoDTO(it) }.collect(Collectors.toList())
        return ResponseEntity.ok().body(listDTO)
    }

    @PostMapping
    fun create(@Valid @RequestBody chamadoDTO: ChamadoDTO): ResponseEntity<ChamadoDTO> {
        val chamado = chamadoService.save(chamadoDTO)
        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.id).toUri()
        return ResponseEntity.created(uri).build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @Valid @RequestBody chamadoDTO: ChamadoDTO): ResponseEntity<ChamadoDTO> {
        val chamado = chamadoService.update(id, chamadoDTO)
        return ResponseEntity.ok().body(ChamadoDTO(chamado))
    }
}