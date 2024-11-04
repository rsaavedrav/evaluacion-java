package com.userapp.apirest.service.impl;

import static java.lang.Boolean.TRUE;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userapp.apirest.model.UserResponse;
import com.userapp.apirest.persistence.entity.UserEntity;
import com.userapp.apirest.repository.UserRepository;
import com.userapp.apirest.repository.PhoneRepository;
import com.userapp.apirest.service.UserService;

import util.Utilities;

@Service
@JsonInclude(Include.NON_NULL)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PhoneRepository phoneRepository;

    public List<UserEntity> findAll() {
        List<UserEntity> actualList = repository.findAll();
        return actualList;
    }

    @Override
    public UserResponse createUser(UserEntity person) {
        UserResponse responseP = new UserResponse();
        boolean validEnmail = Utilities.validateEmail(person.getEmail());

        if (validEnmail) {
            UserEntity emailP = repository.findByEmail(person.getEmail());
            if (emailP != null && emailP.getEmail().equalsIgnoreCase(person.getEmail())) {
                responseP.setCode(500);
                responseP.setMessageString("El correo ya se encuentra registrado");

                ObjectMapper mapper = new ObjectMapper();
                mapper.setSerializationInclusion(Include.NON_NULL);
                try {
                    String dtoAsString = mapper.writeValueAsString(responseP);
                    System.out.println(dtoAsString);
                } catch (JsonProcessingException e) {
                    responseP.setCode(500);
                    responseP.setMessageString(e.getMessage());
                }

                return responseP;
            }
        } else {
            responseP.setCode(500);
            responseP.setMessageString("El email ingresado no es valido");

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            try {
                String dtoAsString = mapper.writeValueAsString(responseP);
                System.out.println(dtoAsString);
            } catch (JsonProcessingException e) {
                responseP.setCode(500);
                responseP.setMessageString(e.getMessage());
            }

            return responseP;
        }

        boolean validPass = Utilities.validaPasswordMatches(person.getPassword());
        if (validPass) {
            UUID uuid = Utilities.UUID();
            person.setCreated(LocalDateTime.now());
            person.setModified(LocalDateTime.now());
            person.setActive(TRUE);
            person.setLastLogin(LocalDateTime.now());
            person.setId(uuid);
            person.setToken(UUID.randomUUID().toString());
            // Encriptar pass
            person.setPassword(person.getPassword());

            UserEntity person2 = repository.save(person);

            responseP = responseCreateUser(person2);
            responseP.setCode(200);
        } else {
            responseP.setCode(500);
            responseP.setMessageString("Formato de password invalido");

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            try {
                String dtoAsString = mapper.writeValueAsString(responseP);
                System.out.println(dtoAsString);
            } catch (JsonProcessingException e) {
                responseP.setCode(500);
                responseP.setMessageString(e.getMessage());
            }

            // return responseP;
        }
        return responseP;
    }

    private UserResponse responseCreateUser(UserEntity user) {
        UserResponse resp = new UserResponse();

        resp.setId(user.getId());
        resp.setCreated(user.getCreated());
        resp.setModified(user.getModified());
        resp.setLast_login(user.getLastLogin());
        resp.setToken(user.getToken());
        resp.setIsactive(user.isActive());
        return resp;
    }

}
