import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    Horse black;

    @BeforeEach
    void init() {
        black = new Horse("Black", 2, 6);
    }

    @Test
    void constructorWithNullNameThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 2, 1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n"})
    void constructorWithEmptyNameThrowsIllegalArgumentException(String argument) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(argument, 1, 2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorWithNegativeSpeed() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Black", -5, 2));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorWithNegativeDistance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Black", 2, -100));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        String actual = black.getName();
        assertEquals("Black", actual);
    }


    @Test
    void getSpeedTest() {
        double actual = black.getSpeed();
        assertEquals(2, actual);
    }

    @Test
    void getDistanceTestThreeParameters() {
        double actual = black.getDistance();
        assertEquals(6, actual);
    }

    @Test
    void getDistanceTestTwoParameters() {
        Horse horse = new Horse("Black", 4);
        double actual = horse.getDistance();
        assertEquals(0, actual);
    }

    @Test
    void moveCallsGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            black.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.4, 0.5, 0.6, 2, 3, 102})
    void distanceUpdateCheck(Double argument) {
        double expected = black.getDistance() + (black.getSpeed() * argument);

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(argument);
            black.move();
        }
        assertEquals(expected, black.getDistance());
    }
}
