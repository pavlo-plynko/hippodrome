import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void testConstructorIfNameNullException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 20, 10);
        });
    }

    @Test
    public void testConstructorIfNameNullCheckMessage() {
        try {
            new Horse(null, 20, 10);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void testConstructorIfNameBlankSpaceException(String str) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(str, 20, 10);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void testConstructorIfNameBlankSpaceCheckMessage(String str) {
        try {
            new Horse(str, 20, 10);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void testConstructorIfSpeedNegativeNumberException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Strelka", -10, 10);
        });
    }

    @Test
    public void testConstructorIfSpeedNegativeNumberCheckMessage() {
        try {
            new Horse("Strelka", -10, 10);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void testConstructorIfDistanceNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Strelka", 20, -10);
        });
    }

    @Test
    public void testConstructorIfDistanceNegativeNumberCheckMessage() {
        try {
            new Horse("Strelka", 20, -10);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }


    @Test
    void testGetName() {
        Horse horse = new Horse("Strelka", 20, 10);
        assertEquals("Strelka", horse.getName());
    }

    @Test
    void testGetSpeed() {
        Horse horse = new Horse("Strelka", 20, 10);
        assertEquals(20, horse.getSpeed());
    }

    @Test
    void testGetDistanceWithValue() {
        Horse horse = new Horse("Strelka", 20, 10);
        assertEquals(10, horse.getDistance());
    }

    @Test
    void testGetDistanceWithoutValue() {
        Horse horse = new Horse("Strelka", 20);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void testMoveMethodCallStaticMethodGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Strelka", 20, 10);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
    void testMoveMethodReturnSpecificMeaning(double randomDouble) {
        Horse horse = new Horse("Strelka", 20, 10);

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {

            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);

            double distanceExpected = horse.getDistance() + horse.getSpeed() * randomDouble;
            horse.move();

            assertEquals(distanceExpected, horse.getDistance());
        }
    }
}