package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JAXRSApplication")
class JAXRSApplicationTests {

    transient JAXRSApplication app;

    @BeforeEach
    void beforeEach() throws Exception {
        app = new JAXRSApplication();
    }

    @Test
    void contextLoads() {
        assertEquals(app.getClass(), JAXRSApplication.class);
    }
}
