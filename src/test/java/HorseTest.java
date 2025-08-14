import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;


class HorseTest {
    @Test
    public void constructor_ShouldThrowException_WhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1, 1);
        });
    }

    @Test
    public void constructor_ShouldThrowExceptionWithMessage_WhenNameIsNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(null, 1, 1);
                });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "    "})
    public void constructor_ShouldThrowException_WhenNameIsEmpty(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 1, 1);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "    "})
    public void constructor_ShouldThrowExceptionWithMessage_WhenNameIsEmpty(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 1, 1);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructor_ShouldThrowException_WhenSpeedIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Pedro", -5, 1);
        });
    }

    @Test
    public void constructor_ShouldThrowExceptionWithMessage_WhenSpeedIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Pedro", -5, 1);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructor_ShouldThrowException_WhenDistanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Pedro", 5, -3);
        });
    }

    @Test
    public void constructor_ShouldThrowExceptionWithMessage_WhenDistanceIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Pedro", 5, -3);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void shouldGetName() {
        String name = "Pedro";
        Horse horse = new Horse(name, 1, 1);
        assertEquals(name, horse.getName());
    }

    @Test
    public void shouldGetSpeed() {
        double speed = 15.1;
        Horse horse = new Horse("Pedro", speed, 1);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void shouldGetPassedDistance() {
        double distance = 10.3;
        Horse horse = new Horse("Pedro", 1, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    public void shouldGetDefaultDistance() {
        Horse horse = new Horse("Pedro", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void verify_GetRandomDouble_WhenMove() {
        Horse horse = new Horse("Pedro", 1);
        try (MockedStatic<Horse> horseStatic = Mockito.mockStatic(Horse.class)) {
            horseStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            horseStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
        }
    }

    @Test
    public void verify_DistanceCalculation_WhenMove() {
        Horse horse = new Horse("Pedro", 3);
        double distance;
        try (MockedStatic<Horse> horseStatic = Mockito.mockStatic(Horse.class)) {
            horseStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            distance = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
        }
        double expectedDistance = 0 + 3 * 0.5;
        assertEquals(expectedDistance, distance);
    }
}