package com.randradee.studies.security.controllers;

import com.randradee.studies.security.domain.user.AuthenticationDTO;
import com.randradee.studies.security.domain.user.RegisterDTO;
import com.randradee.studies.security.domain.user.User;
import com.randradee.studies.security.domain.user.UserRole;
import com.randradee.studies.security.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthorizationService authorizationService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().body("Usuário autenticado com sucesso");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO userToRegister) {
        if (authorizationService.loadUserByUsername(userToRegister.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        authorizationService.createUser(userToRegister);
        return ResponseEntity.ok().body("Usuário criado com sucesso.");
    }

}
