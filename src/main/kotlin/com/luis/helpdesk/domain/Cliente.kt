package com.luis.helpdesk.domain

import javax.persistence.Entity
import javax.persistence.OneToMany


@Entity
class Cliente : Pessoa() {

    @OneToMany(mappedBy = "cliente")
    val chamados : MutableList<Chamado>? = mutableListOf()

}