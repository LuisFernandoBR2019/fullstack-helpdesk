package com.luis.helpdesk.domain.enums

enum class Perfil(
        val codigo: Int,
        val descricao: String
) {

    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    companion object {
        fun toEnum(cod: Int?): Perfil? {
            if (cod == null) {
                return null
            }
            for (x in values()) {
                if (cod == x.codigo) {
                    return x
                }
            }
            throw IllegalArgumentException("Perfil inválido")
        }
    }
}