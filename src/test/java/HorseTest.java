import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class HorseTest {

    @Test
    void constructorThroughIllegalArgumentExceptionNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 2, 0));

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r\n"})
    void constructorThroughEmptyString(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 0, 0));

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorThroughNegativeSpeed() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Name", -1, 0));

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorThroughNegativeDistance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Name", 2, -2));

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTrue() {
        Horse horse = new Horse("Name", 2, 2);
        assertEquals("Name", horse.getName());
    }

    @Test
    void getSpeedTrue() {
        Horse horse = new Horse("Name", 2, 3);
        assertEquals(2, horse.getSpeed());
    }

    @Test
    void getDistanceTrue() {
        Horse horse = new Horse("Name", 2, 3);
        assertEquals(3, horse.getDistance());
    }

    @Test
    void gedDistanceDefault() {
        Horse horse = new Horse("Name", 2);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveShouldCallGetRandomDoubleWithExpectedArguments() {
        Horse horse = new Horse("Name", 10, 5);

        try (MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class, Mockito.CALLS_REAL_METHODS)) {
            horseMock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            horseMock.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    void moveShouldCallGetRandomDoubleWithExpectedArguments(double randomValue) {
        double speed = 12;
        double initialDistance = 3.5;

        Horse horse = new Horse("Name", speed, initialDistance);

        try (MockedStatic<Horse> mockHorse = Mockito.mockStatic(Horse.class, Mockito.CALLS_REAL_METHODS)) {
            mockHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            horse.move();
        }

        assertEquals(initialDistance + speed * randomValue, horse.getDistance());
    }
}