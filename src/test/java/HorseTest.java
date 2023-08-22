import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
    void getName_ShouldReturnStringPlotva() {
        assertEquals("Plotva", horse.getName());
    }

    @Test
    void getSpeed_ShouldReturn200() {
        assertEquals(200.00f, horse.getSpeed());
    }

    @Test
    void getDistance_ShouldReturn1000() {
        assertEquals(1000, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.4, 0.6})
    void move_ShouldReturn1100(double argument) {
        try(MockedStatic<Horse> random = Mockito.mockStatic(Horse.class)) {
            random.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(argument);
            double expected = horse.getDistance() + horse.getSpeed() * argument;
            horse.move();
            random.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(expected, horse.getDistance());
        }
    }
}