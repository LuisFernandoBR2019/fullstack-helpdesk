package com.luis.helpdesk.domain

class Cliente : Pessoa() {
    val chamados : MutableList<Chamado>? = mutableListOf()
}