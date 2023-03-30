package com.luis.helpdesk.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil {
    @Value("\${jwt.expiration}")
    private var expiration: Long = 0

    @Value("\${jwt.secret}")
    private var secret: String = ""

    fun generateToken(email: String): String? {
        return Jwts.builder().setSubject(email).setExpiration(Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.toByteArray()).compact()
    }
}