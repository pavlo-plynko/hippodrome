import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;

class HorseTest {
    private final String validName = "name";
    private final double validSpeed = 1.0;
    private final double validDistance = 1.0;

    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, validSpeed, validDistance)
        );

        assertEquals("Name cannot be null.", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\s", "\t", "\n", "\r"})
    void constructorShouldThrowIllegalArgumentExceptionWhenNameIsWhitespace(String whitespaceStr) {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(whitespaceStr, validSpeed, validDistance)
        );

        assertEquals("Name cannot be blank.", thrown.getMessage());
    }

    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenSpeedIsNegative() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(validName, -1, validDistance)
        );

        assertEquals("Speed cannot be negative.", thrown.getMessage());
    }

    @Test
    void constructorShouldThrowIllegalArgumentExceptionWhenDistanceIsNegative() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(validName, validSpeed, -1)
        );

        assertEquals("Distance cannot be negative.", thrown.getMessage());
    }

    @Test
    void getNameShouldReturnConstructorNameArgument() {
        String expectedName = "expected name";
        Horse horse = new Horse(expectedName, validSpeed);
        assertEquals(expectedName, horse.getName());
    }

    @Test
    void getSpeedShouldReturnConstructorSpeedArgument() {
        double expectedSpeed = 10.0;
        Horse horse = new Horse(validName, expectedSpeed);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void getDistanceShouldReturnConstructorDistanceArgument() {
        double expectedDistance = 10.0;
        Horse horse = new Horse(validName, validSpeed, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void getDistanceShouldReturnZeroWhenConstructorArgumentsAreNameAndSpeed() {
        double expectedDistance = 0;
        Horse horse = new Horse(validName, validSpeed);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void moveShouldInvokeMethodGetRandomDoubleWithDefinedArguments() {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse(validName, validSpeed);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.9})
    void moveShouldChangeDistanceAccordingToFormula(double randomDouble) {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic
                    .when(() -> Horse.getRandomDouble(anyDouble(), anyDouble()))
                    .thenReturn(randomDouble);
            Horse horse = new Horse(validName, validSpeed);

            double expectedDistance = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}