import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void checkNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void checkIllegalArgumentExceptionMessage() {
        String expectedMessage = "Horses cannot be null.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void checkConstructorEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
    }

    @Test
    void checkMessageIfEmptyList() {
        String expectedMessage = "Horses cannot be empty.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(Collections.emptyList()));
        assertEquals(expectedMessage, exception.getMessage());
    }

}