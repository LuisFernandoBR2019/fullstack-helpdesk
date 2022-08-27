package com.luis.helpdesk.domain

import com.luis.helpdesk.domain.enums.Perfil
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Tecnico : Pessoa() {

    @OneToMany(mappedBy = "tecnico")
    var chamados: MutableList<Chamado>? = mutableListOf()
    open fun toEntity(id: Int?, nome: String?, cpf: String?, email: String?, senha: String?) : Tecnico{
        this.id = id
        this.nome = nome
        this.cpf = cpf
        this.email = email
        this.senha = senha

        addPerfil(Perfil.CLIENTE)

        return this
    }
}