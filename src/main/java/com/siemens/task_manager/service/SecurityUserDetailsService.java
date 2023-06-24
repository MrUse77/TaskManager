/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siemens.task_manager.service;

import com.siemens.task_manager.models.usuarios;
import com.siemens.task_manager.repository.InterfazSecurity;
import com.siemens.task_manager.repository.InterfazUsuario;
import com.siemens.task_manager.security.confirmation.ConfirmationToken;
import com.siemens.task_manager.security.confirmation.ConfirmationTokenService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author agust
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService{

    private final InterfazUsuario UserRepo;
    @Autowired
    private InterfazSecurity SecurityRepo;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    
      public SecurityUserDetailsService(InterfazUsuario PersoRepo) {
        this.UserRepo = PersoRepo;
    }
 @Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    usuarios usuario = UserRepo.findOneByEmail(email).orElseThrow(()-> new UsernameNotFoundException("El usuario con email"+email+"no existe"));
    return new usuarios(usuario);
}

    public String signUpUser(usuarios user) {
        usuarios userExists = UserRepo.findByEmailOrNumInt(user.getEmail(),user.getNumInt());

        if (userExists!=null) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       
        usuarios newUser = new usuarios(user.getNumInt(),user.getAlias(),user.getName(),user.getEmail(), passwordEncoder.encode(user.getPassword()));
        newUser.setCreatedDate(LocalDateTime.now());

        UserRepo.save(newUser);
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                newUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableUsuarios(String email) {
        return SecurityRepo.enableSecurityUser(email);
    }
   
    
}