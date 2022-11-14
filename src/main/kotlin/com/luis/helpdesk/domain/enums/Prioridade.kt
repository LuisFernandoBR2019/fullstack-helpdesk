package com.luis.helpdesk.domain.enums

enum class Prioridade(
    val codigo: Int? = null,
    val descricao: String? = null
) {

    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    companion object{
        fun toEnum(cod: Int?): Prioridade? {
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
}