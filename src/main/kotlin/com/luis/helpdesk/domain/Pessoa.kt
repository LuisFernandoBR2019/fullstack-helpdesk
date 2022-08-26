package com.luis.helpdesk.domain

import com.luis.helpdesk.domain.enums.Perfil
import java.time.LocalDate
import java.util.stream.Collectors

abstract class Pessoa {

    protected val id : Int? = null
    protected val nome : String? = null
    protected val cpf : String? = null
    protected val email : String? = null
    protected val senha : String? = null
    protected val perfis : MutableSet<Int> = HashSet()
    protected val dataCriacao : LocalDate? = LocalDate.now()

    constructor(){
        addPerfil(Perfil.CLIENTE)
    }

    fun getPerfisMap() = perfis?.stream()?.map { x -> Perfil.valueOf(x.toString()).toEnum(x)}?.collect(Collectors.toSet())

    fun addPerfil(perfil: Perfil) {
        this.perfis?.add(perfil.ordinal)
    }


}