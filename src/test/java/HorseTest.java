import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    public void constructor_NullNameParam_ThrowsIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
        assertEquals(exception.getMessage(), "Name cannot be null.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\n", "\n\n", "\t", "\t\t", "\t \t"})
    public void constructor_EmptyNameParam_ThrowsIllegalArgumentException(String name) {
        // Arrange
        String exceptionMessage = "Name cannot be blank.";

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 2));

        //Assert
        assertEquals(exception.getMessage(), exceptionMessage);
    }

    @Test
    public void constructor_NagativeSpeedParam_ThrowsIllegalArgumentException() {
        String exceptionMessage = "Speed cannot be negative.";
        String name = "testName";
        double speed = -1;
        double distance = 3;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals(exception.getMessage(), exceptionMessage);
    }

    @Test
    public void constructor_NagativeDistanceParam_ThrowsIllegalArgumentException() {
        String exceptionMessage = "Distance cannot be negative.";
        String name = "testName";
        double speed = 1;
        double distance = -3;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals(exception.getMessage(), exceptionMessage);
    }

    @Test
    void getName_Correct() {
        String name = "testName";
        double speed = 1;
        double distance = 3;
        Horse horse = new Horse(name, speed, distance);

        String actualName = horse.getName();
        assertEquals(name, actualName);
    }

    @Test
    void getSpeed_Correct() {
        String name = "testName";
        double speed = 1;
        double distance = 3;
        Horse horse = new Horse(name, speed, distance);

        double actualSpeed = horse.getSpeed();
        assertEquals(speed, actualSpeed);
    }

    @Test
    void getDistance_Correct() {
        String name = "testName";
        double speed = 1;
        double distance = 3;
        Horse horse = new Horse(name, speed, distance);

        double actualDistance = horse.getDistance();
        assertEquals(distance, actualDistance);
    }

    @Test
    void getDistance_Correct_Zerro() {
        String name = "testName";
        double speed = 1;
        double distance = 0;
        Horse horse = new Horse(name, speed);

        double actualDistance = horse.getDistance();
        assertEquals(distance, actualDistance);
    }

    @Test
    void move_getRandomDoubleMethodWithCorrectParams() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("testName", 3, 5);
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.8, 14, 25, 158})
    public void move_CalcCorrect(double fakeRandomValue) {
        double min = 0.2;
        double max = 0.9;
        String name = "testName";
        double speed = 2.5;
        double distance = 250;
        double expectedDistance = distance + speed * fakeRandomValue;
        Horse horse = new Horse(name, speed, distance);
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeRandomValue);
            horse.move();
        }
        assertEquals(expectedDistance, horse.getDistance());
    }

}