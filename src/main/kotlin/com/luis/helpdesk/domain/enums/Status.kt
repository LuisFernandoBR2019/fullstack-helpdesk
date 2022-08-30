package com.luis.helpdesk.domain.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class Status(
    val codigo: Int,
    val descricao: String
) {

    @JsonProperty("ABERTO")
    ABERTO(0, "ABERTO"),
    @JsonProperty("ANDAMENTO")
    ANDAMENTO(1, "ANDAMENTO"),
    @JsonProperty("ENCERRADO")
    ENCERRADO(2, "ENCERRADO");

    open fun toEnum(cod: Int?): Status? {
        if (cod == null) {
            return null
        }
        for (x in values()) {
            if (cod == x.codigo) {
                return x
            }
        }
        throw IllegalArgumentException("Status inválido")
    }
}