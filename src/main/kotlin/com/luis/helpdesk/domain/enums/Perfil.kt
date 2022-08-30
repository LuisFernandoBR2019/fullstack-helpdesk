package com.luis.helpdesk.domain.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class Perfil(
    val codigo: Int,
    val descricao: String
) {
    @JsonProperty("ADMIN")
    ADMIN(0, "ROLE_ADMIN"),
    @JsonProperty("CLIENTE")
    CLIENTE(1, "ROLE_CLIENTE"),
    @JsonProperty("TECNICO")
    TECNICO(2, "ROLE_TECNICO");

    open fun toEnum(cod: Int?): Perfil? {
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