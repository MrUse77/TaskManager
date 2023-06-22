package com.siemens.task_manager.controller;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.task_manager.models.usuarios;
import com.siemens.task_manager.repository.InterfazUsuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="/**", maxAge=3600)
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private InterfazUsuario UserRepo;
    
    @PostMapping("/crear")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody usuarios user) {
        usuarios existingUser = UserRepo.findByEmailOrNumInt(user.getEmail(),user.getNumInt());
        if (existingUser != null) {
            return ResponseEntity.ok(new ApiResponse("Usuario ya existente"));
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
                usuarios newUser = new usuarios(user.getNumInt(),user.getAlias(),user.getName(),user.getEmail(), passwordEncoder.encode(user.getPassword()));
                newUser.setCreatedDate(LocalDateTime.now());
                UserRepo.save(newUser); 
                return ResponseEntity.ok(new ApiResponse("Usuario Creado"));
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error al crear usuario"));
        }
    }
    @GetMapping("/ver")
    public List<usuarios> getAllUsers(){
        return UserRepo.findAll();
    }
}
