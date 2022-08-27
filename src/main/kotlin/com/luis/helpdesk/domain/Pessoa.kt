package com.luis.helpdesk.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.luis.helpdesk.domain.enums.Perfil
import java.io.Serializable
import java.time.LocalDate
import java.util.stream.Collectors
import javax.persistence.*

@Entity
abstract class Pessoa : Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected open val id : Int? = null

    protected open val nome : String? = null

    @Column(unique = true)
    protected open val cpf : String? = null

    @Column(unique = true)
    protected open val email : String? = null

    protected open val senha : String? = null

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected open val perfis : MutableSet<Int> = HashSet()

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected open val dataCriacao : LocalDate? = LocalDate.now()

    constructor(){
        addPerfil(Perfil.CLIENTE)
    }

    fun getPerfisMap() = perfis?.stream()?.map { x -> Perfil.valueOf(x.toString()).toEnum(x)}?.collect(Collectors.toSet())

    fun addPerfil(perfil: Perfil) {
        this.perfis?.add(perfil.ordinal)
    }


}