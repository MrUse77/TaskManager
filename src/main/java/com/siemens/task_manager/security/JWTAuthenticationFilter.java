/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siemens.task_manager.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.task_manager.models.usuarios;
import com.siemens.task_manager.security.confirmation.ConfirmationToken;
import com.siemens.task_manager.service.RegistrationService;
import com.siemens.task_manager.service.registration.RegistrationRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author agusd
 */
@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    
        @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();
        try{
            authCredentials = new ObjectMapper().readValue(request.getReader(),AuthCredentials.class);
        } catch (IOException e) {
        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword()
        );
        return getAuthenticationManager().authenticate(usernamePAT);
        
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        usuarios userDetails = (usuarios) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getUsername(),userDetails.getId());
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();
        super.successfulAuthentication(req, response, chain, authResult);
    }
    
}