package dev.jozefowicz.springsecurity.apikey.controller;

import dev.jozefowicz.springsecurity.apikey.ApiKeyApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class OrganizationControllerTest {

    @BeforeAll
    public static void setup() {
        SpringApplication.run(ApiKeyApplication.class);
    }

    @Test
    public void shouldNotQueryOrganizationsWithoutAPIKey() {
        get("/organizations")
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldNotCreateOrganizationWithoutAPIKey() {
        given()
                .body(new OrganizationController.CreateOrganizationRequest())
                .when()
                .post("/organizations")
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldCreateOrganizationByAdmin1() {

        // create organization
        final OrganizationController.CreateOrganizationRequest request = new OrganizationController.CreateOrganizationRequest();
        request.setName("test organization");
        given()
                .body(request)
                .contentType("application/json")
                .headers(Map.of("Authorization", "0000"))
                .when()
                .post("/organizations")
                .then()
                .statusCode(200);

        // query organizations
        given()
                .headers(Map.of("Authorization", "0000"))
                .when()
                .get("/organizations")
                .then()
                .statusCode(200)
                .log()
                .body()
                .assertThat()
                .body("size()", is(1))
                .body("organizations[0].createdBy", equalTo("admin1"))
                .body("organizations[0].name", equalTo("test organization"));
    }



}
