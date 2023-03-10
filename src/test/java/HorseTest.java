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

    final private double POSITIVE_SPEED = 1.0;
    final private double NEGATIVE_SPEED = -1.0;
    final private String NORMAL_NAME = "Name";
    final private String EMPTY_NAME = " ";
    final private double POSITIVE_DISTANCE = 1.0;
    final private double NEGATIVE_DISTANCE = -1.0;
    final private String NULL_NAME_ERROR_MESSAGE = "Name cannot be null.";
    final private String EMPTY_CHAR_NAME_ERROR_MESSAGE = "Name cannot be blank.";
    final private String NEGATIVE_SPEED_ERROR_MESSAGE = "Speed cannot be negative.";
    final private String NEGATIVE_DISTANCE_ERROR_MESSAGE = "Distance cannot be negative.";

    @Test
    @DisplayName("Horse creation with null name fails")
    void constructorFailsWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, POSITIVE_SPEED));
    }

    @Test
    @DisplayName("Horse creation with null name fails with message " + NULL_NAME_ERROR_MESSAGE)
    void constructorFailsWithNullNameAndHasSpecificErrorMsg() {
        String errorMsg = "";

        try {
            new Horse(null, POSITIVE_SPEED);
        } catch (IllegalArgumentException exception) {
            errorMsg = exception.getMessage();
        }

        assertEquals(NULL_NAME_ERROR_MESSAGE, errorMsg);

    }

    @ParameterizedTest
    @DisplayName("Horse creation with empty string or only space chars fails")
    @ValueSource(strings = {EMPTY_NAME, "\n", "\t", "\r", " \n\t"})
    void constructorFailsWithEmptyOrOnlySpaceCharsName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, POSITIVE_SPEED));
    }

    @Test
    @DisplayName("Horse creation with empty string or only space chars fails with " + EMPTY_CHAR_NAME_ERROR_MESSAGE)
    void constructorFailsWithEmptyOrOnlySpaceCharsNameAndHasSpecificMsg() {
        String errorMsg = "";

        try {
            new Horse(EMPTY_NAME, POSITIVE_SPEED);
        } catch (IllegalArgumentException exception) {
            errorMsg = exception.getMessage();
        }

        assertEquals(EMPTY_CHAR_NAME_ERROR_MESSAGE, errorMsg);
    }

    @Test
    @DisplayName("Horse creation with negative speed fails")
    void constructorFailsWithNegativeSpeed() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(NORMAL_NAME, NEGATIVE_SPEED));
    }

    @Test
    @DisplayName("Horse creation with negative speed fails with " + NEGATIVE_SPEED_ERROR_MESSAGE)
    void constructorFailsWithNegativeSpeedHasSpecificMsg() {
        String errorMsg = "";

        try {
            new Horse(NORMAL_NAME, NEGATIVE_SPEED);
        } catch (IllegalArgumentException exception) {
            errorMsg = exception.getMessage();
        }

        assertEquals(NEGATIVE_SPEED_ERROR_MESSAGE, errorMsg);
    }

    @Test
    @DisplayName("Horse creation with negative distance fails")
    void constructorFailsWithNegativeDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(NORMAL_NAME, POSITIVE_SPEED, NEGATIVE_DISTANCE));
    }

    @Test
    @DisplayName("Horse creation with negative speed fails with " + NEGATIVE_DISTANCE_ERROR_MESSAGE)
    void constructorFailsWithNegativeDistanceHasSpecificMsg() {
        String errorMsg = "";

        try {
            new Horse(NORMAL_NAME, POSITIVE_SPEED, NEGATIVE_DISTANCE);
        } catch (IllegalArgumentException exception) {
            errorMsg = exception.getMessage();
        }

        assertEquals(NEGATIVE_DISTANCE_ERROR_MESSAGE, errorMsg);
    }

    @Test
    @DisplayName("Method getName returns same string which was set as first param in constructor")
    void getNameWorksAsExpected(){
        assertEquals(NORMAL_NAME, new Horse(NORMAL_NAME, POSITIVE_SPEED).getName());
    }

    @Test
    @DisplayName("Method getSpeed returns same number which was set as second param in constructor")
    void getSpeedWorksAsExpected(){
        assertEquals(POSITIVE_SPEED, new Horse(NORMAL_NAME, POSITIVE_SPEED).getSpeed());
    }

    @Test
    @DisplayName("Method getDistance returns same number which was set as third param in constructor")
    void getDistanceWorksAsExpectedIfDistanceWasSet(){
        assertEquals(POSITIVE_DISTANCE, new Horse(NORMAL_NAME, POSITIVE_SPEED, POSITIVE_DISTANCE).getDistance());
    }

    @Test
    @DisplayName("Method getDistance returns 0 if constructor with only 2 params was used")
    void getDistanceWorksAsExpectedIfNoDistanceWasSet(){
        assertEquals(0, new Horse(NORMAL_NAME, POSITIVE_SPEED).getDistance());
    }

    @Test
    @DisplayName("Static method getRandomDouble invoked with specific args as expected under the hood")
    void getRandomDoubleWorksAsExpected(){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class);) {
            Horse testHorse = new Horse(NORMAL_NAME, POSITIVE_SPEED, POSITIVE_DISTANCE);
            testHorse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    @DisplayName("Method move set value to distance which calculated by rule: distance + speed * getRandomDouble(0.2, 0.9)")
    void methodMoveCorrectlyCalculatesAndSetDistance(){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class);) {
            Horse testHorse = new Horse(NORMAL_NAME, POSITIVE_SPEED, POSITIVE_DISTANCE);
            double expectedRandomDouble = 0.5;
            double expectedDistanceAfterMove = testHorse.getDistance() + testHorse.getSpeed() * expectedRandomDouble;

            horseMockedStatic
                    .when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(expectedRandomDouble);
            testHorse.move();

            assertEquals(expectedDistanceAfterMove, testHorse.getDistance());
        }
    }
}
