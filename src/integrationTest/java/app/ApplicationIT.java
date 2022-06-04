package app;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DisplayName("Integration Tests")
class ApplicationIT {

    @Container
    static GenericContainer<?> app = new GenericContainer<>("microprofile-api")
        .withExposedPorts(8080)
        .waitingFor(Wait.forLogMessage(".*Payara Micro URLs.*\\n", 1));

    @Test
    @DisplayName("/microprofile-api (GET)")
    public void testHelloEndpoint() {
        given()
            .port(app.getFirstMappedPort())
            .when()
            .get("/microprofile-api")
            .then()
            .statusCode(200)
            .body(is("Enjoy Jakarta EE with MicroProfile 4+!"));
    }
}
