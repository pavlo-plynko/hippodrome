import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    @Test
    public void testConstructor_NullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10.0, 5.0);
        });

        String expectedMessage = "Name cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\r\n", "\t\t", " \t \n"})
    public void testConstructor_CheckIfCorrect(String blank) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(blank, 10.0, 5.0);
        });
        String expectedMessage = "Name cannot be blank.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testConstructor_NegativeSpeed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("check", -1.0, 5.0);
        });
        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testConstructor_NegativeDistance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Horse", 10.0, -1.0);
        });
        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testConstructor_getName() {
        String expectedName = "Blaze";
        Horse horse = new Horse(expectedName, 5.0, 10.0);
        String actualName = horse.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testConstructor_getSpeed() {
        double expectedSpeed = 5.2;
        Horse horse = new Horse("Horse", expectedSpeed, 10.0);
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    public void testConstructor_getDistance() {
        double expectedDistance = 10.2;
        Horse horse = new Horse("Horse", 5.0, expectedDistance);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    public void testConstructor_checkZero() {
        double expectedValue = 0;
        Horse horse = new Horse("Horse", 5.0);
        double actualValue = horse.getDistance();
        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"0.2, 0.9, 0.5, 10.0, 5.0, 12.5",
            "0.2, 0.9, 0.2, 15.0, 5.0, 16.0",
            "0.2, 0.9, 0.8, 20.0, 7.0, 25.6"})
    void testConstructor_moveParameters(double min, double max, double mockedResult, double intialDistance, double speed, double finalDistance) {
        try (MockedStatic<Horse> utilities = mockStatic(Horse.class)) {
            utilities.when(() -> Horse.getRandomDouble(min, max)).thenReturn(mockedResult);
            Horse horse = new Horse("Horse", speed, intialDistance);
            horse.move();
            utilities.verify(() -> Horse.getRandomDouble(min, max));
            assertEquals(finalDistance, horse.getDistance());
        }
    }


}
