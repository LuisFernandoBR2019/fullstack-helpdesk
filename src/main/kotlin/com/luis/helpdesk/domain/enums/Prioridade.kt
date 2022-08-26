package com.luis.helpdesk.domain.enums

enum class Prioridade(codigo: Int, descricao: String) {

    BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    open fun toEnum(cod: Int?): Prioridade? {
        if (cod == null) {
            return null
        }
        for (x in values()) {
            if (cod == x.ordinal) {
                return x
            }
        }
        throw IllegalArgumentException("Prioridade inválido")
    }
}