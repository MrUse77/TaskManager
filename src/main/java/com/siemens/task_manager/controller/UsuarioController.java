package com.siemens.task_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.task_manager.models.usuarios;
import com.siemens.task_manager.repository.InterfazUsuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private InterfazUsuario UserRepo;
    @PostMapping
    public ResponseEntity<ApiResponse> registerUser(@RequestBody usuarios newUser) {
        usuarios encodedUser = new usuarios(newUser.getName(),
                                            newUser.getEmail());
        UserRepo.save(encodedUser);
        return ResponseEntity.ok(new ApiResponse("Usuario Creado"));
    }
}
