package com.luis.helpdesk.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.luis.helpdesk.domain.enums.Perfil
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
abstract class Pessoa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int? = null,

    open var nome: String? = null,

    @Column(unique = true)
    open var cpf: String? = null,

    @Column(unique = true)
    open var email: String? = null,

    open var senha: String? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    open var perfis: MutableSet<Int>? = HashSet(),

    @JsonFormat(pattern = "dd/MM/yyyy")
    open var dataCriacao: LocalDate? = LocalDate.now()
) : Serializable {

    fun addPerfil(perfil: Perfil) {
        this.perfis?.add(perfil.codigo)
    }
}