import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    void checkExceptionIfFirstParameterIsNullInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 50, 100));
    }

    @Test
    void checkExceptionMessageIfFirstParameterIsNullInConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 50, 100));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\t\t", "\n\n"})
    void checkExceptionIfFirstParameterIsVoidInConstructor(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 50, 100));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "       ", "\t", "\n", "\t\t", "\n\n"})
    void checkExceptionMessageIfFirstParameterIsVoidInConstructor(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 50, 100));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void checkExceptionIfSecondParameterIsNegativeInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Barsik", -50, 100));
    }

    @Test
    void checkExceptionMessageIfSecondParameterIsNegativeInConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Barsik", -50, 100));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void checkExceptionIfThirdParameterIsNegativeInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Barsik", 50, -100));
    }

    @Test
    void checkExceptionMessageIfThirdParameterIsNegativeInConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Barsik", 50, -100));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Barsik", "Poni", "Veterok"})
    void checkGetNameReturnName(String name) {
        Horse horse = new Horse(name, 50, 100);
        assertEquals(name, horse.getName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {10, 30.0, 100.78})
    void checkGetSpeedReturnSpeed(Double speed) {
        Horse horse = new Horse("Barsik", speed, 100);
        assertEquals(speed, horse.getSpeed());
    }

    @ParameterizedTest
    @ValueSource(doubles = {10, 30.0, 100.78})
    void checkGetDistanceReturnDistance(Double distance) {
        Horse horse = new Horse("Barsik", 50, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void checkGetDistanceReturn0() {
        Horse horse = new Horse("Barsik", 50);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void checkMoveRunGetRandomDoubleWithParameters02and09() {
        try(MockedStatic<Horse> horseStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Barsik", 50, 100);
            horse.move();
            horseStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
