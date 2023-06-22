/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siemens.task_manager.config;

import com.siemens.task_manager.security.JWTAuthenticationFilter;
import com.siemens.task_manager.security.JWTAuthorizationFilter;
import com.siemens.task_manager.service.SecurityUserDetailsService;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author agust
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig{
    
    @Autowired
    private final SecurityUserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
private static final Logger logger = Logger.getLogger(SecurityConfig.class.getName());

    @Bean
    AuthenticationManager authManager(HttpSecurity http)throws Exception{
        logger.info("Mensaje a imprimir");
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
   
    }
    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
        JWTAuthenticationFilter jwtAuthenticationFilter  = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

                return http.
                        csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET)
                .authenticated()
                .anyRequest()
                .permitAll()    
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors()
                .and()
                .build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder.getInstance();//no se usa en produccion
        return new BCryptPasswordEncoder();
        
    }
    
    

}