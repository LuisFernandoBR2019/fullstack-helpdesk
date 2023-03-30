package com.luis.helpdesk.config

import com.luis.helpdesk.security.JWTAuthenticationFilter
import com.luis.helpdesk.security.JWTUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*
import kotlin.collections.ArrayList


@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    private val PUBLIC_MATCHERS = arrayOf("/h2-console/**")

    @Autowired
    private lateinit var env: Environment

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        if (env.activeProfiles.contains("test")) {
            http.headers().frameOptions().disable()
        }
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers(*PUBLIC_MATCHERS)
                .permitAll().anyRequest().authenticated()
        http.addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

//        http.cors().and().csrf().disable()
//        http.addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil))
//        http.addFilter(JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService))
//        http.authorizeRequests().antMatchers(*PUBLIC_MATCHERS).permitAll().anyRequest().authenticated()
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration().applyPermitDefaultValues()
        configuration.allowedMethods = listOf("POST", "GET", "PUT", "DELETE", "OPTIONS")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}