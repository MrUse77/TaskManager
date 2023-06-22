package com.siemens.task_manager.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.siemens.task_manager.models.usuarios;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfazUsuario extends MongoRepository<usuarios, ObjectId> {
    public usuarios findByEmailOrNumInt(String email, String numInt);
    Optional<usuarios> findOneByEmail(String email);
}
