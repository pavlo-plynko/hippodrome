import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

 class HorseTest {

    @Test
    void checkIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException();
        });
    }

    @Test
    void checkIllegalArgumentExceptionMessage() {
        String expectedMessage = "Name cannot be null.";
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
                    throw new IllegalArgumentException(expectedMessage);
                });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void checkFirstParamIsBlank(String name) {
        if (name.isBlank()) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException();
            });
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void checkFirstParamBlankMessage(String name) {
        String expectedMessage = "Name cannot be blank.";

        if (name.isBlank()) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                        throw new IllegalArgumentException(expectedMessage);
                    });

            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0})
    void checkSecondParamMinus(double speed) {
        if (speed < 0) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException();
            });
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0})
    void checkSecondParamMinusMessage(double speed) {
        String expectedMessage = "Speed cannot be negative.";

        if (speed < 0) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                        throw new IllegalArgumentException(expectedMessage);
                    });

            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0})
    void checkThirdParamMinus(double distance) {
        if (distance < 0) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException();
            });
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0})
    void checkThirdParamMinusMessage(double distance) {
        String expectedMessage = "Distance cannot be negative.";

        if (distance < 0) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                        throw new IllegalArgumentException(expectedMessage);
                    });

            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Буцефал"})
     void checkArgumentName(String actual) {
        String expectedValue = "Буцефал";
        assertEquals(expectedValue, actual);
    }

     @ParameterizedTest
     @ValueSource(doubles = {2.4})
     void checkArgumentSpeed(double actual) {
         double expectedValue = 2.4;
         assertEquals(expectedValue, actual);
     }

     @ParameterizedTest
     @ValueSource(doubles = {0})
     void checkArgumentDistance(double actual) {
         double expectedValue = 0;
         assertEquals(expectedValue, actual);
     }
}
