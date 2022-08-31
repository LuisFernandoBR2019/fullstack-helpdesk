package com.luis.helpdesk.domain.enums

enum class Prioridade(
    val codigo: Int,
    val descricao: String
) {

    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
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