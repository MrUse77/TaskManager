package com.siemens.task_manager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.task_manager.models.usuarios;
import com.siemens.task_manager.repository.InterfazUsuario;
import com.siemens.task_manager.service.RegistrationService;
import com.siemens.task_manager.service.registration.RegistrationRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="/**", maxAge=3600)
@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private InterfazUsuario UserRepo;  
    @Autowired
    private RegistrationService regisService;  
    
private static final Logger logger = Logger.getLogger(UsuarioController.class.getName());
    
    @PostMapping("/crear")
    public String registerUser( @RequestBody RegistrationRequest req) {
        
        try {
            return  regisService.register(req);
        }catch(Exception e) {
            logger.log(Level.INFO, "hola1 {0}", e);
            return null;
        }
    }
    @GetMapping("/ver")
    public List<usuarios> getAllUsers(){
        return UserRepo.findAll();
    }

}
