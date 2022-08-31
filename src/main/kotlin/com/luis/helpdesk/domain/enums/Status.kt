package com.luis.helpdesk.domain.enums

enum class Status(
    val codigo: Int,
    val descricao: String
) {

    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
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