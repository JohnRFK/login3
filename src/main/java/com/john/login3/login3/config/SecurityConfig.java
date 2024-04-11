package com.john.login3.login3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


import lombok.RequiredArgsConstructor;

//clase que contendrá la cadena de filtros y el security chain
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //Vamos a diferenciar los endpoints privados con los públicos
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
            .csrf(csrf-> csrf.disable())
            .authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers("/auth/**")
                .permitAll()//todos los request que matchean la ruta /auth (login y registro) van a ser públicos
                .anyRequest()
                .authenticated())
            .formLogin(withDefaults())
            .build();
    
    }
}
