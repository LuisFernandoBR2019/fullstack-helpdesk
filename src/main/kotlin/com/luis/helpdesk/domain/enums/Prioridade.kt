package com.luis.helpdesk.domain.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class Prioridade(
    val codigo: Int,
    val descricao: String
) {

    @JsonProperty("BAIXA")
    BAIXA(0, "BAIXA"),

    @JsonProperty("MEDIA")
    MEDIA(1, "MEDIA"),

    @JsonProperty("ALTA")
    ALTA(2, "ALTA");

    open fun toEnum(cod: Int?): Prioridade? {
        if (cod == null) {
            return null
        }
        for (x in values()) {
            if (cod == x.codigo) {
                return x
            }
        }
        throw IllegalArgumentException("Prioridade inválido")
    }
}