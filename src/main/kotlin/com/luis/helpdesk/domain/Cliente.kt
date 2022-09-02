package com.luis.helpdesk.domain

import com.luis.helpdesk.domain.enums.Perfil
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.OneToMany


@Entity
class Cliente(
    id: Int?,
    nome: String?,
    cpf: String?,
    email: String?,
    senha: String?,
    perfis: MutableSet<Int>?,
    dataCriacao: LocalDate?
) : Pessoa(id, nome, cpf, email, senha,  perfis ?: mutableSetOf(Perfil.CLIENTE.codigo), dataCriacao ?: LocalDate.now()) {

    @OneToMany(mappedBy = "cliente")
    var chamados: MutableList<Chamado>? = mutableListOf()
}