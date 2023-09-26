import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class HorseTest {
    static final int DEFAULT_SPEED = 100;
    static final int DEFAULT_DISTANCE = 5;
    static final String DEFAULT_NAME = "MILA";
    private static final String NAME_CANNOT_BE_BLANK_EXC_MSG = "Name cannot be blank.";

    static Stream<Arguments> constructorParamsWithExceptionProvider() {
        return Stream.of(Arguments.of(null, DEFAULT_SPEED, "Name cannot be null."),
                         Arguments.of("", DEFAULT_SPEED, NAME_CANNOT_BE_BLANK_EXC_MSG),
                         Arguments.of(" ", DEFAULT_SPEED, NAME_CANNOT_BE_BLANK_EXC_MSG),
                         Arguments.of("\t", DEFAULT_SPEED, NAME_CANNOT_BE_BLANK_EXC_MSG),
                         Arguments.of("\r", DEFAULT_SPEED, NAME_CANNOT_BE_BLANK_EXC_MSG),
                         Arguments.of(DEFAULT_NAME, -1, "Speed cannot be negative."));
    }

    @ParameterizedTest
    @MethodSource("constructorParamsWithExceptionProvider")
    @DisplayName("Check 2 params constructor with invalid params throws IllegalArgumentException")
    void testShouldReturnIllegalArgumentExceptionForInvalidParam(String name, int speed, String errMsg) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));
        assertEquals(errMsg, exception.getMessage());
    }

    @Test
    @DisplayName("Check constructor with negative distance throws IllegalArgumentException")
    void testShouldReturnIllegalArgumentExceptionForNegativeDistance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(DEFAULT_NAME, DEFAULT_SPEED, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Check get methods returns expected values from constructor")
    void testShouldReturnExpectedHorseParams() {
        Horse horse = new Horse(DEFAULT_NAME, DEFAULT_SPEED, DEFAULT_DISTANCE);
        assertEquals(DEFAULT_NAME, horse.getName());
        assertEquals(DEFAULT_SPEED, horse.getSpeed());
        assertEquals(DEFAULT_DISTANCE, horse.getDistance());
    }

    @Test
    @DisplayName("Check getDistance() returns zero value if not specified in the constructor")
    void testShouldReturnZeroDistanceParamsForConstructorWithoutIt() {
        Horse horse = new Horse(DEFAULT_NAME, DEFAULT_SPEED);
        assertEquals(DEFAULT_NAME, horse.getName());
        assertEquals(DEFAULT_SPEED, horse.getSpeed());
        assertEquals(0, horse.getDistance());
    }

    @Test
    @DisplayName("Check getRandomDouble call")
    void testShouldCallGetRandomDouble() {
        double min = 0.2;
        double max = 0.9;
        double randomValue = 0.3;

        try (MockedStatic<Horse> mockedRandomizer = mockStatic(Horse.class)) {
            Horse horse = new Horse(DEFAULT_NAME, DEFAULT_SPEED, DEFAULT_DISTANCE);
            when(Horse.getRandomDouble(min, max)).thenReturn(randomValue);
            horse.move();
            mockedRandomizer.verify(() -> Horse.getRandomDouble(min, max));
            assertEquals((DEFAULT_DISTANCE + DEFAULT_SPEED * randomValue), horse.getDistance());
        }
    }
}
