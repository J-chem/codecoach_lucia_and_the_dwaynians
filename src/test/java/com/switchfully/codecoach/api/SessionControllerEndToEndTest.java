package com.switchfully.codecoach.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.switchfully.codecoach.domain.coachinfo.CoachInfo;
import com.switchfully.codecoach.domain.session.Location;
import com.switchfully.codecoach.domain.session.Session;
import com.switchfully.codecoach.domain.session.Status;
import com.switchfully.codecoach.domain.topic.Topic;
import com.switchfully.codecoach.domain.topic.TopicName;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.repository.SessionRepository;
import com.switchfully.codecoach.repository.TopicRepository;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.service.session.dto.CreateSessionDto;
import com.switchfully.codecoach.service.session.dto.SessionDto;
import com.switchfully.codecoach.service.user.dto.UserDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    SessionRepository sessionRepository;

    User coachee;
    User coach;
    Topic topic;
    List<Session> sessionsToGet;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SessionControllerEndToEndTest(MockMvc mockMvc, TopicRepository topicRepository, UserRepository userRepository, SessionRepository sessionRepository) {
        this.mockMvc = mockMvc;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @BeforeEach
    void setUp() {
        topic = new Topic(UUID.randomUUID(),TopicName.JAVA);
        topicRepository.save(topic);

        coachee = new User(UUID.randomUUID(), "Jon", "Snow", "jon@snow.com", "team", "test");
        userRepository.save(coachee);

        coach = new User(UUID.randomUUID(), "Jon", "Coach", "jon@coach.com", "team coach", "test");
        CoachInfo coachInfo = new CoachInfo();
        coach.setCoachInfo(coachInfo);
        coach.setIsCoach(true);
        userRepository.save(coach);

        objectMapper.registerModule(new JavaTimeModule());
        setUpGetCoachSessions();
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

    @Test
    @WithMockUser(authorities = "ACCESS_COACH_SESSIONS")
    void getCoachSessions() throws Exception {
        String url = "/sessions?coach=" + coach.getId();

        ResultActions result = mockMvc.perform(get(url)).andExpect(status().isOk());
        String response = result.andReturn().getResponse().getContentAsString();
        System.out.println(response);

        List<SessionDto> resultSessions = objectMapper.readValue(response, new TypeReference<>() {});

        Assertions.assertThat(resultSessions).hasSize(3);
    }

    private void setUpGetCoachSessions() {
        LocalDate date = LocalDate.now().plusDays(4);
        LocalTime time = LocalTime.now().plusHours(4);

        User coach = userRepository.getById(this.coach.getId());
        User coachee = userRepository.getById(this.coachee.getId());

        sessionsToGet = new ArrayList<>();
        Session session1 = new Session(topic, date, time, Location.ONLINE, "This is a remark", coach, coachee);
        sessionsToGet.add(session1);
        Session session2 = new Session(topic, date, time, Location.ONLINE, "This is a remark", coach, coachee);
        sessionsToGet.add(session2);
        Session session3 = new Session(topic, date, time, Location.ONLINE, "This is a remark", coach, coachee);
        sessionsToGet.add(session3);

        sessionRepository.saveAll(sessionsToGet);
    }

}
