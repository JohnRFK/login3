package com.john.login3.login3.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Hereda de una clase abstracta que personaliza filtros donde se ejecuta solamente una vez por cada solicitud http, incluso si hay múltiples filtros

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Método que va a realizar todos los filtros referidos al token
        final String token = getTokenFromRequest(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
        throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }

    private String getTokenFromRequest(HttpServletRequest request) {    
        // TODO del encabezado del request vamos a obtener el token
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) { //acceder a una estructura de control para verificar que existe el texto en el encabezado
            return authHeader.substring(7); //devuelve el encabezado
        }
        return null;
    }

}
