package com.luis.helpdesk.domain.enums

enum class Perfil(codigo: Int, descricao: String) {

    ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    open fun toEnum(cod: Int?): Perfil? {
        if (cod == null) {
            return null
        }
        for (x in values()) {
            if (cod == x.ordinal) {
                return x
            }
        }
        throw IllegalArgumentException("Perfil inválido")
    }
}