package com.userapp.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userapp.apirest.model.Phone;
import com.userapp.apirest.service.PhoneService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/phone")
@RequiredArgsConstructor
/*
 * Controlador Lista los telefonos
 */
public class PhoneController {

    @Autowired
    private final PhoneService phoneService;

    @GetMapping("/findAll")
    public List<Phone> findAllPhones() {
        return phoneService.findAllPhones();
    }
}
