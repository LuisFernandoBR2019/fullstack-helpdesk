package com.luis.helpdesk.domain

import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Tecnico : Pessoa() {

    @OneToMany(mappedBy = "tecnico")
    val chamados : MutableList<Chamado>? = mutableListOf()
}