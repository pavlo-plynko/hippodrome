import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void testConstructorIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10, 100);
        });
       assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   ", "\t\t", "\r\n"})
    public void testConstructorBlankStrings(String input) {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(input, 10, 100)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testIsNegativeIntSpeed() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("test", -1, 100);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testIsNegativeIntDistance() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("test", 3, -1);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetName() {
        Horse horse = new Horse("test", 10, 100);
        assertEquals("test", horse.getName());
    }

    @Test
    public void testGetSpeed() {
        Horse horse = new Horse("test", 10, 100);
        assertEquals(10, horse.getSpeed());
    }

    @Test
    public void testGetDistance() {
        Horse horse = new Horse("test", 10, 100);
        assertEquals(100, horse.getDistance());
    }

    @Test
    public void testRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", 10, 100);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.9})
    public void testMove(double mockRandom) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockRandom);
            Horse horse = new Horse("Test", 10, 100);
            double expectedDistance = 100 + 10 * mockRandom;
            horse.move();
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}
