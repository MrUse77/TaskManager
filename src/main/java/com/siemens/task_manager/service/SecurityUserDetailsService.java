/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siemens.task_manager.service;

import com.siemens.task_manager.models.usuarios;
import com.siemens.task_manager.repository.InterfazUsuario;
import com.siemens.task_manager.security.SecurityUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author agust
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService{

    private static final Logger logger = Logger.getLogger(SecurityUserDetailsService.class.getName());
    private final InterfazUsuario UserRepo;
    
      public SecurityUserDetailsService(InterfazUsuario PersoRepo) {
        this.UserRepo = PersoRepo;
    }
 @Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    logger.log(Level.INFO, "Buscando usuario con nombre de usuario: {0}", email);
    usuarios usuario = UserRepo.findOneByEmail(email).orElseThrow(()-> new UsernameNotFoundException("El usuario con email"+email+"no existe"));
    return new SecurityUser(usuario);
}

    
   
    
}