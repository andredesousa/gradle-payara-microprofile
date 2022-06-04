package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("AppService")
@ExtendWith(MockitoExtension.class)
class AppServiceTests {

    transient Field nameField;

    @InjectMocks
    transient AppService appService;

    @BeforeEach
    void beforeEach() throws Exception {
        nameField = appService.getClass().getDeclaredField("message");
        nameField.setAccessible(true);
        nameField.set(appService, "Hello!");
    }

    @Test
    @DisplayName("#hello returns 'Hello!' message")
    void helloMethod() {
        assertEquals("Hello!", appService.hello());
    }
}
