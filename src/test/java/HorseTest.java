import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {
    @Test
    @DisplayName("Конструктор має кидати IllegalArgumentException, якщо name == null")
    void constructorShouldThrowExceptionWhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 2.0, 3.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    @DisplayName("Конструктор має кидати IllegalArgumentException, якщо name порожній або лише пробіли")
    void constructorShouldThrowExceptionWhenNameIsBlank(String invalidName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(invalidName, 2.0, 3.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("Конструктор має кидати IllegalArgumentException, якщо speed < 0")
    void constructorShouldThrowExceptionWhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", -1.0, 3.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Конструктор має кидати IllegalArgumentException, якщо distance < 0")
    void constructorShouldThrowExceptionWhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", 2.0, -5.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameShouldReturnNameFromConstructor() {
        Horse horse = new Horse("Lobster", 2.5, 5.0);
        assertEquals("Lobster", horse.getName());
    }

    @Test
    void getSpeedShouldReturnSpeedFromConstructor() {
        Horse horse = new Horse("Lobster", 2.5, 5.0);
        assertEquals(2.5, horse.getSpeed());
    }

    @Test
    void getDistanceShouldReturnDistanceFromConstructor() {
        Horse horse = new Horse("Lobster", 2.5, 5.0);
        assertEquals(5.0, horse.getDistance());
    }

    @Test
    void getDistanceShouldReturnZeroIfNotProvided() {
        Horse horse = new Horse("Lobster", 2.5);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    @DisplayName("move() має викликати getRandomDouble з параметрами 0.2 і 0.9")
    void moveShouldCallGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Lobster", 2.5, 5.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    @DisplayName("move() має правильно оновлювати distance")
    void moveShouldUpdateDistance(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Lobster", 2.0, 5.0);

            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            double expected = 5.0 + 2.0 * random;
            assertEquals(expected, horse.getDistance());
        }
    }
}
