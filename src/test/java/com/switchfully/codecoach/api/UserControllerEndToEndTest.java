package com.switchfully.codecoach.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.repository.CoachInfoRepository;
import com.switchfully.codecoach.repository.CoachInfoTopicRepository;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.service.user.UserService;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
@Transactional
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CoachInfoTopicRepository coachInfoTopicRepository;
    @Autowired
    UserService userService;
    @Autowired
    CoachInfoRepository coachInfoRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    void createUsers() {
        UUID idOne = UUID.randomUUID();
        UUID idTwo = UUID.randomUUID();
        UUID idThree = UUID.randomUUID();

        User user1 = new User(idOne, "One", "One", "one@email.com", "One", "one");
        User user2 = new User(idTwo, "Two", "Two", "two@email.com", "Two", "two");
        User user3 = new User(idThree, "Three", "Three", "three@email.com", "Three", "three");

        userRepository.saveAll(List.of(user1, user2, user3));
        userService.becomeACoach(idOne);
        userService.becomeACoach(idTwo);


//        user1 = userRepository.getById(idOne);
//
//        var coachInfoOne = coachInfoRepository.getById(user1.getCoachInfo().getId());
//
//        UUID topicIDone = UUID.randomUUID();
//        var topicOne = new Topic(topicIDone, TopicName.JAVA);
//        var coachInfoTopicOne = new CoachInfoTopic(coachInfoOne, topicOne, Expertise.JUNIOR);
//
//        coachInfoOne.setCoachInfoTopics(List.of(coachInfoTopicOne));
//
//        userRepository.save(user1);
    }

    @Test
    @WithAnonymousUser
    void endToEndRegisterUser() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("Laurie", "TestingIsCool",
                "laurie@test.com",
                "password",
                "Douane",
                "test");

        ResultActions result = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsBytes(createUserDto))).andExpect(status().isCreated());

        String response = result.andReturn().getResponse().getContentAsString();

        UserDto testUser = objectMapper.readValue(response, UserDto.class);
        User user = userRepository.getById(testUser.id());

        assertThat(testUser.id()).isNotNull();
        assertThat(testUser.firstName()).isEqualTo("Laurie");
        assertThat(testUser.lastName()).isEqualTo("TestingIsCool");
        assertThat(testUser.email()).isEqualTo("laurie@test.com");
        assertThat(testUser.team()).isEqualTo("Douane");
        assertThat(testUser.isCoach()).isFalse();

        assertThat(testUser.id()).isEqualTo(user.getId());
        assertThat(user.getFirstName()).isEqualTo("Laurie");
        assertThat(user.getLastName()).isEqualTo("TestingIsCool");
        assertThat(user.getEmail()).isEqualTo("laurie@test.com");
        assertThat(user.getTeam()).isEqualTo("Douane");
        assertThat(user.isCoach()).isFalse();

    }

    @Test
    @WithMockUser(authorities = "BECOME_A_COACH")
    void becomeAComeCoach() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("Laurie", "TestingIsCool",
                "laurie2@test.com",
                "password",
                "Douane",
                "test");

        ResultActions result = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsBytes(createUserDto))).andExpect(status().isCreated());

        String response = result.andReturn().getResponse().getContentAsString();
        UserDto userDto = objectMapper.readValue(response, UserDto.class);

        System.out.println(userDto.firstName() + userDto.id());
        mockMvc.perform(post("/users/" + userDto.id() + "/become-a-coach"))
                .andExpect(status().isCreated());

        User user = userRepository.getById(userDto.id());

        System.out.println(user);

        assertThat(user.isCoach()).isTrue();
        assertThat(user.getCoachInfo()).isNotNull();
        assertThat(user.getCoachInfo().getId()).isNotNull();
        assertThat(user.getCoachInfo().getAvailability()).isNull();
        assertThat(user.getCoachInfo().getIntroduction()).isNull();

    }

    @Test
    @WithMockUser(authorities = {"ACCESS_PROFILE", "BECOME_A_COACH"})
    void myCoachProfile() throws Exception {
        CreateUserDto createUser = new CreateUserDto("Laurie", "TestingIsCool",
                "laurie3@test.com",
                "password",
                "Douane",
                "test");

        ResultActions result = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsBytes(createUser))).andExpect(status().isCreated());

        String response = result.andReturn().getResponse().getContentAsString();
        UserDto userDto = objectMapper.readValue(response, UserDto.class);

        System.out.println(userDto.firstName() + userDto.id());
        mockMvc.perform(post("/users/" + userDto.id() + "/become-a-coach"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/users/" + userDto.id())).andExpect(status().isOk());

        User user = userRepository.getById(userDto.id());

        assertThat(user.isCoach()).isTrue();
        assertThat(user.getCoachInfo()).isNotNull();
        assertThat(user.getCoachInfo().getIntroduction()).isNull();
        assertThat(user.getCoachInfo().getCoachInfoTopics()).isNotNull();

    }


    @Test
    @WithMockUser(authorities = "REQUEST_SESSION")
    void getAllCoaches() throws Exception {

        var result = mockMvc.perform(
                        get("/users").param("coach", "true")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        List<UserDto> response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), new TypeReference<List<UserDto>>() {
        });
        System.out.println(response.toString());
        assertThat(response.get(0).firstName()).isEqualTo("One");
        assertThat(response.get(1).lastName()).isEqualTo("Two");
//        assertThat(response.get(0).coachInfoDto().coachInfoTopicDtoList().get(0).topicDto().topicName().name()).isEqualTo("JAVA");
    }

    @Test
    @WithAnonymousUser
    void isUserEmailTaken_givenUnusedEmail_thenReturnsFalse() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/users/useremailavailability")
                        .content("one@emaileuh.com")).andExpect(status().isOk());

        String response = result.andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Boolean isTaken = objectMapper.readValue(response, Boolean.class);

        assertThat(isTaken).isFalse();
    }

    @Test
    @WithAnonymousUser
    void isUserEmailTaken_givenUsedEmail_thenReturnsTrue() throws Exception {
        User user1 = new User(UUID.randomUUID(), "One", "One", "maderolucia@gmail.com", "One", "one");
        userRepository.save(user1);

        ResultActions result = mockMvc.perform(
                post("/users/useremailavailability")
                        .content(user1.getEmail())).andExpect(status().isOk());

        String response = result.andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Boolean isTaken = objectMapper.readValue(response, Boolean.class);

        assertThat(isTaken).isTrue();

    }
}
