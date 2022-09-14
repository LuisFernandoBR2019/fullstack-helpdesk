package com.luis.helpdesk.resources

import com.luis.helpdesk.domain.dtos.TecnicoDTO
import com.luis.helpdesk.services.TecnicoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/tecnicos")
class TecnicoResource {

    @Autowired
    private lateinit var tecnicoService: TecnicoService

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<TecnicoDTO> {
        val obj = tecnicoService.findById(id)

        return ResponseEntity.ok().body(TecnicoDTO(obj))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<TecnicoDTO>> {
        val list = tecnicoService.findAll()
        val listDTO = list.stream().map { TecnicoDTO(it) }.collect(Collectors.toList())
        return ResponseEntity.ok().body(listDTO)
    }

    @PostMapping
    fun create(@Valid @RequestBody tecnicoDTO: TecnicoDTO): ResponseEntity<TecnicoDTO> {
        val tecnico = tecnicoService.save(tecnicoDTO)
        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.id).toUri()
        return ResponseEntity.created(uri).build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @Valid @RequestBody tecnicoDTO: TecnicoDTO): ResponseEntity<TecnicoDTO> {
        val tecnico = tecnicoService.update(id, tecnicoDTO)
        return ResponseEntity.ok().body(TecnicoDTO(tecnico))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<TecnicoDTO> {
        tecnicoService.delete(id)
        return ResponseEntity.noContent().build()
    }
}