package com.example.moviee.utils


import com.example.moviee.service.UserService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class MovieReactiveUserDetailsService(val userService : UserService) :
    ReactiveUserDetailsService {
    override fun findByUsername(username: String?): Mono<UserDetails> {
        if (username != null) {
            return userService.getByUsername(username).toMono().map { user ->
                User.withUsername(user.username).password(user.password).roles(user.role).build();
            };
        }
        return Mono.empty();
    }
}