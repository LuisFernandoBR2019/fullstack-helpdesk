package com.luis.helpdesk.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.luis.helpdesk.domain.dtos.CredenciaisDTO
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
        var authentication: AuthenticationManager,
        var jwtUtil: JWTUtil) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val creds = ObjectMapper().readValue(request?.inputStream, CredenciaisDTO::class.java)
            val authenticationToken = UsernamePasswordAuthenticationToken(creds.email, creds.senha, mutableListOf())
            return authentication.authenticate(authenticationToken)
        } catch (e: Exception) {
            e.stackTrace
            throw RuntimeException()
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val username = (authResult?.principal as UserSS).username
        val token = jwtUtil.generateToken(username!!)
        response?.setHeader("acceess-control-expose-headers", "Authorization")
        response?.setHeader("Authorization", "Bear $token")
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, failed: AuthenticationException?) {
        response?.status = 401
        response?.contentType = "application/json"
        response?.writer?.append(json())
    }

    private fun json(): String {
        val date = Date().time
        return "{\"timestamp\": $date, \"status\": 401, \"error\": \"Não Autorizado\", \"message\": \"Email ou senha inválidos\", \"path\": \"/login\"},"
    }
}