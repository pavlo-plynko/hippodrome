import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HorseTest {
    private final Horse horse = new Horse("name", 100, 99);
    private final Horse horseWithoutDistance = new Horse("name", 100);
    private Horse horseMock = mock(Horse.class);

    @Test
    void testHorseConstructorFirstParameterForIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10).getName());
    }

    @Test
    void testHorseConstructorFirstParameterForNullIllegalArgumentExceptionMessage() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10).getName());
        String expectedMessage = "Name cannot be null.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void testHorseConstructorFirstParameterForBlankIllegalArgumentException(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void testHorseConstructorFirstParameterForBlankIllegalArgumentExceptionMessage(String name) {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10));
        String expectedMessage = "Name cannot be blank.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testHorseConstructorSecondParameterForIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("test", -1));
    }

    @Test
    void testHorseConstructorSecondParameterForIllegalArgumentExceptionMessage() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("test", -1));
        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testHorseConstructorThirdParameterForIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("test", 10, -1));
    }

    @Test
    void testHorseConstructorThirdParameterForIllegalArgumentExceptionMessage() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("test", 10, -1));
        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getNameTest() {
        Assertions.assertEquals("name", horse.getName());
    }

    @Test
    void getSpeedTest() {
        Assertions.assertEquals(100, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        Assertions.assertEquals(99, horse.getDistance());
        Assertions.assertNull(horseWithoutDistance.getDistance());
    }

    @Test
    void moveVerificationTest() {
        horseMock.move();
        verify(horseMock).getRandomDouble(0.2, 0.9);
    }

    @Test
    void moveAssignsDistanceValueTest() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.55);
            Horse horseTest = new Horse("horse", 10, 100);
            double actualResult = (horseTest.getDistance() + horseTest.getSpeed() * 0.55);
            horseTest.move();
            Assertions.assertEquals(horseTest.getDistance(), actualResult);
        }
    }
}