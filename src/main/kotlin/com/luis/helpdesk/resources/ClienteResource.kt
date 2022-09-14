package com.luis.helpdesk.resources

import com.luis.helpdesk.domain.dtos.ClienteDTO
import com.luis.helpdesk.services.ClienteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/clientes")
class ClienteResource {

    @Autowired
    private lateinit var clienteService: ClienteService

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<ClienteDTO> {
        val obj = clienteService.findById(id)

        return ResponseEntity.ok().body(ClienteDTO(obj))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<ClienteDTO>> {
        val list = clienteService.findAll()
        val listDTO = list.stream().map { ClienteDTO(it) }.collect(Collectors.toList())
        return ResponseEntity.ok().body(listDTO)
    }

    @PostMapping
    fun create(@Valid @RequestBody clienteDTO: ClienteDTO): ResponseEntity<ClienteDTO> {
        val cliente = clienteService.save(clienteDTO)
        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.id).toUri()
        return ResponseEntity.created(uri).build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @Valid @RequestBody clienteDTO: ClienteDTO): ResponseEntity<ClienteDTO> {
        val cliente = clienteService.update(id, clienteDTO)
        return ResponseEntity.ok().body(ClienteDTO(cliente))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<ClienteDTO> {
        clienteService.delete(id)
        return ResponseEntity.noContent().build()
    }
}