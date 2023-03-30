package com.luis.helpdesk.services

import com.luis.helpdesk.domain.Pessoa
import com.luis.helpdesk.repositories.PessoaRepository
import com.luis.helpdesk.security.UserSS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    private lateinit var repository: PessoaRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String?): UserDetails? {
        val user: Optional<Pessoa> = repository.findPessoaByEmail(email)
        if (user.isPresent) {
            return UserSS(user.get().id, user.get().email, user.get().senha, user.get().perfis)
        }
        throw UsernameNotFoundException(email)
    }
}