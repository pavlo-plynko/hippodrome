import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    Horse horse;

    @BeforeEach
    void init() {
        horse = new Horse("Plotva", 200.00f, 1000.00f);
    }

    @Nested
    class ConstructorTests {
        @Test
        void constructor_ShouldReturnIllegalArgumentExceptionWhithCurrectMessage_WhenFirstArgumentIsNull() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 200, 1000));
            assertEquals("Name cannot be null.", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "\n", "\t" })
        void constructor_ShouldReturnIllegalArgumentExceptionWhithCurrectMessage_WhenFirstArgumentIsBlank(String argument) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 200, 1000));
            assertEquals("Name cannot be blank.", exception.getMessage());
        }

        @Test
        void constructor_ShouldReturnIllegalArgumentExceptionWhithCurrectMessage_WhenSecondArgumentIsNegativeNumber() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Plotva", -200, 1000));
            assertEquals("Speed cannot be negative.", exception.getMessage());
        }

        @Test
        void constructor_ShouldReturnIllegalArgumentExceptionWhithCurrectMessage_WhenThirdArgumentIsNegativeNumber() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Plotva", 200, -1000));
            assertEquals("Distance cannot be negative.", exception.getMessage());
        }
    }


    @Test
    void getName() {
    }

    @Test
    void getSpeed() {
    }

    @Test
    void getDistance() {
    }

    @Test
    void move() {
    }
}