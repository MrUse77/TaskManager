/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.siemens.task_manager.security.confirmation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agusd
 */

@Repository
public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {

    ConfirmationToken findByToken(String token);


    @Query("{$and: [{'token': ?0}, {'confirmedAt': {$exists: true}}]}")
    @Update("{$set: {'confirmedAt': ?1}}")
    Integer updateConfirmedAt(String token, LocalDateTime confirmedAt);
        

}
