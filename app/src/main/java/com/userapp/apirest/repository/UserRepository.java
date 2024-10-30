package com.userapp.apirest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userapp.apirest.persistence.entity.UserEntity;

/* 
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByIdAndIsActive(Long id, Boolean isActive);

    User findByEmail(String email);

    // SELECT * FROM PERSON WHERE EMAIL = 'juan@rodriguez.org'

    // @Query(value = "SELECT EMAIL "
    // + " FROM PERSON pt "
    // + " WHERE pt.EMAIL = :email", nativeQuery = true)
    // String findByEmail(@Param("email") String email);
}
*/

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
}