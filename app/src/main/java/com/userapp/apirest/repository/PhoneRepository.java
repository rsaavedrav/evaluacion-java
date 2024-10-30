package com.userapp.apirest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userapp.apirest.model.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {

}
