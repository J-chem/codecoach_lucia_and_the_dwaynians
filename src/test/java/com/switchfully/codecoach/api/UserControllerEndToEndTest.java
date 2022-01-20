package com.switchfully.codecoach.api;

import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.service.security.KeycloakService;
import com.switchfully.codecoach.service.user.dto.CreateUserDto;
import com.switchfully.codecoach.service.user.dto.UserDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerEndToEndTest {

    @LocalServerPort
    private int port;

    @Autowired
    private KeycloakService keycloakService;
    String url;
    String token;


    @BeforeAll
    void setUp() {
        url = "https://keycloak.switchfully.com/auth/realms/java-oct-2021/protocol/openid-connect/token";
        token = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("grant_type", "password")
                .formParam("username", "najima@dwaynians.com")
                .formParam("password", "password")
                .formParam("client_id", "CodeCoach-Dwaynians")
                .when()
                .post(url)
                .then()
                .extract()
                .path("access_token")
                .toString();
    }

    @Test
    void endToEndRegisterUser() {
        CreateUserDto createUserDto = new CreateUserDto("Laurie", "TestingIsCool",
                "laurie@test.com",
                "password",
                "Douane");

        RestAssured.defaultParser = Parser.JSON;
        UserDto userDto = RestAssured
                .given()
                .accept(JSON)
                .contentType(JSON)
                .body(createUserDto)
                .when()
                .port(port)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UserDto.class);

        keycloakService.deleteUser(userDto.keycloakId());

        Assertions.assertThat(userDto.id()).isNotNull();
        Assertions.assertThat(userDto.firstName()).isEqualTo("Laurie");

    }

 }