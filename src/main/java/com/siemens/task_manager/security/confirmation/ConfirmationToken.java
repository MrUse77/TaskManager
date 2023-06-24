/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siemens.task_manager.security.confirmation;

import com.siemens.task_manager.models.usuarios;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author agusd
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "token")
public class ConfirmationToken {
    
    @Id
    private String id;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    @Field("confirmedAt")
    private LocalDateTime confirmedAt;
    
    @DBRef
    private usuarios usuarios;
    
        public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             usuarios usuarios) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.usuarios = usuarios;
    } 
        
        
}
