package com.siemens.task_manager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.siemens.task_manager.models.usuarios;

public interface InterfazUsuario extends MongoRepository<usuarios, Long> {

}
