/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.siemens.task_manager.repository;

import com.siemens.task_manager.models.usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author agusd
 */
public interface InterfazSecurity  extends MongoRepository<usuarios, String>{
    
    @Transactional
    @Query("{'email': ?0}")
    @Update("{$set: {'enabled': true}}")
    int enableSecurityUser(String email);
}
