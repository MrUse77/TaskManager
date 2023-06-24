/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siemens.task_manager.security.confirmation;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author agusd
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {
private static final Logger logger = Logger.getLogger(ConfirmationTokenService.class.getName());
    private final ConfirmationTokenRepository confirmationTokenRepository;
    

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token, LocalDateTime dia) {
        logger.log(Level.INFO, "Buscando usuario con nombre de usuario: {0}");
        Integer updatedRows = confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
        

        return updatedRows;
    }
}