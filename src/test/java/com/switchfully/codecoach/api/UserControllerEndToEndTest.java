package com.switchfully.codecoach.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
@ActiveProfiles("test")
@Transactional
class UserControllerEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void endToEndRegisterUser() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("Laurie", "TestingIsCool",
                "laurie@test.com",
                "password",
                "Douane");

        ResultActions result = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsBytes(createUserDto))).andExpect(status().isCreated());

        String response = result.andReturn().getResponse().getContentAsString();

        UserDto testUser = objectMapper.readValue(response, UserDto.class);
        User user = userRepository.getById(testUser.id());

        Assertions.assertThat(testUser.id()).isNotNull();
        Assertions.assertThat(testUser.firstName()).isEqualTo("Laurie");
        Assertions.assertThat(testUser.lastName()).isEqualTo("TestingIsCool");
        Assertions.assertThat(testUser.email()).isEqualTo("laurie@test.com");
        Assertions.assertThat(testUser.team()).isEqualTo("Douane");
        Assertions.assertThat(testUser.isCoach()).isFalse();

        Assertions.assertThat(testUser.id()).isEqualTo(user.getId());
        Assertions.assertThat(user.getFirstName()).isEqualTo("Laurie");
        Assertions.assertThat(user.getLastName()).isEqualTo("TestingIsCool");
        Assertions.assertThat(user.getEmail()).isEqualTo("laurie@test.com");
        Assertions.assertThat(user.getTeam()).isEqualTo("Douane");
        Assertions.assertThat(user.isCoach()).isFalse();

    }

}