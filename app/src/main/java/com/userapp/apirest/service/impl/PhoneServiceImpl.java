package com.userapp.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userapp.apirest.model.Phone;
import com.userapp.apirest.repository.PhoneRepository;
import com.userapp.apirest.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneRepository repository;

    @Override
    public List<Phone> findAllPhones() {
        return (List<Phone>) repository.findAll();

    }

}
