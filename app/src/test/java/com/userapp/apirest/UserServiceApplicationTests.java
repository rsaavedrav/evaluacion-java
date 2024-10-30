package com.userapp.apirest;

import com.userapp.apirest.persistence.entity.UserEntity;
import com.userapp.apirest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceApplicationTests {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private UserRepository userRepository;

        @BeforeEach
        public void setup() {
                userRepository.deleteAll();
        }

        @Test
        public void testRegisterUserSuccess() throws Exception {
                String userJson = "{ \"name\": \"Juan Rodriguez\", \"email\": \"juan@rodriguez.org\", \"password\": \"hunter2\", "
                                +
                                "\"phones\": [{\"number\": \"1234567\", \"cityCode\": \"1\", \"countryCode\": \"57\"}] }";

                mockMvc.perform(post("/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.email").value("juan@rodriguez.org"))
                                .andExpect(jsonPath("$.token").isNotEmpty());
        }

        @Test
        public void testRegisterUserDuplicateEmail() throws Exception {
                String userJson = "{ \"name\": \"Juan Rodriguez\", \"email\": \"juan@rodriguez.org\", \"password\": \"hunter2\", "
                                +
                                "\"phones\": [{\"number\": \"1234567\", \"cityCode\": \"1\", \"countryCode\": \"57\"}] }";

                // Crear el usuario
                mockMvc.perform(post("/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                                .andExpect(status().isOk());

                // Crear el mismo usuario
                mockMvc.perform(post("/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.mensaje").value("El correo ya registrado"));
        }

        @Test
        public void testGetAllUsersSuccess() throws Exception {
                UserEntity user = new UserEntity();
                user.setName("Juan Rodriguez");
                user.setEmail("juan@rodriguez.org");
                user.setPassword("hunter2");
                user.setToken(UUID.randomUUID().toString());
                userRepository.save(user);

                mockMvc.perform(get("/users/findAll")
                                .header("Authorization", "Bearer " + user.getToken()))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].email").value("juan@rodriguez.org"));
        }

        @Test
        public void testUnauthorizedAccess() throws Exception {
                mockMvc.perform(get("/users/findAll"))
                                .andExpect(status().isUnauthorized());
        }
}
