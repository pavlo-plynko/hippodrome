import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class HorseTest {

    @Test
    void constructorNullNameTests() {
        Throwable throwable =
                Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null,0,0));
        String expectedMessage = "Name cannot be null.";
        String actualMessage = throwable.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "\n", "\t", "\t \n" })
    void constructorBlankNameTests(String blankName) {
        Throwable throwable =
                Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(blankName,0,0));
        String expectedMessage = "Name cannot be blank.";
        String actualMessage = throwable.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -Double.MAX_VALUE, -Double.MIN_VALUE })
    void constructorNegativeSpeedTests(double negativeValue) {
        Throwable throwable =
                Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Unicorn", negativeValue,0));
        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = throwable.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -Double.MAX_VALUE, -Double.MIN_VALUE })
    void constructorNegativeDistanceTests(double negativeValue) {
        Throwable throwable =
                Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Unicorn", 0, negativeValue));
        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = throwable.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void horseGettersTests() {
        Horse testHorse = new Horse("Unicorn", 0);
        double expectedDistance = 0;
        double actualDistance = testHorse.getDistance();
        Assertions.assertEquals(expectedDistance, actualDistance);

        testHorse = new Horse("Unicorn", 15.0, 100.0);

        expectedDistance = 100.0;
        actualDistance = testHorse.getDistance();
        Assertions.assertEquals(expectedDistance, actualDistance);

        String expectedName = "Unicorn";
        String actualName = testHorse.getName();
        Assertions.assertEquals(expectedName, actualName);

        double expectedSpeed = 15.0;
        double actualSpeed = testHorse.getSpeed();
        Assertions.assertEquals(expectedSpeed, actualSpeed);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0.3, 0.6",
            "0.8, 1.6"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void horseMoveTests(double random, double expectedDistance) {
        Horse horseForTest = new Horse("test",2.0);

        try (MockedStatic<Horse> testHorse = Mockito.mockStatic(Horse.class)) {
            testHorse.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);
            horseForTest.move();
            testHorse.verify(()-> Horse.getRandomDouble(0.2,0.9));

            double actualDistance = horseForTest.getDistance();
            Assertions.assertEquals(expectedDistance, actualDistance);
        }
    }
}
