import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HorseTest {
    String horseName = "MeatProduct";
    double horseSpeed = 29.9d;
    double horseDistance = 30.3d;
    Horse horse = new Horse(horseName, horseSpeed, horseDistance);
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
    @DisplayName("Horse_Constructor")
    void HorsesTest(String horseName) {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));
        assertEquals("Name cannot be null.", throwable.getMessage());
        throwable = assertThrows(IllegalArgumentException.class, () -> new Horse(horseName, 1,1));
        assertEquals("Name cannot be blank.", throwable.getMessage());
        throwable = assertThrows(IllegalArgumentException.class, () -> new Horse("SomeName", -1,1));
        assertEquals("Speed cannot be negative.", throwable.getMessage());
        throwable = assertThrows(IllegalArgumentException.class, () -> new Horse("SomeName", 0,-1));
        assertEquals("Distance cannot be negative.", throwable.getMessage());
    }

    @Test
    @DisplayName("getName")
    void getNameTest() {
        assertEquals(horseName, horse.getName());
    }

    @Test
    @DisplayName("getSpeed")
    void getSpeedTest() {
        assertEquals(horseSpeed, horse.getSpeed());

    }

    @Test
    @DisplayName("getDistance")
    void getDistanceTest() {
        Horse zeroDistanceHorse = new Horse(horseName, horseSpeed);
        assertEquals(horseDistance, horse.getDistance());
        assertEquals(0d, zeroDistanceHorse.getDistance());
    }

    @ParameterizedTest
    @DisplayName("move")
    @ValueSource(doubles = {0.1 , 0.33, 0.6, 0.9 })
    void moveTest(double value) {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(horseDistance+horseSpeed*value, horse.getDistance());
        }
    }
}