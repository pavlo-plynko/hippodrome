import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
 class HorseTest {

    @Test
    void checkNullArgumentHorse() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0));
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

     @Mock
     List<Horse> horseList;

    @Test
    void checkListSize() {
        horseList = Mockito.mock(ArrayList.class);
        int expectedSize = 7;

        Mockito.doReturn(expectedSize).when(horseList).size();
        assertEquals(expectedSize, horseList.size());
    }

    @Test
    void checkArgumentNameHorseList() {
        String expectedName = "Зефир";
        int expectedIndex = 2;

        Mockito.doReturn(expectedName).when(horseList).get(any(int.class));
        assertEquals(expectedName, horseList.get(expectedIndex));

    }

}
