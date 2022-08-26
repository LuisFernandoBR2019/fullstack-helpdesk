package com.luis.helpdesk.domain

class Tecnico : Pessoa() {
    val chamados : MutableList<Chamado>? = mutableListOf()
}