package com.switchfully.codecoach.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.switchfully.codecoach.domain.session.Location;
import com.switchfully.codecoach.domain.session.Status;
import com.switchfully.codecoach.domain.user.CoachInfo;
import com.switchfully.codecoach.domain.user.Topic;
import com.switchfully.codecoach.domain.user.TopicName;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.repository.TopicRepository;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.service.session.dto.CreateSessionDto;
import com.switchfully.codecoach.service.session.dto.SessionDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class SessionControllerEndToEndTest {

    MockMvc mockMvc;
    TopicRepository topicRepository;
    UserRepository userRepository;

    User coachee;
    User coach;
    Topic topic;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SessionControllerEndToEndTest(MockMvc mockMvc, TopicRepository topicRepository, UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUp() {
        topic = new Topic(TopicName.MATHEMATICS);
        topicRepository.save(topic);

        coachee = new User(UUID.randomUUID(), "Jon", "Snow", "jon@snow.com", "team");
        userRepository.save(coachee);

        coach = new User(UUID.randomUUID(), "Jon", "Coach", "jon@coach.com", "team coach");
        CoachInfo coachInfo = new CoachInfo();
        coach.setCoachInfo(coachInfo);
        coach.setIsCoach(true);
        userRepository.save(coach);

        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @WithMockUser(authorities = "REQUEST_SESSION")
    void requestSession() throws Exception {
        LocalDate date = LocalDate.now().plusDays(3);
        LocalTime time = LocalTime.now().plusHours(3);
        CreateSessionDto createSessionDto = new CreateSessionDto(topic.getTopicId(), date, time, Location.ONLINE, "Super remarque", coach.getId(), coachee.getId());

        ResultActions result = mockMvc.perform(post("/sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createSessionDto))).andExpect(status().isCreated());

        String response = result.andReturn().getResponse().getContentAsString();
        SessionDto resultSessionDto = objectMapper.readValue(response, SessionDto.class);

        Assertions.assertThat(resultSessionDto.status()).isEqualTo(Status.REQUESTED);
        Assertions.assertThat(resultSessionDto.date()).isEqualTo(date);
        Assertions.assertThat(resultSessionDto.time()).isEqualTo(time);
        Assertions.assertThat(resultSessionDto.coachFullName()).isEqualTo(coach.getFullName());
        Assertions.assertThat(resultSessionDto.topicName()).isEqualTo(topic.getTopicName());
    }

}
