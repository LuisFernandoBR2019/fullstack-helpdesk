package com.luis.helpdesk.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors


class UserSS(var id: Int?, var email: String?, var senha: String?, var authorities: MutableSet<Int>?) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities!!.stream().map { x: Int -> SimpleGrantedAuthority(x.toString()) }.collect(Collectors.toSet())

    override fun getPassword() = senha

    override fun getUsername() = email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}