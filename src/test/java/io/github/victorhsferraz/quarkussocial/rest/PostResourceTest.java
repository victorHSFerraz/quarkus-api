package io.github.victorhsferraz.quarkussocial.rest;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.victorhsferraz.quarkussocial.domain.model.User;
import io.github.victorhsferraz.quarkussocial.domain.repository.UserRepository;
import io.github.victorhsferraz.quarkussocial.rest.dto.CreatePostRequest;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestHTTPEndpoint(PostResource.class)
public class PostResourceTest {

    @Inject
    UserRepository userRepository;
    Long userId;

    @BeforeEach
    @Transactional
    public void setUp() {
        var user = new User();
        user.setName("Fulano de Tal");
        user.setAge(30);

        userRepository.persist(user);
        userId = user.getId();
    }

    @Test
    @DisplayName("Should save a post")
    void testSavePost() {
        var postRequest = new CreatePostRequest();
        postRequest.setText("This is a test post");

        given()
                .contentType(ContentType.JSON)
                .body(postRequest)
                .pathParam("userId", userId)
                .when()
                .post()
                .then()
                .statusCode(201);

    }

    @Test
    @DisplayName("Should return 404 for an invalid user")
    void testPostForAnInvalidUser() {
        var postRequest = new CreatePostRequest();
        postRequest.setText("This is a test post");

        given()
                .contentType(ContentType.JSON)
                .body(postRequest)
                .pathParam("userId", 999)
                .when()
                .post()
                .then()
                .statusCode(404);

    }
}
