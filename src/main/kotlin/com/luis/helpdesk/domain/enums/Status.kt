package com.luis.helpdesk.domain.enums

enum class Status(
    val codigo: Int? = null,
    val descricao: String? = null
) {

    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    companion object{
        fun toEnum(cod: Int?): Status? {
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
}