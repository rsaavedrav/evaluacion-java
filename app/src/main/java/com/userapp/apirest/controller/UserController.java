package com.userapp.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userapp.apirest.model.UserResponse;
import com.userapp.apirest.persistence.entity.UserEntity;
import com.userapp.apirest.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/person")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
/*
 * Controlador de usuarios, provee los endpints para crear un usuario y para
 * listar todos los usuario creados
 * GET Listas usuarios api/person/findAll
 * POST Crear Usuario api/person//create
 * 
 */
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/findAll")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('READ')")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserEntity person) {
        UserResponse per = userService.createUser(person);
        if (per.getCode() != 200) {
            return new ResponseEntity<>(per, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(per, HttpStatus.OK);
    }

}
