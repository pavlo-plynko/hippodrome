import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    String name = "qwe";
    double speed = 0.5;
    double distance = 0.4;
    Horse horse;

    @BeforeEach
    void setUp() {
        horse = new Horse(name, speed, distance);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("if first param == null")
    void TestConstructor() {
        String name = null;
        double speed = 1.0;
        double distance = 1.0;
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(name, speed, distance);
        });
        assertEquals("Name cannot be null.", e.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n"})
    @DisplayName("If first param == space or tab ")
    void TestConstructor1(String name) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(name, speed, distance);
        });
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    @DisplayName("negative speed")
    void TestConstructor2() {

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(name, -1.0, distance);
        });
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    @DisplayName("negative distance")
    void TestConstructor3() {

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(name, speed, -1.0);
        });
        assertEquals("Distance cannot be negative.", e.getMessage());
    }


    @Test
    @DisplayName("test return first param")
    void getName() {
        String testName = "Test";
        horse = new Horse(testName, speed, distance);
        assertEquals(testName, horse.getName());
    }

    @Test
    @DisplayName("test return speed")
    void getSpeed() {
        double testSpeed = 1.0;
        horse = new Horse(name, testSpeed, distance);
        assertEquals(testSpeed, horse.getSpeed(), 0);
    }

    @Test
    @DisplayName("test return distance")
    void getDistance() {
        double testDistance = 1.0;
        horse = new Horse(name, speed, testDistance);
        assertEquals(testDistance, horse.getDistance(), 0);
        horse = new Horse(name, speed);
        assertEquals(0, horse.getDistance(), 0);

    }

    @Test

    void move() {
        double max = 0.9;
        double min = 0.2;
        double distance = 0.3;
        double speed = 0.4;
        Horse horse = new Horse(name, speed, distance);
        try (MockedStatic<Horse> moc = Mockito.mockStatic(Horse.class)) {

            moc.when(() -> Horse.getRandomDouble(min, max)).thenReturn(15.0);
            horse.move();
            assertEquals(6.3, horse.getDistance());
            moc.verify(() -> Horse.getRandomDouble(min, max));

        }
    }
}